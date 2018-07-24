package com.willemhunt.snake.logic;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * The game's positional system.  keeps track of the size of the game board and location of pieces.
 */
public class Grid {

    public static final Color COLOR = Color.BLACK;
    public static final Color FRUIT_COLOR = Color.RED;
    public static int   SIZE;
    private Random rand = new Random();

    private ArrayList<ArrayList<Point>> grid;
    private Snake snake;
    private Point fruit;

    private final int rows = 40;
    private final int cols = 40;
    private int width;
    private int height;

    /**
     * Default constructor.
     * @param width
     * @param height
     */
    public Grid(int width, int height){

        SIZE = width/rows;
        this.width = width;
        this.height = height;

        grid = new ArrayList<ArrayList<Point>>(rows);
        for(int i=0;i<rows;i++){
            grid.add(new ArrayList<Point>(cols));
            for(int a=0;a<cols;a++){
                grid.get(i).add(new Point(a, i));
            }
        }

        this.snake = new Snake(this, getCenterPoint());
        this.fruit = getRandomPoint();
    }

    /**
     * Returns the point at a random position in the board.
     * @return Point p
     */
    private Point getRandomPoint() {
        return grid.get(rand.nextInt(rows)).get(rand.nextInt(cols));
    }

    /**
     * Returns the point at the center of the board.
     * @return Point p
     */
    private Point getCenterPoint() {
        return grid.get(rows/2).get(cols/2);
    }

    /**
     * Getter for the Grid's Snake object
     * @return Snake s
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Getter for the Grid's Fruit Point
     * @return Point p
     */
    public Point getFruit() {
        return fruit;
    }

    /**
     * Sets Grid's fruit location to a given point
     * @param fruit
     */
    public void setFruit(Point fruit) {
        this.fruit = fruit;
    }

    /**
     * Updates the snake's size and position.
     */
    public void update() {
        if(snake.getHead().equals(fruit)){
            snake.extend();
            fruit = getRandomPoint();
        }else{
            snake.move();
        }
    }

    /**
     * If a point would be outside the grid, wrap it to be on the opposite edge
     * @param point
     * @return Point p
     */
    public Point wrap(Point point){
        int x = point.getX();
        int y = point.getY();

        if(x >= cols)x=0;
        if(y >= rows)y=0;
        if(x < 0)x = cols-1;
        if(y < 0)y = rows-1;

        return new Point(x, y);
    }

    /**
     * Getter for the number of cols.
     * @return cols
     */
    public int getCols(){
        return cols;
    }

    /**
     * Getter for the number of rows.
     * @return rows
     */
    public int getRows(){
        return rows;
    }

    /**
     * Getter for the grid's width in pixels.
     * @return pixel width
     */
    public double getWidth(){
        return width;
    }

    /**
     * Getter for the grid's height in pixels.
     * @return pixel height
     */
    public double getHeight(){
        return height;
    }
}
