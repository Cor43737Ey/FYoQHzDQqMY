// 代码生成时间: 2025-09-22 00:21:26
import groovy.transform.TypeChecked

/**
 * Service class for sorting algorithms.
 */
@TypeChecked
class SortService {

    /**
     * Sorts an array of integers using the bubble sort algorithm.
     *
     * @param array The array of integers to sort.
     * @return The sorted array of integers.
     * @throws IllegalArgumentException if the input is null or not an array of integers.
     */
    Integer[] bubbleSort(Integer[] array) {
        if (array == null) {
            throw new IllegalArgumentException('Input array cannot be null.')
        }
        if (array.length == 0) {
            return array
        }

        boolean swapped
        do {
            swapped = false
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    // Swap elements
                    Integer temp = array[i]
                    array[i] = array[i + 1]
                    array[i + 1] = temp
                    swapped = true
                }
            }
        } while (swapped)

        return array
    }

    /**
     * Sorts an array of integers using the insertion sort algorithm.
     *
     * @param array The array of integers to sort.
     * @return The sorted array of integers.
     * @throws IllegalArgumentException if the input is null or not an array of integers.
     */
    Integer[] insertionSort(Integer[] array) {
        if (array == null) {
            throw new IllegalArgumentException('Input array cannot be null.')
        }
        if (array.length == 0) {
            return array
        }

        for (int i = 1; i < array.length; i++) {
            Integer key = array[i]
            int j = i - 1
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j]
                j--
            }
            array[j + 1] = key
        }

        return array
    }

    /**
     * Sorts an array of integers using the selection sort algorithm.
     *
     * @param array The array of integers to sort.
     * @return The sorted array of integers.
     * @throws IllegalArgumentException if the input is null or not an array of integers.
     */
    Integer[] selectionSort(Integer[] array) {
        if (array == null) {
            throw new IllegalArgumentException('Input array cannot be null.')
        }
        if (array.length == 0) {
            return array
        }

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j
                }
            }
            // Swap elements
            Integer temp = array[i]
            array[i] = array[minIndex]
            array[minIndex] = temp
        }

        return array
    }

}
