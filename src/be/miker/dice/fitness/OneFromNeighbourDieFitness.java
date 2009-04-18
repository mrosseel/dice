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
	
	/** optimisation, hold arrays */
	private int[] values;
	/** optimisation, hold arrays */
	private int[][] faces;
	
	/**
	 * 
	 */
	public OneFromNeighbourDieFitness() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see be.miker.dice.fitness.DieFitness#calculateFitness(be.miker.dice.data.Die)
	 */
	public double calculateFitness(Die theDie) {
		this.values = theDie.getValues();
		this.faces = theDie.getFaces();
		double result = 0;
		
		for (int faceCounter = 0; faceCounter < this.values.length; faceCounter++) {
			for (int neighbourCounter = 0; neighbourCounter < this.faces[faceCounter].length; neighbourCounter++) {
				if((this.values[faceCounter] - this.values[this.faces[faceCounter][neighbourCounter]]) == 1) {
				    result++;
				}
			}
		}
		
		return (this.values.length*3) - result;
	}
}
