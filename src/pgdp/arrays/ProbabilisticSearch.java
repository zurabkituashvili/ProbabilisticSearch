package pgdp.arrays;


import java.util.Arrays;

public class ProbabilisticSearch extends MiniJava {
    /**
     * Binary search slightly modified from the lecture
     */
    public static int[] find(int[] a, int x) {
        return find0(a, x, 0, a.length - 1, 1);
    }

    public static int[] find0(int[] a, int x, int n1, int n2, int numberOfSteps) {
        int t = (n1 + n2) / 2;
        if (a[t] == x)
            return new int[]{t, numberOfSteps};
        else if (n1 >= n2)
            return new int[]{-1, numberOfSteps};
        else if (x > a[t])
            return find0(a, x, t + 1, n2, numberOfSteps + 1);
        else if (n1 < t)
            return find0(a, x, n1, t - 1, numberOfSteps + 1);
        else return new int[]{-1, numberOfSteps};
    }

    public static int[] probalisticSearch(int[] arr, int value) {
        int position = (int) Math.round((value - arr[0]) / ((double) (arr[arr.length - 1] - arr[0]) / (arr.length - 1)));
        if (position < 0) position = 0;
        boolean asc = arr[position] < value;
        return recSearch(arr, value, position, -1, asc, 1);
    }

    private static int[] recSearch(int[] arr, int value, int position, int prevPosition, boolean asc, int calls) {

        if (position >= arr.length) position = arr.length - 1;
        else if (position < 0) position = 0;

        int newStep = asc ? (int) (position + Math.pow(2d, calls - 1)) : (int) (position - Math.pow(2d, calls - 1));

        if (newStep >= arr.length){
            newStep = arr.length - 1;
        }
        else if (newStep < 0){
            newStep = 0;
        }

        boolean isBetween = asc ? arr[newStep] > value : arr[newStep] < value;

        if (position == prevPosition) return new int[]{-1, calls};

        if (arr[position] == value) {
            return new int[]{position, calls};
        } else if (asc && isBetween) {
            return find0(arr, value, position + 1, newStep - 1, ++calls);
        } else if (!asc && isBetween) {
            return find0(arr, value, newStep + 1, position - 1, ++calls);
        } else if (asc) {
            return recSearch(arr, value, newStep, position, true, ++calls);
        } else {
            return recSearch(arr, value, newStep, position, false, ++calls);
        }

    }

    public static void compareApproaches(int[] arr, int min, int max) {

        long maxBinCalls = 0;
        long maxBinValue = Long.MIN_VALUE;
        long totalBinCalls = 0;
        long maxProbCalls = 0;
        long maxProbValue = Long.MIN_VALUE;
        long totalProbCalls = 0;

        for (int i = min; i <= max; i++) {
            int[] binResult = find(arr, i);
            if (binResult[1] > maxBinCalls) {
                maxBinCalls = binResult[1];
                maxBinValue = i;
            }

            int[] proRes = probalisticSearch(arr, i);
            if (proRes[1] > maxProbCalls) {
                maxProbCalls = proRes[1];
                maxProbValue = i;
            }

            totalBinCalls += binResult[1];
            totalProbCalls += proRes[1];

        }

        String sb = "Binary Search:\n" +
                "Maximum number of calls:\n" +
                maxBinCalls +
                "\nValue at which the maximum number of calls occurs:\n" +
                maxBinValue +
                "\nNumber of total calls:\n" +
                totalBinCalls +
                "\nProbabilistic Search:\n" +
                "Maximum number of calls:\n" +
                maxProbCalls +
                "\nValue at which the maximum number of calls occurs:\n" +
                maxProbValue +
                "\nNumber of total calls:\n" +
                totalProbCalls;

        System.out.println(sb);

    }

    public static void main(String[] args) {
//        // Not part of the exercise but can be helpful for debugging purposes
//        int[] exampleArray = new int[]{6, 20, 22, 35, 51, 54, 59, 74, 77, 80, 87, 94, 97};
//        compareApproaches(exampleArray, 6, 97);
//        System.out.println(Arrays.toString(
//                probalisticSearch(exampleArray, 3)));
//        System.out.println(Arrays.toString(probalisticSearch(exampleArray, 25)));
//        System.out.println(Arrays.toString(probalisticSearch(exampleArray, 33)));
//        System.out.println(Arrays.toString(
//                probalisticSearch(exampleArray, 75)));
//        System.out.println(Arrays.toString(probalisticSearch(exampleArray, 54)));
//        System.out.println(Arrays.toString(probalisticSearch(exampleArray, 61)));
//        System.out.println(Arrays.toString(probalisticSearch(exampleArray, 74)));
//        System.out.println(Arrays.toString(probalisticSearch(exampleArray, 75)));

//        System.out.println(Arrays.toString(
//                find0(new int[]{6, 20}, 18, 0, 1, 0)));
//        System.out.println(Arrays.toString(find0(new int[]{51,54,59}, 54, 0, 2, 0)));

    }

}
