/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.process;

import be.miker.dice.data.Die;
import be.miker.dice.fitness.DieFitness;

/**
 * @author mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class BruteForce {
	/**
	 * 
	 */
	public BruteForce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void process(Die theDie, DieFitness fitness) {
		int nrFaces = theDie.getNrOfFaces();
		double bestResult = fitness.calculateFitness(theDie);
		int[] bestValues = theDie.getValues();
		int[] currentValues;
		double currentFitness;
		
		System.out.println("Original die fitness = " + bestResult);
		System.out.println(theDie);
		
		PermutationGenerator gen = new PermutationGenerator(nrFaces);
		while(gen.hasMore()) {
			currentValues = gen.getNext();
			theDie.setValues(currentValues);
			if((currentFitness = fitness.calculateFitness(theDie)) < bestResult) {
				bestResult = currentFitness;
				bestValues = currentValues;
				System.out.println("Current best die fitness = " + bestResult);
				System.out.println(theDie);
			}
		}
		
		theDie.setValues(bestValues);
		System.out.println("Best die fitness = " + bestResult);
		System.out.println(theDie);
		
		
		
	}
}
