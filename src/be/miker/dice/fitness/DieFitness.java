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
public interface DieFitness {
	
	/**
	 * Calculates the fitness of a given die.
	 * 
	 * @param theDie the die to calculate the fitness off
	 * @return the fitness
	 */
	public double calculateFitness(Die theDie);
}
