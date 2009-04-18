/*
 * Created on Jun 13, 2004
 *  
 */
package be.miker.dice.application;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;

import be.miker.dice.data.D20;
import be.miker.dice.data.Die;
import be.miker.dice.data.DieUtil;
import be.miker.dice.fitness.DieFitness;
import be.miker.dice.fitness.NeighbourMinDistanceDieFitness;
import be.miker.dice.process.ga.D20Wrapper;
import be.miker.dice.process.ga.SelectParents;
import be.miker.dice.ui.ContrastChart;
/**
 * @author mike
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DiceApplicationTester {
	/**
	 *  
	 */
	public DiceApplicationTester() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		int POPULATION = 1000; 
		ContrastChart chart = new ContrastChart();
		JFrame panel = new JFrame();
		panel.getContentPane().add(new ContrastChart());
		panel.setVisible(true);
		
		int bestCounter = 0;
		DieFitness fitness = new NeighbourMinDistanceDieFitness();
		//DieFitness fitness = new OneFromNeighbourDieFitness();
		DieFitness fitnessStandard = new NeighbourMinDistanceDieFitness();
		Die die = new D20();
		long time = System.currentTimeMillis();
		double best = Double.MAX_VALUE;
		Die bestDie = null;
		//BruteForce.process(die, fitness);
		SelectParents parents = new SelectParents(POPULATION);
		ArrayList pool = new ArrayList(POPULATION);
		ArrayList newPool = new ArrayList(POPULATION);
		for (int poolCounter = 0; poolCounter < POPULATION; poolCounter++) {
			pool.add(new D20Wrapper(fitness, POPULATION));
		}
		int counter = 0;
		int newPoolCounter = 0;
		while (counter < Integer.MAX_VALUE) {
			newPool.clear();
			Collections.sort(pool);
			//Collections.reverse(pool);
			if (((D20Wrapper) pool.get(0)).getFitness() < best) {
			    bestDie = new D20Wrapper((D20Wrapper) pool.get(0));
				best = ((D20Wrapper) bestDie).getFitness();
				
				System.out.println("Best = " + best);
				System.out.println((Die) pool.get(0));
			}
			
	/*		for (int reproductionCounter = 0; reproductionCounter < bestCounter && reproductionCounter < pool.size(); reproductionCounter++) {
				newPool.add(new D20Wrapper((D20Wrapper) pool
						.get(0)));
			}
			
			for (int reproductionCounter = 0; reproductionCounter < pool.size() / 20.0; reproductionCounter++) {
				newPool.add(new D20Wrapper((D20Wrapper) pool
						.get(reproductionCounter)));
				newPool.add(new D20Wrapper((D20Wrapper) pool
						.get(reproductionCounter)));
				newPoolCounter++;
				newPoolCounter++;
			}
			*/
			
			/*
			for (int reproductionCounter = 0; reproductionCounter < pool.size() / 20; reproductionCounter++) {
				newPool.add(new D20Wrapper((D20Wrapper) pool
						.get(reproductionCounter)));
				newPoolCounter++;
			}
			*/
			
			int nrToDo = pool.size() - newPoolCounter;
			for (int reproductionCounter = 0; reproductionCounter < nrToDo; reproductionCounter++) {
				// DieUtil.getRandomDieThrow(pool.size());
				int partnerOne = parents.getParent();
				int partnerTwo = parents.getParent();
				D20Wrapper one = new D20Wrapper((D20Wrapper) pool.get(partnerOne));
				//D20Wrapper one = (D20Wrapper) pool.get(partnerOne);
				D20Wrapper two = (D20Wrapper) pool.get(partnerTwo);
				one.crossover(two, 0.005); //Math.exp(-Math.pow(counter,0.5)/30.0));
				newPool.add(one);
			}
			
			double totalFitness = 0;
			for (int reproductionCounter = 0; reproductionCounter < newPool.size(); reproductionCounter++) {
				totalFitness+= ((D20Wrapper)newPool.get(reproductionCounter)).getFitness();
			}
			
			bestCounter = 0;
			for (int reproductionCounter = 0; reproductionCounter < newPool.size(); reproductionCounter++) {
				bestCounter = (((D20Wrapper)newPool.get(reproductionCounter)).getFitness() == best)?bestCounter+1:bestCounter;
			}
			
			System.out.println("Generation = " + counter + " average fitness = " 
			        + totalFitness / newPool.size() + " best = "
			        + best + " pool best = " + ((D20Wrapper) pool.get(0)).getFitness() +" avg per die = " 
			        + fitnessStandard.calculateFitness(bestDie)/20.0/3.0 + " bestCounter = " + bestCounter + " mutation = " + Math.exp(-Math.pow(counter,0.5)/30.0));
			chart.addValue(totalFitness/ newPool.size());
			
		
			counter++;
			newPoolCounter = 0;
			
			
		}
		System.out.println("time elapsed = "
				+ (System.currentTimeMillis() - time));
		System.out
				.println("correctnees = " + DieUtil.testFacesCorrectness(die));
	}
}