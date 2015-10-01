/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.process.ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Random;

import be.miker.dice.data.D20;
import be.miker.dice.data.Die;
import be.miker.dice.data.DieUtil;
import be.miker.dice.fitness.DieFitness;

/**
 * @author mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class D20Wrapper extends D20 implements Comparable {

	private DieFitness fitnessClass;


	public D20Wrapper(D20Wrapper wrapper) {
		super();
		setValues(wrapper.getValues().clone());
		setFitnessClass(wrapper.getFitnessClass());
	}

	/**
	 * Create a random D20 die.
	 *
	 */
	public D20Wrapper(DieFitness fitness, int nrOfMutations) {
		super();
		this.fitnessClass = fitness;
		mutate(nrOfMutations);
	}


	private int getUnused(boolean[] seen, int neighbour) {
		for (int counter = 0; counter < seen.length; counter++) {

		}
		return 0;
	}
	/**
	 *
	 * @param theDie
	 * @param mutationRate between 0 and 1, the chance a single position will mutate.
	 */
	public void crossover(Die theDie, double mutationRate) {
		ArrayList rejectPositions = new ArrayList();
		ArrayList rejectValues = new ArrayList();

		Random random = new Random();
		int faces = getNrOfFaces();
		int[] newValues = new int[faces];
		int[] valuesHere = getValues();
		int[] valuesThere = theDie.getValues();
		int division = DieUtil.getRandomDieThrow(faces);
		int[] currentValues = valuesHere;
		int currentValue;
		boolean[] seen = new boolean[faces];
		int mutationNr = 0;

		if(DieUtil.getRandomDieThrow(2)==1) {
			newValues[0] = valuesHere[0];
		} else {
			newValues[0] = valuesThere[0];
		}
		seen[newValues[0]] = true;

		int last;
		for (int facesCounter = 1; facesCounter < newValues.length; facesCounter++) {
			last = newValues[facesCounter-1];
			if(Math.abs(last-valuesHere[facesCounter]) > Math.abs(last-valuesThere[facesCounter])) {

				newValues[facesCounter] = valuesThere[facesCounter];

			} else {
				newValues[facesCounter] = valuesHere[facesCounter];
			}
		}
		setValues(newValues);
		mutate(mutationNr);
	}


	/**
	 *
	 * @param theDie
	 * @param mutationRate between 0 and 1, the chance a single position will mutate.
	 */
	public void crossoverOld(Die theDie, double mutationRate) {
		ArrayList rejectPositions = new ArrayList();
		ArrayList rejectValues = new ArrayList();

		Random random = new Random();
		int faces = getNrOfFaces();
		int[] newValues = new int[faces];
		int[] valuesHere = getValues();
		int[] valuesThere = theDie.getValues();
		int division = DieUtil.getRandomDieThrow(faces);
		int[] currentValues = valuesHere;
		int currentValue;
		boolean[] seen = new boolean[faces];
		int mutationNr = 0;

		for (int faceCounter = 0; faceCounter < seen.length; faceCounter++) {
			seen[faceCounter] = false;
			mutationNr = (random.nextDouble() < mutationRate)?mutationNr+1:mutationNr;
		}

		for (int faceCounter = 0; faceCounter < faces; faceCounter++) {
			if(faceCounter >= division) {
				currentValues = valuesThere;
			}

			currentValue = currentValues[faceCounter];

			if(!seen[currentValue]) {
				newValues[faceCounter] = currentValue;
				seen[currentValue] = true;
			} else {
				rejectPositions.add(new Integer(faceCounter));
			}
		}

		for (int seenCounter = 0; seenCounter < seen.length; seenCounter++) {
			if(!seen[seenCounter]) {
				rejectValues.add(new Integer(seenCounter));
			}
		}

		assert rejectPositions.size() == rejectValues.size();
		Collections.shuffle(rejectPositions);
		Collections.shuffle(rejectValues);
		ListIterator iter = rejectPositions.listIterator();
		int counter = 0;

		while(iter.hasNext()) {
			newValues[((Integer)iter.next()).intValue()] = ((Integer)rejectValues.get(counter)).intValue();
			counter++;
		}

		setValues(newValues);
		mutate(mutationNr);
	}

	public void mutate() {
		int one = DieUtil.getRandomDieThrow(getNrOfFaces());
		int two;
		// make sure we have two different positions
		while((two = DieUtil.getRandomDieThrow(getNrOfFaces())) == one);
		valueSwitch(one, two);
	}

	public void mutate(int nrOfTimes) {
		for (int nrCounter = 0; nrCounter < nrOfTimes; nrCounter++) {
			mutate();
		}
	}

	public void swap() {
		int first = DieUtil.getRandomDieThrow(getNrOfFaces());
		int second = (first+1)%getNrOfFaces();
		int tempValue = getValue(first);
		setValue(first, getValue(second));
		setValue(second, tempValue);
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object theDie) {
		double fitnessHere = getFitness();
		double fitnessThere = fitnessClass.calculateFitness((Die)theDie);
		if(fitnessHere < fitnessThere) {
			return -1;
		} else if (fitnessHere > fitnessThere) {
			return 1;
		}

		return 0;
	}



	private void valueSwitch(int firstPosition, int secondPosition) {
		int tempValue = getValue(secondPosition);
		setValue(secondPosition, getValue(firstPosition));
		setValue(firstPosition, tempValue);
	}

	/**
	 * @return Returns the fitness.
	 */
	public double getFitness() {
		return fitnessClass.calculateFitness(this);
	}

	/**
	 * @return Returns the fitness.
	 */
	public DieFitness getFitnessClass() {
		return fitnessClass;
	}
	/**
	 * @param fitness The fitness to set.
	 */
	public void setFitnessClass(DieFitness fitness) {
		this.fitnessClass = fitness;
	}
}
