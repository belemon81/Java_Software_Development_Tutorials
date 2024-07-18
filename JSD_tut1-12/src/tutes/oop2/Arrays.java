package tutes.oop2;

/**
 * @overview This class provides a number of standalone procedures useful for
 * manipulating arrays
 */
public class Arrays {

    /**
     * A method to search for the index of a given element of an array.
     *
     * @param a an <code>int[]</code> array
     * @param x an element of the array whose index is to be searched for
     * @effects If <code>x</code> is in <code>a</code>, returns an index where <code>x</code> is stored;
     * otherwise, returns -1.
     */
    public static int search(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }

        return -1;
    }

    /**
     * A method to search for the index of a given element of a sorted array.
     *
     * @param a a sorted <code>int[]</code> array
     * @param x an element whose index is to be searched for
     * @requires <code>a</code> is sorted in ascending order
     * @effects If <code>x</code> is in <code>a</code>, returns an index where <code>x</code> is stored;
     * otherwise, returns -1.
     */
    public static int searchSorted(int[] a, int x) {
        // use sequential search
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x)
                return i;
            else if (a[i] > x) {
                return -1;
            }
        }

        return -1; // x is greater than all elements of a
    }

    /**
     * A method to search for the index of a given element of a sorted array.
     *
     * @param a a sorted <code>int[]</code> array
     * @param x an element whose index is to be searched for
     * @requires <code>a</code> is sorted in ascending order
     * @effects If <code>x</code> is in <code>a</code>, returns an index where <code>x</code> is stored;
     * otherwise, returns -1.
     */
    public static int searchSortedBin(int[] a, int x) {
        // use binary search
        if (a == null) return -1;
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2; // the floor mid point
            if (x == a[mid]) return mid;
            if (x < a[mid])
                high = mid - 1;
            else
                low = mid + 1;
        }

        return -1; // x is greater than all elements of a
    }

    /**
     * A method to sort the elements of an array in ascending order.
     *
     * @param a an <code>Comparable[]</code> array
     * @modifies <code>a</code>
     * @effects Rearranges the elements of <code>a</code> into ascending order
     * e.g. if <code>a = [3, 1, 6, 1]</code> before the call, on return
     * <code>a_post = [1, 1, 3, 6]</code>.
     */
    public static void sort(Comparable[] a) {
        // quick-sort implementation
        if (a == null) return;
        quickSort(a, 0, a.length - 1);
    }

    /**
     * A method to sort the elements of an array that are between the low and high
     * indexes.
     *
     * @param a    an <code>Comparable[]</code> array
     * @param low  the left-most position
     * @param high the right most position
     * @requires <code>0 <= low</code> and <code>high < a.length</code>
     * @modifies <code>a</code>
     * @effects sorts <code>a[low], ..., a[high]</code> into ascending order
     */
    private static void quickSort(Comparable[] a, int low, int high) {
        if (low >= high)
            return;

        int mid = partition(a, low, high);
        quickSort(a, low, mid);
        quickSort(a, mid + 1, high);
    }

    /**
     * A method that arranges elements of an array into two sub-arrays separated
     * by a middle value that is greater than all the elements of the first (left)
     * sub-array and is less than all the elements of the second (right)
     * sub-array.
     *
     * @param a an array to partition
     * @param i the left-most position of the array
     * @param j the righ-most position of the array
     * @requires <code>a</code> is not <code>null</code> and
     * <code>0 <= i < j < a.length</code>
     * @modifies <code>a</code>
     * @effects Reorders the elements into two contiguous groups,
     * <code>a[i],...,a[res]</code> and <code>a[res+1],...,a[j]</code>,
     * such that each element in the second group is at least as large as
     * each element of the first group. Returns <code>res</code>.
     */
    public static int partition(Comparable[] a, int i, int j) {
        Comparable x = a[i];
        while (true) {
            while (a[j].compareTo(x) > 0)
                j--;
            while (a[i].compareTo(x) < 0)
                i++;

            if (i < j) { // need to swap
                Comparable temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                j--;
                i++;
            } else {
                return j;
            }
        }
    }
}