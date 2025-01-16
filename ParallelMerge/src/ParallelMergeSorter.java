import java.util.*;

public class ParallelMergeSorter {

    // Method to perform parallel merge sort using MergeTask threads
    public static int[] parallelMergeSort(int[] array, int n, int m) throws InterruptedException {
        List<int[]> pool = new ArrayList<>();
        for (int value : array) {
            pool.add(new int[]{value});
        }

        while (pool.size() > 1) {
            List<int[]> newPool = new ArrayList<>();
            List<MergeTask> tasks = new ArrayList<>();

            for (int i = 0; i < pool.size(); i += 2) {
                if (i + 1 < pool.size()) {
                    MergeTask task = new MergeTask(pool.get(i), pool.get(i + 1));
                    tasks.add(task);
                    task.start(); // Start the thread
                } else {
                    // Add the last array directly if there's no pair
                    newPool.add(pool.get(i));
                }
            }

            // Wait for all threads to complete
            for (MergeTask task : tasks) {
                task.join();
                newPool.add(task.getResult());
            }

            pool = newPool;
        }

        return pool.get(0);
    }

    // Method to generate an array of random integers
    public static int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(100) + 1;
        }
        return array;
    }
}
