/*
 * Created on Jun 13, 2004
 *
 */
package be.miker.dice.application;

import be.miker.dice.data.D20;
import be.miker.dice.data.D6;
import be.miker.dice.data.Die;
import be.miker.dice.fitness.DieFitness;
import be.miker.dice.fitness.NeighbourMinDistanceDieFitness;
import be.miker.dice.fitness.NeighbourSEMinDistanceDieFitness;
import be.miker.dice.fitness.OneFromNeighbourDieFitness;
import be.miker.dice.process.BruteForce;

/**
 * @author mike
 */
public class DiceApplicationTester {

    public static void main(String[] args) {
        DieFitness neighbourMinDistanceDieFitness = new NeighbourMinDistanceDieFitness();
        DieFitness oneFromNeighbourDieFitness = new OneFromNeighbourDieFitness();
        DieFitness neighbourSEMinDistanceDieFitness = new NeighbourSEMinDistanceDieFitness();
        DieFitness fitness = oneFromNeighbourDieFitness;
        Die die = new D6();

        BruteForce.process(die, fitness);
    }
}
