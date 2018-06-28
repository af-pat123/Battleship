/* A simple Battleship game where the user and computer both deploy 5 ships. The user and computer both try and guess
the coordinates of the others ships in order to sink them. Winner is whoever sinks all 5 of the opponents ships.
*/

package com.company;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        char[][] field = new char[10][10];
        char[][] compField = new char[10][10];
        int x;
        int y;
        int userShips = 5;
        int compShips = 5;
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        initializeField(field);
        initializeField(compField);

        System.out.println("*** Welcome to Battle Ships Game***");
        System.out.println();
        System.out.println("Right Now The Sea is Empty");
        printField(field);

        deployUserShips(field,input);

        printField(field);

        deployCompShips(field,compField,rand);

        do {
            System.out.println("Your Turn");

            do {
                System.out.print("Enter X Coordinate:");
                x = input.nextInt();
                System.out.print("Enter Y coordinate:");
                y = input.nextInt();
            } while ( x < 0 || x > 10 || y < 0 || y > 10 ); //make sure x and y coordinates are within range 0-9

            if (field[y][x] == '@') {
                System.out.println("You sunk your own ship!");
                userShips--;
            } else if (compField[y][x] == '@') {
                System.out.println("Boom! You sunk the ship!");
                compShips--;
            } else {
                System.out.println("You missed!");
            }

            field[y][x] = 'X'; //mark the spot on the user's map

            System.out.println("Computers Turn");

            do {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
            } while (compField[y][x] == 'X');

            if (compField[y][x] == '@') {
                System.out.println("Computer sunk its own ship!");
                compShips--;
            } else if (field[y][x] == '@') {
                System.out.println("Boom! Computer sunk your ship!");
                field[y][x] = ' ';
                userShips--;
            } else {
                System.out.println("Computer missed!");
            }

            compField[y][x] = 'X'; //mark the spot on the computer's map

            printField(field);
            printScore(userShips, compShips);


        } while (userShips > 0 && compShips > 0);

        if (compShips == 0) {
            System.out.println("Congrations! You win!");
        } else {
            System.out.println("Computer wins");
        }

        input.close();
    }

    //loops through every element in the field and prints that element
    public static void printField(char[][] array) {

        System.out.println();
        System.out.println("   0123456789   ");

        for (int i = 0; i < array.length; i++) {
            System.out.print(i + " |");

            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println("| " + i);

        }
        System.out.println("   0123456789   ");
        System.out.println();

    }

    //makes every space on the field ' '
    public static void initializeField(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = ' ';
            }

        }


    }

    //asks the user for coordinates of the ships and marks the spots with a '@'
    public static void deployUserShips (char[][] array, Scanner input){
       int y;
       int x;

        System.out.println("Deploy Your Ships:");
        for (int i = 1; i < 6; i++) {
            do {
                System.out.print("Enter X coordinate for ship " + i + ":");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for ship " + i + ":");
                y = input.nextInt();
            } while ( x > 10 || x < 0 || y > 10 || y < 0 || array[y][x] == '@' );
            // make sure x and y coordinate falls within range 0-9 and a ship isn't already deployed at that coordinate

            array[y][x] = '@';
        }

    }

    //randomly chooses the coordinates for the computer's ships
    public static void deployCompShips(char[][] field, char[][] compField, Random rand){
        int x;
        int y;

        System.out.println("Computer is Deploying Ships");
        for (int i = 1; i < 6; i++) {

            do {
                x = rand.nextInt(10);
                y = rand.nextInt(10);

            } while (field[y][x] == '@'|| compField[y][x] == '@'); /*make sure neither the user nor the computer has
                                                                     already deployed a ship at that coordinate*/

            compField[y][x] = '@';
            System.out.println(i + ". ship Deployed");

        }

    }

    public static void printScore(int userShips, int compShips ){
        System.out.println();
        System.out.println("Your ships: " + userShips + " | Computer ships: " + compShips);
        System.out.println("----------------------------------------------");
        System.out.println();

    }


}
