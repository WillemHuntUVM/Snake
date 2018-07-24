package com.willemhunt.snake.gui;

import com.willemhunt.snake.logic.Game;
import com.willemhunt.snake.logic.Grid;
import com.willemhunt.snake.logic.Snake;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main GUI class, starts game loop and updates GUI
 */
public class Main extends Application {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int COLS = 100;
    private static final int ROWS = 100;

    private Game game;
    private Grid grid;
    private GraphicsContext context;

    /**
     * Starts app.
     * @param args
     */
    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        context = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            Snake snake = grid.getSnake();
            if(game.isKeyIsPressed()){
                return;
            }
            switch (e.getCode()){
                case UP:
                    snake.setUp();
                    break;
                case DOWN:
                    snake.setDown();
                    break;
                case RIGHT:
                    snake.setRight();
                    break;
                case LEFT:
                    snake.setLeft();
                    break;
                case ENTER:
                    if(game.isPaused()){
                        reset();
                        (new Thread(game)).start();
                    }
            }
        });

        reset();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        (new Thread(game)).start();
    }

    /**
     * Resets the game state with a new Grid and Game.
     */
    private void reset(){
        grid = new Grid(WIDTH, HEIGHT);
        game = new Game(grid, context);
        Graphics.paint(grid, context);
    }

}
