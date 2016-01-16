package be.miker.dice.fitness;

import be.miker.dice.data.D6;

import static org.junit.Assert.assertEquals;

/**
 * Created by mike on 16/01/16.
 */
public class OneFromNeighbourDieFitnessTest {

    /**
     * {1, 4, 3, 2}, // value = 1, neighbour values = 2,3,4,5 = 1
     * {0, 3, 2, 5}, // value = 2, neighbour values = 1,3,4,6 = 2
     * {4, 5, 0, 1}, // value = 3, neighbour values = 1,2,5,6 = 1
     * {5, 4, 0, 1}, // value = 4, neighbour values = 1,2,5,6 = 1
     * {5, 3, 2, 0}, // value = 5, neighbour values = 1,3,4,6 = 2
     * {3, 4, 2, 1}  // value = 6, neighbour values = 2,3,4,5 = 1
     *
     * @throws Exception
     */
    @org.junit.Test
    public void calculateFitness() throws Exception {
        OneFromNeighbourDieFitness dieFitness = new OneFromNeighbourDieFitness();

        assertEquals(8, dieFitness.calculateFitness(new D6()), 0.00001);
    }

}
