package com.willemhunt.snake.logic;

/**
 * Class for managing individual points.
 * Keeps track of their position and motion.
 */
public class Point {
    private final int x;
    private final int y;

    /**
     * Default construtor.
     * @param x position
     * @param y position
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x value.
     * @return x value.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y value.
     * @return y value.
     */
    public int getY() {
        return y;
    }

    /**
     * Moves point by a specified offset.
     * @param dx x change
     * @param dy y change
     * @return
     */
    public Point translate(int dx, int dy){
        return new Point(x+dx, y+dy);
    }

    /**
     * Checks for equality with another Point.
     * @param other Point.
     * @return Whether the x and y coordinates are identical.
     */
    public boolean equals(Point other){
        return (other.getX() == x) && (other.getY() == y);
    }

    /**
     * Returns the cartesian coordinates of the Point.
     * @return String.
     */
    public String toString(){
        return x+", "+y;
    }
}
