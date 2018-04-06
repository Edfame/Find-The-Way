package me.edfame;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random random = new Random();
        Point person, door;

        //room size
        System.out.print("Insert the room width/heigh: ");
        int size = in.nextInt();
        char[][] room = new char[size][];

        //if the space is not a door nor a person is '_'
        for (int y = 0; y < room.length; y++) {
            room[y] = new char[size];
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
        for (int obstacles = 0; obstacles < size;) {
            if (room[obstacleY][obstacleX] != '_') {
                obstacleX = random.nextInt(size);
                obstacleY = random.nextInt(size);
            } else {
                room[obstacleY][obstacleX] = 'x';
                obstacles++;
            }
        }

        //if personPosition == doorPosition the game ends
        while ((person.getY() != door.getY()) || (person.getX() != door.getX())) {

            //to display the room
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (x < size - 1) System.out.print(room[y][x]);
                    else System.out.print(room[y][x] + "\n");
                }
            }

            System.out.print("\nL - Left\nR - Right\nF - Forward\nB - Backward\nInsert the letter of the direction: ");
            String directionString = in.next();

            //checks if the direction is only a letter or a word
            while (directionString.length() > 1) {
                System.out.print("Insert only the letter of the direction: ");
                directionString = in.next();
            }

            //if the string has only a char
            char direction = directionString.charAt(0);

            //tests if the direction is valid
            while (!(direction == 'L' || direction == 'R' || direction == 'F' || direction == 'B')) {
                System.out.print("\nInvalid direction.\nInsert a new one: ");
                direction = in.next().charAt(0);
            }

            //what to do in any of the valid directions
            switch (direction) {
                case 'L':
                    try {
                        if (room[person.getY()][person.getX() - 1] != 'x') {
                            room[person.getY()][person.getX()] = '_';
                            person.setX(person.getX() - 1);
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
                        if (room[person.getY()][person.getX() + 1] != 'x') {
                            room[person.getY()][person.getX()] = '_';
                            person.setX(person.getX() + 1);
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
                        if (room[person.getY() + 1][person.getX()] != 'x') {
                            room[person.getY()][person.getX()] = '_';
                            person.setY(person.getY() + 1);
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
                        if (room[person.getY() - 1][person.getX()] != 'x') {
                            room[person.getY()][person.getX()] = '_';
                            person.setY(person.getY() - 1);
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
