public class LeftRotation {
    public static int[] rotLeft(int[] a, int d) {
        int n = a.length;
        int effectiveRotations = d % n;  // Calculate the effective rotations needed
        int[] rotatedArray = new int[n];

        // Copy the elements to their new positions
        for (int i = 0; i < n; i++) {
            rotatedArray[i] = a[(i + effectiveRotations) % n];
        }

        return rotatedArray;
    }

    public static void main(String[] args) {
        // Example usage:
        int[] array = {1, 2, 3, 4, 5};
        int rotations = 4;
        int[] result = rotLeft(array, rotations);

        // Print the result
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Output should be: 5 1 2 3 4
    }
}
