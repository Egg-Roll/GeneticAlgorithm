// Justin Do

import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    private static final Random rng = new Random(); // Used for random number generation

    /*
     * No-arg constructor
     */
    public Chromosome() {
    }

    /*
     * Adds a copy of each of the items passed in to this Chromosome. Uses a random
     * number to decide whether each item’s included = field is set to true or false.
     */
    public Chromosome(ArrayList<Item> items) {
        for (Item i : items) {
            Item j = new Item(i);
            j.setIncluded(rng.nextBoolean());
            this.add(j);
        }
    }

    /*
     * Creates and returns a new child chromosomes by performing a crossover operation
     * this chromosome and the other one that is pass in. A random number is used to decide
     * which parent's item should be copied and added to the child.
     */
    public Chromosome crossover(Chromosome other) {
        Chromosome childChromes = new Chromosome();

        for (int i = 0; i < this.size(); i++) {
            if (rng.nextBoolean()) {
                childChromes.add(new Item(this.get(i)));
            } else {
                childChromes.add(new Item(other.get(i)));
            }
        }

        return childChromes;
    }

    /*
     * Performs the mutation operation on this chromosome
     * Uses a random number to decided whether to flip it's included field from true
     * to false or vice versa.
     */
    public void mutate() {
        for (Item item : this) {
            if (rng.nextInt(10) == 1) {
                boolean inclusion = item.isIncluded();
                item.setIncluded(!inclusion);
            }
        }
    }

    /*
     * Returns the fitness of his chromosome.
     * If the sum of all included items' weights are greater than 10, the fitness is zero.
     * Otherwise, the fitness is equal to the sum of all the included items' values.
     */
    public int getFitness() {
        double totalWeight = 0;
        int fitnessLevel = 0;

        for (Item item : this) {
            if (item.isIncluded()) {
                totalWeight += item.getWeight();
                fitnessLevel += item.getValue();
            }
        }

        if (totalWeight > 10) {
            return 0;
        }

        return fitnessLevel;
    }

    /*
     * Returns -1 if this chromosome's fitness is greater than the other's fitness.
     * Returns 1 if this chromosome's fitness is less than the other one's.
     * Returns 0 if their fitness is the same.
     */
    public int compareTo(Chromosome other) {
        int comparison = 0;

        if (this.getFitness() > other.getFitness()) {
            comparison = -1;
        } else if (this.getFitness() < other.getFitness()) {
            comparison = 1;
        }

        return comparison;
    }

    /*
     * Displays the name, weight, and value of all items in this chromosome whose
     * included value is true, followed by the fitness of this chromosome.
     */
    public String toString() {
        String stats = "";

        for (Item item : this) {
            if (item.isIncluded()) {
                stats = stats.concat(item + "\n");
            }
        }
        stats = stats.concat("fitness: " + this.getFitness() + "\n");

        return stats;
    }
}
