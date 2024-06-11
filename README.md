Explanation
 We calculate Effective Rotations:

effectiveRotations = d % n: This computes the number of effective rotations needed, taking into account that rotating n times returns the array to its original state.
Create a New Array:

We create a new array rotatedArray to store the result of the rotations.
Copy Elements:

We use a loop to copy each element of the original array a to its new position in rotatedArray. The new position of each element is determined by (i + effectiveRotations) % n.
Print the Result:

In the main method, we print the elements of the rotated array to verify the result.
