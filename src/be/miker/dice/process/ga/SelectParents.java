/*
 * Created on 14-jun-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package be.miker.dice.process.ga;

import java.util.Random;


/**
 * @author mikers
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SelectParents {
	private int population;
	int gaussNr;
    int[] pick;
    Random random = new Random();
	
	/**
	 * 
	 */
	public SelectParents(int population) {
		this.population = population;
		this.gaussNr = getGaussSum(population);
		this.pick = new int[this.gaussNr];
		fillPick(population);
		// TODO Auto-generated constructor stub
	}
	
	
    public int getParent() {
        return pick[random.nextInt(this.gaussNr)];
    }
	
    private void fillPick(int population) {
    	int index = 0;
    	for (int counter = 0; counter < population; counter++) {
			for (int gaussCounter = 0; gaussCounter < this.population - counter; gaussCounter++) {
				pick[index++] = counter;
			}
		}
    }
    
    private int getGaussSum(int nr) {
    	return (int) ((nr*(nr+1))/2.0);
    }
}
