/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.process;

import be.miker.dice.data.Die;
import be.miker.dice.fitness.DieFitness;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class BruteForce {

	public static void process(Die theDie, DieFitness fitness) {
		int nrFaces = theDie.getNrOfFaces();
		double bestResult = fitness.calculateFitness(theDie);
		int[] bestValues = theDie.getValues();
		int[] currentValues;
		double currentFitness;
		Set<String> values = new HashSet<>();

		System.out.println("Original die fitness = " + bestResult);
		System.out.println(theDie);

		PermutationGenerator gen = new PermutationGenerator(nrFaces);

		long counter= 0;
		while(gen.hasMore()) {
			currentValues = gen.getNext();
			theDie.setValues(currentValues);
			String stringValue = Arrays.stream(currentValues).mapToObj(value -> String.valueOf(value)).collect(Collectors.joining("")).toString();
			values.add(stringValue);
			counter++;
			if((currentFitness = fitness.calculateFitness(theDie)) < bestResult) {
				bestResult = currentFitness;
				bestValues = currentValues;
				System.out.println("Current best die fitness = " + bestResult+ " num left% = " + counter*100.0/gen.getNumLeft().doubleValue());
				System.out.println(theDie);
			}
		}

		theDie.setValues(bestValues);
		System.out.println("Best die fitness = " + bestResult);
		System.out.println(theDie);
		System.out.println("counter is = " + counter + " set size is = " + values.size());



	}
}
