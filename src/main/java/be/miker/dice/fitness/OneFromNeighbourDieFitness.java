/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.fitness;

import be.miker.dice.data.Die;

/**
 * @author mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OneFromNeighbourDieFitness implements DieFitness {

	/**
	 *
	 */
	public OneFromNeighbourDieFitness() {
		super();
	}

	/* (non-Javadoc)
	 * @see main.java.dice.fitness.DieFitness#calculateFitness(main.java.dice.data.Die)
	 */
	public double calculateFitness(int[] values, int[][] faces) {
		double result = 0;

		for (int faceCounter = 0; faceCounter < values.length; faceCounter++) {
			for (int neighbourCounter = 0; neighbourCounter < faces[faceCounter].length; neighbourCounter++) {
				if((values[faceCounter] - values[faces[faceCounter][neighbourCounter]]) == 1) {
				    result++;
				}
			}
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
