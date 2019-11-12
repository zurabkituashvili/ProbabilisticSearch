package pgdp.arrays;

public class ProbabilisticSearch extends  MiniJava {
    /**
     * Binary search slightly modified from the lecture
     */
    public static int[] find (int[] a, int x) {
        return find0(a, x, 0, a.length-1, 1);
    }

    public static int[] find0 (int[] a, int x, int n1, int n2, int numberOfSteps) {
        int t = (n1+n2)/2;
        if (a[t] == x)
            return new int[]{t, numberOfSteps};
        else if (n1 >= n2)
            return new int[]{-1, numberOfSteps};
        else if (x > a[t])
            return find0 (a,x,t+1,n2, numberOfSteps + 1);
        else if (n1 < t)
            return find0 (a,x,n1,t-1, numberOfSteps + 1);
        else return new int[]{-1, numberOfSteps};
    }

    public static int[] probalisticSearch(int[] arr, int value) {
        // TODO
        return new int[]{};
    }
    
    public static void compareApproaches(int[] arr, int min, int max) {
        // TODO
    }

    public static void main(String[] args) {
        // Not part of the exercise but can be helpful for debugging purposes
        // int[] exampleArray = new int[]{6, 20, 22, 35, 51, 54, 59, 74, 77, 80, 87, 94, 97};
    }
}
