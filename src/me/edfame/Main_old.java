package me.edfame;

import java.util.Random;
import java.util.Scanner;

public class Main_old {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random random = new Random();
        Point person, door;

        //room size
        System.out.print("Insert the room width/heigh: ");
        int size = in.nextInt();
        char[][] room = new char[size][size];

        //if the space is not a door nor a person is '_'
        for (int y = 0; y < room.length; y++) {
            for (int x = 0; x < room[y].length; x++) {
                room[y][x] = '_';
            }
        }

        //door position, the door is represented by a D
        door = new Point(random.nextInt(size), random.nextInt(size), 'D');
        room[door.getY()][door.getX()] = door.getType();

        //person position, the person is represented by a P
        do {
            person = new Point(random.nextInt(size), random.nextInt(size), 'P');
        } while (person.getX() == door.getX() || person.getY() == door.getY());
        room[person.getY()][person.getX()] = person.getType();

        //obstacles positions
        int obstacleX = random.nextInt(size);
        int obstacleY = random.nextInt(size);
        for (int obstacles = 0; obstacles < size; ) {
            if (room[obstacleY][obstacleX] != '_') {
                obstacleX = random.nextInt(size);
                obstacleY = random.nextInt(size);
            } else {
                room[obstacleY][obstacleX] = 'x';
                obstacles++;
            }
        }
        //prints the directions ONCE.
        System.out.print("\nL - Left\nR - Right\nF - Forward\nB - Backward\n\n");

        //skip \n
        String skip = in.nextLine();

        //if personPosition == doorPosition the game ends
        while ((person.getY() != door.getY()) || (person.getX() != door.getX())) {

            //to display the room
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (x < size - 1) System.out.print(room[y][x]);
                    else System.out.print(room[y][x] + "\n");
                }
            }

            System.out.print("Insert the direction with the format Letter Multiplier: ");
            String directionString = in.nextLine();

            //valid letters and multipliers
            char[] validLetters = {'L', 'R', 'F', 'B'};
            int[] validMultipliers = {1, 2, 3, 4, 5, 6, 7, 8, 9};

            //direction letter and multiplier
            char directionLetter, directionMultiplier;
            int multiplier;

            if (directionString.length() != 3 ){
                directionLetter = directionString.charAt(0);
                multiplier = 1;

            } else {
                directionLetter = directionString.charAt(0);
                directionMultiplier = directionString.charAt(2);
                multiplier = Character.getNumericValue(directionMultiplier);
            }

            //tests if the direction is valid
            for (int i = 0; i < validLetters.length; i++) {
                if (directionLetter == validLetters[i]) {
                    break;
                } else if (i == 3) {
                    System.out.print("\nInvalid Letter.\nInsert a new one: ");
                    directionLetter = in.next().charAt(0);
                }
            }

            //tests if the multiplier is valid
            for (int i = 0; i < validMultipliers.length; i++) {
                if (multiplier != validMultipliers[i]) {
                    break;
                } else if (i == 8){
                    System.out.print("\nInvalid Multiplier.\nInsert a new one: ");
                    directionMultiplier = in.next().charAt(2);
                    multiplier = Character.getNumericValue(directionMultiplier);
                }
            }

            //what to do in any of the valid directions
            switch (directionLetter) {
                case 'L':
                    try {
                        if (room[person.getY()][person.getX() - multiplier] != 'x') {
                            room[person.getY()][person.getX()] = '_';
                            person.setX(person.getX() - multiplier);
                            room[person.getY()][person.getX()] = person.getType();
                            break;
                        } else {
                            System.out.println("Ups! There is an obstacle there! Insert a new direction.");
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("WALL! New direction please!");
                        break;
                    }
                case 'R':
                    try {
                        if (room[person.getY()][person.getX() + multiplier] != 'x') {
                            room[person.getY()][person.getX()] = '_';
                            person.setX(person.getX() + multiplier);
                            room[person.getY()][person.getX()] = person.getType();
                            break;
                        } else {
                            System.out.println("Ups! There is an obstacle there! Insert a new direction.");
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("WALL! New direction please!");
                        break;
                    }
                case 'B':
                    try {
                        if (room[person.getY() + multiplier][person.getX()] != 'x') {
                            room[person.getY()][person.getX()] = '_';
                            person.setY(person.getY() + multiplier);
                            room[person.getY()][person.getX()] = person.getType();
                            break;
                        } else {
                            System.out.println("Ups! There is an obstacle there! Insert a new direction.");
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("WALL! New direction please!");
                        break;
                    }
                case 'F':
                    try {
                        if (room[person.getY() - multiplier][person.getX()] != 'x') {
                            room[person.getY()][person.getX()] = '_';
                            person.setY(person.getY() - multiplier);
                            room[person.getY()][person.getX()] = person.getType();
                            break;
                        } else {
                            System.out.println("Ups! There is an obstacle there! Insert a new direction.");
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("WALL! New direction please!");
                        break;
                    }

            }
        }
        System.out.println(">- HYPE! You won! -<");
    }
}
