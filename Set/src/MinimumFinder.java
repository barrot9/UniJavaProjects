import java.util.Iterator;

public class MinimumFinder {
	public static <T extends Comparable<T>> T findMin(GenericSet<T> set) {
        Iterator<T> iterator = set.iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Set is empty, cannot find minimum element.");
        }

        T min = iterator.next(); // Assume the first element is the minimum
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (current.compareTo(min) < 0) {
                min = current;
            }
        }
        return min;
	}
}
