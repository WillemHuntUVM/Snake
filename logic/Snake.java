package com.willemhunt.snake.logic;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Snake {
    private ArrayList<Point> body;
    private Point head;

    public static final Color COLOR = Color.GREEN;
    public static final Color DEAD  = Color.YELLOW;

    private boolean safe;
    private int     length;
    private Grid    grid;
    private int     xVelocity;
    private int     yVelocity;


    /**
     * Default constructor.
     * @param grid Grid
     * @param point Initial Point
     */
    public Snake(Grid grid, Point point) {
        body = new ArrayList<>();
        body.add(point);
        length = 1;
        head = point;
        safe = true;
        xVelocity = 0;
        yVelocity = 0;
        this.grid = grid;
    }

    /**
     * Grows the snake to include a given point
     * @param point to grow to.
     */
    public void growTo(Point point){
        length++;
        checkAndAdd(point);
    }

    /**
     * Shifts the snake's head to a given point and removes its tail.
     * @param point to shift to.
     */
    public void shiftTo(Point point){
        checkAndAdd(point);
        body.remove(0);
    }

    /**
     * Checks whether a move is safe for the snake and makes the move.
     * @param point to move to.
     */
    private void checkAndAdd(Point point) {
        point = grid.wrap(point);
        safe = true;
        for(Point p: body){
            if(p.equals(point)){
                safe = false;
            }
        }
        body.add(point);
        head = point;
    }

    /**
     * Returns the "Head" of the snake, the last element in the body array.
     * @return The snake's "Head" position.
     */
    public Point getHead() {
        return body.get(body.size()-1);
    }

    /**
     * Getter for the entire snake body array.
     * @return Body array.
     */
    public ArrayList<Point> getBody(){
        return body;
    }

    /**
     * Returns true if safe is true or the snake has a length of 1.
     * @return Whether the snake is safe.
     */
    public boolean isSafe(){
        return safe || length == 1;
    }

    /**
     * Moves the Snake's head by translation.
     */
    public void move(){
        if(isMoving()){
            shiftTo(head.translate(xVelocity, yVelocity));
        }
    }

    /**
     * Returns whether the snake is stationary.  False if x and y velocity are 0.
     * @return Whether the snake is stationary.
     */
    private boolean isMoving() {
        return xVelocity != 0 || yVelocity != 0;
    }

    /**
     * Adds another point to the snake.
     */
    public void extend() {
        if(isMoving()){
            growTo(head.translate(xVelocity, yVelocity));
        }
    }

    /**
     * Sets snakes direction to Up.
     */
    public void setUp(){
        if(yVelocity == 1 && length > 1) return;
        xVelocity = 0;
        yVelocity = -1;
    }

    /**
     * Sets snake's direction to Down.
     */
    public void setDown(){
        if(yVelocity == -1 && length > 1)return;
        xVelocity = 0;
        yVelocity = 1;
    }

    /**
     * Sets snake's direction to Right.
     */
    public void setRight(){
        if(xVelocity == -1 && length > 1)return;
        xVelocity = 1;
        yVelocity = 0;
    }

    /**
     * Sets snake's direction to Left.
     */
    public void setLeft(){
        if(xVelocity == 1 && length > 1)return;
        xVelocity = -1;
        yVelocity = 0;
    }

    /**
     * Returns the cartesian coordinates of the snake's body points.
     * @return Coordinate list.
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(Point p: this.getBody()){
            str.append("(").append(p).append(") ");
        }
        return str.toString();
    }
}
