package be.miker.dice.application;


import be.miker.dice.data.D20;
import be.miker.dice.data.Die;
import be.miker.dice.data.DieUtil;
import be.miker.dice.fitness.*;
import org.jenetics.*;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionStatistics;

import static org.jenetics.engine.EvolutionResult.toBestPhenotype;
import static org.jenetics.engine.limit.bySteadyFitness;

/**
 *
 */
public class JeneticsTester {
    static Die die = new D20();
    static DieFitness neighbourMinDistanceDieFitness = new NeighbourMinDistanceDieFitness();
    static DieFitness neighbourSEDistanceDieFitness = new NeighbourSEMinDistanceDieFitness();
    static DieFitness oneNeighbourFitness = new OneFromNeighbourDieFitness();
    static DieFitness countableNeighbourDieFitness = new CountableNeighbourDieFitness();
    static DieFitness fitness = countableNeighbourDieFitness;

    // This method calculates the fitness for a given genotype.
    private static Double distance(final Genotype<EnumGene<Integer>> gt) {
        final int[] path = gt.getChromosome().toSeq().stream().mapToInt(EnumGene<Integer>::getAllele)
        .toArray();
        return fitness.calculateFitness(path, die.getFaces());
    }

    public static void main(String[] args) {

//        int[] normalDie = {5,4,3,2,1,0};
//        int[] bestDie = {3,2,1,4,5,0};
        int[] normalDie = {5,4,3,2,1,0,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        System.out.println("normal case: " + fitness.calculateFitness(normalDie, die.getFaces()));
        Optimize optimize = fitness.maximum()?Optimize.MAXIMUM:Optimize.MINIMUM;
        System.out.println("Optimize: " + optimize);
//        System.out.println("best case: " + fitness.calculateFitness(bestDie, die.getFaces()));
        // Configure and build the evolution engine.
        final Engine<EnumGene<Integer>, Double> engine = Engine
                .builder(
                        JeneticsTester::distance,
                        PermutationChromosome.ofInteger(die.getNrOfFaces()))
                .optimize(optimize)
                .maximalPhenotypeAge(11)
                .populationSize(50000)
                .alterers(
                        new SwapMutator<>(0.35),
                        new PartiallyMatchedCrossover<>(0.35)
                )
                .build();

        // Create evolution statistics consumer.
        final EvolutionStatistics<Double, ?>
                statistics = EvolutionStatistics.ofNumber();

        final Phenotype<EnumGene<Integer>, Double> best = engine.stream()
                // Truncate the evolution stream after 7 "steady"
                // generations.
                .limit(bySteadyFitness(100))
                        // The evolution will stop after maximal 100
                        // generations.
                .limit(25000)
                        // Update the evaluation statistics after
                        // each generation
                .peek(statistics)
                        // Collect (reduce) the evolution stream to
                        // its best phenotype.
                .collect(toBestPhenotype());

        System.out.println(statistics);
        System.out.println(best);
        final int[] path = best.getGenotype().getChromosome().toSeq().stream().mapToInt(EnumGene<Integer>::getAllele)
                .toArray();
        System.out.println("Standard fitness: " +
                neighbourMinDistanceDieFitness.calculateFitness(path, die.getFaces()));
        System.out.println("Squared fitness: " +
                neighbourSEDistanceDieFitness.calculateFitness(path, die.getFaces()));
        System.out.println("One distance fitness: " +
                oneNeighbourFitness.calculateFitness(path, die.getFaces()));
        System.out.println("Countable distance fitness: " +
                countableNeighbourDieFitness.calculateFitness(path, die.getFaces()));
        DieUtil.printNeighbours(die, path);
    }
}
