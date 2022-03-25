// Justin Do

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GeneticAlgorithm {

    /*
     * Reads in the data file with the correct format and creates and returns
     * an ArrayList of Item objects.
     */
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {

        Scanner scan = new Scanner(new File(filename));
        ArrayList<Item> list = new ArrayList<>();

        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] tokens = line.split(", ");
            Item nextItem = new Item(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]));
            list.add(nextItem);
        }

        scan.close();
        return list;
    }

    /*
     * Creates and returns an ArrayList of populationSizeChromosome objects
     * that each contain items, with their included field randomly set to
     * true or false.
     */
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {

        ArrayList<Chromosome> populationSizeChromosome = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            populationSizeChromosome.add(new Chromosome(items));
        }

        return populationSizeChromosome;
    }

    /*
     * 1) Creates a set of 10 random individuals that are stored in the currentGeneration ArrayList<Chromosome>
     * 2) Adds each of the currentGeneration into the nextGeneration
     * 3) Randomly pairs off the individuals and performs a crossover to create a child
     *    and adds it to the nextGeneration.
     * 4) Randomly chooses 10 % of the nextGeneration to be mutated
     * 5) Sorts the nextGeneration based on their fitness
     * 6) Clears out the current generation
     * 7) Adds the top 10 of the nextGeneration to the currentGeneration
     * 8) Repeats the steps 2 - 7, 20 times or however many times specified
     * 9) Sorts the population
     * 10) Displays the fittest individual to the console.
     */
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Item> itemList = readData("item.txt");
        ArrayList<Chromosome> currentGeneration = initializePopulation(itemList, 10);

        for (int i = 0; i < 20; i++) {
            ArrayList<Chromosome> nextGeneration = new ArrayList<>(currentGeneration);

            for (int j = 1; j < nextGeneration.size(); j += 2) {
                Chromosome randomIndividual1 = nextGeneration.get(j);
                Chromosome randomIndividual2 = nextGeneration.get(j - 1);
                nextGeneration.add(randomIndividual1.crossover(randomIndividual2));
            }

            int selectedMutators = (int) (nextGeneration.size() * .1);

            for (int j = 0; j < selectedMutators; j++) {
                nextGeneration.get((int) (Math.random() * nextGeneration.size())).mutate();
            }

            Collections.sort(nextGeneration);
            currentGeneration.clear();

            for (int k = 0; k < itemList.size() / 2.0; k++) {
                currentGeneration.add(nextGeneration.get(k));
            }

            Collections.shuffle(currentGeneration);
        }

        Collections.sort(currentGeneration);
        System.out.print(currentGeneration.get(0));

    }
}






