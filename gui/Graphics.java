package com.willemhunt.snake.gui;

import com.willemhunt.snake.logic.Grid;
import com.willemhunt.snake.logic.Point;
import com.willemhunt.snake.logic.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static com.willemhunt.snake.logic.Grid.SIZE;

/**
 * Graphics class to paint the game to the canvas
 */
public class Graphics {

    private static final Color TEXT_COLOR = Color.WHEAT;

    /**
     * Paints the game to the canvas.
     * @param grid Game grid.
     * @param context Canvas GraphicsContext.
     */
    public static void paint(Grid grid, GraphicsContext context) {
        paintGrid(grid, context);
        paintSnake(grid, context);
        paintFruit(grid, context);
        paintScore(grid, context);
    }

    /**
     * Paints a grid Point to the canvas.
     * @param point Game grid
     * @param context Canvas GraphicsContext
     */
    public static void paintPoint(Point point, GraphicsContext context){
        context.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
    }

    /**
     * Paints the message to reset the game to the canvas.
     * @param context Canvas GraphicsContext.
     */
    public static void paintResetMessage(GraphicsContext context) {
        context.setFill(TEXT_COLOR);
        context.fillText("Hit RETURN to reset.", 10, 10);
    }

    /**
     * Paints the Snake object to the canvas.
     * @param grid Game grid.
     * @param context Canvas GraphicsContext.
     */
    public static void paintSnake(Grid grid, GraphicsContext context){
        Snake snake = grid.getSnake();
        context.setFill(Snake.COLOR);
        snake.getBody().forEach(point -> paintPoint(point, context));
        if(!snake.isSafe()){
            context.setFill(Snake.DEAD);
            paintPoint(snake.getHead(), context);
        }
    }

    /**
     * Paints the fruit Point to the Canvas.
     * @param grid Game grid.
     * @param context Canvas GraphicsContext
     */
    public static void paintFruit(Grid grid, GraphicsContext context){
        context.setFill(Grid.FRUIT_COLOR);
        paintPoint(grid.getFruit(), context);
    }

    /**
     * Paints the score text to the Canvas.
     * @param grid Game grid.
     * @param context Canvas GraphicsContext.
     */
    public static void paintScore(Grid grid, GraphicsContext context){
        Snake snake = grid.getSnake();
        context.setFill(TEXT_COLOR);
        context.fillText("Score: "+(100*snake.getBody().size()), 10, 490);
    }

    /**
     * Paints the grid background to the Canvas.
     * @param grid Game grid.
     * @param context Canvas GraphicsContext.
     */
    public static void paintGrid(Grid grid, GraphicsContext context){
        context.setFill(Grid.COLOR);
        context.fillRect(0, 0, grid.getWidth(), grid.getHeight());
    }
}
