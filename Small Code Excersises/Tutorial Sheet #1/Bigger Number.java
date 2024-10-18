class BiggerNumber {

    // Prints the larger of two integers

    static int larger(int a, int b) {

        if (a > b) {

            return a;

        } else {

            return b;
        }
    }

    public static void main(String args[]) {

        int a;
        int b;

        a = 5;
        b = 6;

        System.out.println(" The larger number of " + a + " and " + b + " is " + larger(a, b));
    }
}