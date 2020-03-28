package me.edfame;

import java.util.Random;
import java.util.Scanner;

public class Main {

    //Given a room, initializes with a "default" Point.
    private static Point[][] initializeRoom() {

        Scanner input = new Scanner(System.in);
        int size;

        //Getting the room size from the std input.
        System.out.print("Insert the room width/heigh: ");
        size = input.nextInt();

        Point [][] room = new Point[size][size];

        //Initializing all the points in the room
        for (Point[] row : room) {
            for (int index = 0; index < row.length; index++) {
                row[index] = new Point();
            }
        }

        return room;
    }

    //Given a room, sets a Door on a random position.
    private static Door setDoor(Point[][] room) {

        Random random = new Random();

        int x = random.nextInt(room.length);
        int y = random.nextInt(room.length);

        room[y][x] = new Door(x,y);

        return (Door) room[y][x];

    }

    //Given a room, sets a Person with a position different from the Door's.
    private static Person setPerson(Point[][] room) {

        Random random = new Random();
        int x, y;

        do {

            x = random.nextInt(room.length);
            y = random.nextInt(room.length);

        }while (room[y][x].getType() == 'D');

        room[y][x] = new Person(x,y);

        return (Person) room[y][x];
    }

    //Given a room, inserts random Obstacles.
    private static void setObstacles(Point[][] room) {

        Random random = new Random();

        int obstacleX = random.nextInt(room.length);
        int obstacleY = random.nextInt(room.length);

        for (int obstacles = 0; obstacles < room.length; ) {

            if (room[obstacleY][obstacleX].getType() != '_') {

                obstacleX = random.nextInt(room.length);
                obstacleY = random.nextInt(room.length);

            } else {

                room[obstacleY][obstacleX] = new Obstacle();
                obstacles++;
            }
        }
    }

    //Prints the content of the room given.
    private static void printRoom(Point[][] room) {

        for (Point[] row: room) {
            for (Point point : row) {
                System.out.print(point.getType());
            }
            System.out.println("");

        }
    }

    //Prints the move that are allowed to make.
    private static void printMoves() {

        System.out.println("\nL - Left\nR - Right\nU - Up\nD - Down\n");
    }

    //Moves a person on the given room.
    private static void getPlay(Point[][] room, Person person) {

        Scanner input = new Scanner(System.in);
        char direction;
        int multiplier;

        System.out.print("Play format: \"DirectionMultiplier\" > ");

        String play = input.nextLine();

        if(play.length() < 2) {

            direction = play.charAt(0);
            multiplier = 1;

        } else {

            direction = play.charAt(0);
            multiplier = Character.getNumericValue(play.charAt(1));
        }

        switch (direction) {

            case 'L':
                try {

                    for (int i = person.getX(); i != person.getX() - multiplier ; i--) {
                        if (room[person.getY()][i] instanceof Obstacle){
                            System.out.println("Ups! There is an obstacle there! Insert a new direction.");
                            return;
                        }
                    }

                    if (!(room[person.getY()][person.getX() - multiplier] instanceof  Obstacle)) {

                        room[person.getY()][person.getX()] = new Point(person.getX(), person.getY(), '_');
                        person.setX(person.getX() - multiplier);
                        room[person.getY()][person.getX()] = person;

                    } else {

                        System.out.println("Ups! There is an obstacle there! Insert a new direction.");

                    }
                    break;

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println("WALL! New direction please!");
                    break;
                }

            case 'R':
                try {

                    for (int i = person.getX(); i != person.getX() + multiplier ; i++) {
                        if (room[person.getY()][i] instanceof Obstacle){
                            System.out.println("Ups! There is an obstacle there! Insert a new direction.");
                            return;
                        }
                    }

                    if (!(room[person.getY()][person.getX() + multiplier] instanceof Obstacle)) {

                        room[person.getY()][person.getX()] = new Point(person.getX(), person.getY(), '_');
                        person.setX(person.getX() + multiplier);
                        room[person.getY()][person.getX()] = person;

                    } else {

                        System.out.println("Ups! There is an obstacle there! Insert a new direction.");

                    }
                    break;

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println("WALL! New direction please!");
                    break;
                }

            case 'U':

                for (int i = person.getY(); i != person.getX() - multiplier ; i--) {
                    if (room[i][person.getX()] instanceof Obstacle){
                        System.out.println("Ups! There is an obstacle there! Insert a new direction.");
                        return;
                    }
                }

                try {
                    if (!(room[person.getY() - multiplier][person.getX()] instanceof  Obstacle)) {

                        room[person.getY()][person.getX()] = new Point(person.getX(), person.getY(), '_');
                        person.setY(person.getY() - multiplier);
                        room[person.getY()][person.getX()] = person;

                    } else {

                        System.out.println("Ups! There is an obstacle there! Insert a new direction.");

                    }

                    break;

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println("WALL! New direction please!");
                    break;

                }

            case 'D':

                try {

                    for (int i = person.getY(); i != person.getX() + multiplier ; i++) {
                        if (room[i][person.getX()] instanceof Obstacle){
                            System.out.println("Ups! There is an obstacle there! Insert a new direction.");
                            return;
                        }
                    }

                    if (!(room[person.getY() + multiplier][person.getX()] instanceof  Obstacle)) {

                        room[person.getY()][person.getX()] = new Point(person.getX(), person.getY(), '_');
                        person.setY(person.getY() + multiplier);
                        room[person.getY()][person.getX()] = person;

                    } else {

                        System.out.println("Ups! There is an obstacle there! Insert a new direction.");

                    }
                    break;

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println("WALL! New direction please!");
                    break;

                }

            default:
                System.out.println("\tINSERT A VALID DIRECTION!");
                break;
        }

    }

    public static void main(String[] args) {

        //Variables
        Point[][] room;
        Door door;
        Person person;

        //initializing the room.
        room = initializeRoom();

        //setting the door in the room.
        door = setDoor(room);

        //setting the person in the room.
        person = setPerson(room);

        //setting some obstacles in the room.
        setObstacles(room);

        do {

            //printing the room's content.
            printRoom(room);

            //prints the possible moves.
            printMoves();

            getPlay(room, person);

        } while ((person.getY() != door.getY()) || (person.getX() != door.getX()));

        System.out.println(">- HYPE! You won! -<");

    }
}
