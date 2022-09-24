package data_structures;

import java.util.Random;

/*cd "/Users/havardskjaerstein/Desktop/Amsterdam/Concurrency & Multithreading /DataStructures/src/data_structures" ; /usr/bin/env /Library/Java/JavaVirtualMachines/jdk-18.0.1.1.jdk/Contents/Home/bin/java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages -cp "/Users/havardskjaerstein/Library/Application Support/Code/User/workspaceStorage/31c4ca1df4edf1e693444dbb45481cf9/redhat.java/jdt_ws/jdt.ls-java-project/bin" data_structures.Main cgl 1 16 10000 */

/**
 * This is the main program of the Concurrency and Multithreading programming assignment.
 */
public class Main {
    
    static String dataStructure;
    static int nrItems;
    static int nrThreads;
    static int workTime;
    static boolean debug = false;
    static long seed;

    /** Coarse-grained list. */
    static final String CGL = "cgl";
    /** Coarse-grained tree. */
    static final String CGT = "cgt";
    /** Find-grained list. */
    static final String FGL = "fgl";
    /** Fine-grained tree. */
    static final String FGT = "fgt";

    /**
     * Deterministically computes a seed from the parameters.
     * @param params - the integer parameters from which to compute the seed
     */
    static long computeSeed(int... params) {

        long result = 0;

        for (int p : params) {
            result <<= 16;
            result |= p;
        }
        //Returns the seed 
        return result;
    }

    /**
     * Permutes an array in a predictable manner, based on a specific seed.
     *
     * @param array - the array to permute.
     * @param seed - the seed to use for the random number generator.
     */

    private static void permute(Integer[] array, long seed) {

        Random random = new Random(seed);

        for (int i = 0; i < array.length; i++) {
            int r = random.nextInt(array.length);
            int swapped = array[i];
            array[i] = array[r];
            array[r] = swapped;
        }
    }

    /**
     * Fills the specified <code>itemsToAdd</code> and
     * <code>itemsToRemove</code> arrays with pseudo-random numbers, based on
     * the specified seed.
     *
     * @param itemsToAdd - array to be initialized with items to add
     * @param itemsToRemove - array to be initialized with items to remove (the same items as <code>ItemsToAdd</code>, but in a different order)
     * @param seed - the seed
     */
    private static void createWorkData(Integer[] itemsToAdd, Integer[] itemsToRemove, long seed) {

        Random random = new Random(seed);

        for (int i = 0; i < itemsToAdd.length; i++) {
            int nextRandom = random.nextInt(1024 * 256);
            itemsToAdd[i] = nextRandom;
            itemsToRemove[i] = nextRandom;
        }
        permute(itemsToRemove, seed + 1);
    }

    /**
     * This method is called when there was some error in the arguments, and
     * explains how the program should be invoked.
     */
    static void exitWithError() {

        System.out.println("run_data_structures <data_structure> <nrThreads> <nrItems> <workTime> [debug]");
        System.out.println("  where:");
        System.out.printf("    <data_structure> in {%s, %s, %s, %s}\n", CGL, CGT, FGL, FGT);
        System.out.println("    <nrThreads> is a number > 0");
        System.out.println("    <nrItems> is a number > 0");
        System.out.println("    <workTime> is a number >= 0 (micro seconds)");
        System.out.println("    [debug] can be omitted. If added as the last parameter,");
        System.out.println("            the data structure will be printed ");
        System.out.println("            after adding and before removing the numbers.");
        System.exit(1);
    }

    static void parseArgs(String[] args) {
        if (args.length < 4 || args.length > 5) {
            exitWithError();
        }

        dataStructure = args[0];
        
        nrThreads = Integer.parseInt(args[1]);
        if (nrThreads < 1) {
            System.out.println("Hello"); 
            exitWithError();
        }

        nrItems = Integer.parseInt(args[2]);
        if (nrItems < 1) {
            exitWithError();
        }

        if (nrItems % nrThreads != 0) {
            System.out.println("nrItems should be divisible by nrThreads");
            System.exit(1);
        }

        workTime = Integer.parseInt(args[3]);
        if (workTime < 0) {
            exitWithError();
        }

        if (args.length == 5) {
            
            if (args[4].equalsIgnoreCase("debug")) {
                debug = true;
            } else {
                System.out.println(
                        "last argument should be 'debug', or be omitted\n");
                System.exit(1);
            }
        }

        seed = computeSeed(nrThreads, nrItems, workTime);
    }

    public static void main(String[] args) {
        
        parseArgs(args);
       
        // Create the items to be added and deleted.
        Integer[] itemsToAdd = new Integer[nrItems];
        Integer[] itemsToRemove = new Integer[nrItems];
 
        createWorkData(itemsToAdd, itemsToRemove, seed);

        DoRuns<Integer> run = new DoRuns<Integer>(dataStructure, nrThreads, itemsToAdd, itemsToRemove, workTime, debug);
      
        run.runDataStructure();
    }
}
