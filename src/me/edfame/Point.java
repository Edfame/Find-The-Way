package me.edfame;

public class Point {

    private int x;
    private int y;
    private char type;

    public Point() {
        this.type = '_';
    }

    public Point(char type) {
        this.x = 0;
        this.y = 0;
        this.type = type;
    }

    public Point(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {this.type = type;}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
