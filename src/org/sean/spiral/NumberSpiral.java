package org.sean.spiral;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A program that will generate a number sequence in a spiral format.
 *
 * @author Sean M. Staley
 * @version 10/1/12
 */
public class NumberSpiral {

    /**
     * A method that will print out a grid of increasing numbers in a spiral format.
     *
     * @param lastNumber the number before the last integer in the sequence.
     */
    public void generateGrid(int lastNumber) {
        int gridLength = (int) Math.sqrt(lastNumber) + 2;
        int x = gridLength / 2;
        int y = gridLength / 2;
        int currentNumber = 0;
        int numberOfMoves = 2;
        String[][] grid = new String[gridLength][gridLength];

        // While the current number we are working on is less than the last number, cycle through the loops again.
        while (currentNumber < lastNumber) {
            if (currentNumber == 0) {
                grid[y][x] = Integer.toString(currentNumber);
                currentNumber++;
                x++;
                y--;
            }

            // Check to see if the current number is 1. If it is, stop.
            if (currentNumber == lastNumber) {
                break;
            }

            //Moving downward
            for (int i = 1; i <= numberOfMoves && currentNumber < lastNumber; i++) {
                y++;
                grid[y][x] = Integer.toString(currentNumber);
                currentNumber++;
            }

            //The number of moves needs to be 2 three times. Checking to make sure we are not incrementing now.
            if (numberOfMoves != 2 && currentNumber < lastNumber) {
                numberOfMoves++;
            }

            //Moving left
            for (int i = 1; i <= numberOfMoves && currentNumber < lastNumber; i++) {
                x--;
                grid[y][x] = Integer.toString(currentNumber);
                currentNumber++;
            }

            //Moving upward
            for (int i = 1; i <= numberOfMoves && currentNumber < lastNumber; i++) {
                y--;
                grid[y][x] = Integer.toString(currentNumber);
                currentNumber++;
            }

            //The number of moves needs to be 2 three times, so now we can being incremental process.
            if (currentNumber != lastNumber) {
                numberOfMoves++;
            }

            //Moving right
            for (int i = 1; i <= numberOfMoves && currentNumber < lastNumber; i++) {
                x++;
                grid[y][x] = Integer.toString(currentNumber);
                currentNumber++;
            }
        }

        //Adding the rows to a StringBuilder
        for (int row = 0; row <= (gridLength - 1); row++) {
            for (int column = 0; column <= (gridLength - 1); column++) {
                if (grid[row][column] == null) {
                    System.out.print(" ");
                    continue;
                }
                System.out.print(grid[row][column] + " ");
            }
            System.out.print('\n');
        }
    }

    /**
     * The main method that is executed when the program starts up.
     *
     * @param args no arguments are required for this method to function correctly.
     */
    public static void main(String args[]) {
        System.out.println("Welcome to Sean's Spiral Builder!");

        NumberSpiral numberSpiral = new NumberSpiral();
        boolean again = true;

        do {
            Scanner input = new Scanner(System.in);
            Scanner questionInput = new Scanner(System.in);

            try {
                System.out.print("Enter a number: ");
                int gridSize = input.nextInt();

                if (gridSize < 1) {
                    System.out.println("Please enter a non-negative number.");
                }
                else {
                    //Running the spiral method
                    numberSpiral.generateGrid(gridSize);
                }
            }
            catch (InputMismatchException exception) {
                System.out.println("Not an integer!");
            }

            System.out.println('\n' + "Would you like to try another? (Yes or No)");
            String another = questionInput.nextLine();
            if (another.equalsIgnoreCase("no") || another.equalsIgnoreCase("n")) {
                again = false;
            }
            else {
                continue;
            }
        }
        while (again);

        System.out.println("Ending Number Spiral!");
        System.exit(0);
    }
}