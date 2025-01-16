public class MergeTask extends Thread {
    private final int[] left;
    private final int[] right;
    private int[] result;

    public MergeTask(int[] left, int[] right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        result = merge(left, right);
    }

    public int[] getResult() {
        return result;
    }

    // Static method to merge two sorted arrays into one
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }
}
