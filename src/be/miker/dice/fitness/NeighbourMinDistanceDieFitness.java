/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.fitness;

import be.miker.dice.data.Die;

/**
 * @author mike
 *
 * Sum of absolute values of difference between the current face and its neighbour faces.
 *
 */
public class NeighbourMinDistanceDieFitness implements DieFitness {

	/**
	 *
	 */
	public NeighbourMinDistanceDieFitness() {
		super();
	}

	public double calculateFitness(Die theDie) {
		return calculateFitness(theDie.getValues(), theDie.getFaces());
	}

	/* (non-Javadoc)
	 * @see be.miker.dice.fitness.DieFitness#calculateFitness(be.miker.dice.data.Die)
	 */
	public double calculateFitness(int[] values, int[][] faces) {
		double result = 0;

		for (int faceCounter = 0; faceCounter < values.length; faceCounter++) {
			for (int neighbourCounter = 0; neighbourCounter < faces[faceCounter].length; neighbourCounter++) {
				result+=Math.abs(values[faceCounter] - values[faces[faceCounter][neighbourCounter]]);
			}
		}

		return result;
	}
}
