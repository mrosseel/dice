/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.fitness;

import be.miker.dice.data.Die;

/**
 * @author mike
 */
public class CountableNeighbourDieFitness implements DieFitness {

	/**
	 *
	 */
	public CountableNeighbourDieFitness() {
		super();
	}

	/* (non-Javadoc)
	 * @see main.java.dice.fitness.DieFitness#calculateFitness(main.java.dice.data.Die)
	 */
	public double calculateFitness(int[] values, int[][] faces) {
		double result = 0;

		for (int faceCounter = 0; faceCounter < values.length; faceCounter++) {
			double intermediateResult = 0;
			for (int neighbourCounter = 0; neighbourCounter < faces[faceCounter].length; neighbourCounter++) {
				if((values[faceCounter] - values[faces[faceCounter][neighbourCounter]]) == 1) {
				    intermediateResult++;
				}
			}
			result += (intermediateResult/2)%2;
		}

		return result;
	}

	@Override
	public double calculateFitness(Die theDie) {
		return calculateFitness(theDie.getValues(), theDie.getFaces());
	}

	@Override
	public boolean maximum() {
		return true;
	}
}
