package com.alessio;

import java.util.Scanner;

public class Human extends Player {

    public Human(String name, char marker) {
        super(name, marker);
    }

    @Override
    public Coordinate move(int controls) {
        Scanner scanner = new Scanner(System.in);

        if (controls == 2) {
            System.out.println(this.getName() + " where would you like to place a marker? A1-3 | B1-3 | C1-3");

            int row = -1; // initializing to whatever anything (will change on user input)
            int column = -1;
            String input;
            Coordinate markerPos;
            while (true) { // loop until user inserts correct valid coordinates.
                try {
                    input = scanner.nextLine().toUpperCase(); //Get user input to parse as string
                    if (input.toLowerCase().equals("quit") || input.toLowerCase().equals("exit")) { //Allow player to quit game.
                        System.out.println("Exiting the game.");
                        System.exit(0);
                    }
                    if (input.length() > 1) { //Coordinates require min two values X and Y. If under 1 print error.
                        if (input.charAt(0) == 'A' || input.charAt(0) == 'B' || input.charAt(0) == 'C') {
                            if (input.charAt(1) == '1' || input.charAt(1) == '2' || input.charAt(1) == '3') {
                                switch (input.charAt(0)) {
                                    case 'A':
                                        row = 0;
                                        break;
                                    case 'B':
                                        row = 1;
                                        break;
                                    case 'C':
                                        row = 2;
                                        break;
                                    default:
                                }
                                column = Character.getNumericValue(input.charAt(1) - 1); // decrement value by 1 to fit array 0 index format
                                return markerPos = new Coordinate(row, column);
                            } else {
                                System.out.println("Invalid entry, second must be 1-2-3 without spaces example: A1");
                            }
                        } else {
                            System.out.println("Invalid entry, first value for rows must be A-B-C");
                        }
                    } else {
                        System.out.println("Invalid entry, coordinate requires 2 values for row and column.");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid entry " + e);
                }
            }
        } else {
            while (true) {
                try {
                    Coordinate coordinate;
                    System.out.println(this.getName() + " where would you like to place a marker? 1-9");
                    String position = scanner.nextLine();

                    switch (position) {
                        case "1":
                            return new Coordinate(0, 0);

                        case "2":
                            return new Coordinate(0, 1);

                        case "3":
                            return new Coordinate(0, 2);

                        case "4":
                            return new Coordinate(1, 0);

                        case "5":
                            return new Coordinate(1, 1);

                        case "6":
                            return new Coordinate(1, 2);

                        case "7":
                            return new Coordinate(2, 0);

                        case "8":
                            return new Coordinate(2, 1);

                        case "9":
                            return new Coordinate(2, 2);

                        case "quit":
                            System.exit(0);
                            break;
                        case "exit":
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid entry, please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid entry, please try again.");
                }
            }
        }
    }
}
