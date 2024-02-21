public class SortDemo {
    // 从大到小排序
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        printArray(array);
        barrelSort(array.clone());
        bubbleSort(array.clone());
        quickSort(array, 0, array.length - 1);
        printArray(array);
    }

    // 冒泡排序
    // 循环两两相邻比较，空间复杂度低，时间复杂度高
    static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        printArray(array);
    }

    // 桶排序
    // 申请flag空间，时间复杂度低，空间复杂度高
    static void barrelSort(int[] array) {
        int[] temp = new int[11];
        for (int k : array) {
            temp[k] = temp[k] + 1;
        }
        for (int i = temp.length - 1; i > 0; i--) {
            for (int j = 0; j < temp[i]; j++) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // 快速排序
    // 二分法
    static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left, j = right;
        int temp = array[left];
        boolean isSwap = false;
        while (i < j) {
            while (i < j && array[j] <= temp) {
                j--;
            }
            while (i < j && array[i] >= temp) {
                i++;
            }
            if (i < j) {
                array[i] = array[j];
                array[j] = temp;
                isSwap = true;
            }
        }
        if (!isSwap) {
            array[left] = array[j];
            array[j] = temp;
        }
        quickSort(array, left, j - 1);
        quickSort(array, j + 1, right);
    }
    static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
