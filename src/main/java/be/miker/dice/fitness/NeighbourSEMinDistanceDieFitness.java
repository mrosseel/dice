/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.fitness;

import be.miker.dice.data.Die;

/**
 * @author mike
 *
 * Sum of the squared difference between the current face and its neighbour faces.
 *
 */
public class NeighbourSEMinDistanceDieFitness implements DieFitness {

	/** optimisation, hold arrays */
	private int[] values;
	/** optimisation, hold arrays */
	private int[][] faces;

	/**
	 *
	 */
	public NeighbourSEMinDistanceDieFitness() {
		super();
	}

	public double calculateFitness(Die theDie) {
		return calculateFitness(theDie.getValues(), theDie.getFaces());
	}
	/* (non-Javadoc)
	 * @see main.java.dice.fitness.DieFitness#calculateFitness(main.java.dice.data.Die)
	 */
	public double calculateFitness(int[] values, int[][] faces) {
		this.values = values;
		this.faces = faces;
		double result = 0;

		for (int faceCounter = 0; faceCounter < this.values.length; faceCounter++) {
			for (int neighbourCounter = 0; neighbourCounter < this.faces[faceCounter].length; neighbourCounter++) {
				result+=Math.pow(this.values[faceCounter] - this.values[this.faces[faceCounter][neighbourCounter]], 2);
			}
		}

		return result;
	}
}
