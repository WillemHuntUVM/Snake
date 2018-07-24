package com.willemhunt.snake.logic;

import com.willemhunt.snake.gui.Graphics;
import javafx.scene.canvas.GraphicsContext;

/**
 * Main game logic class, manages the game state
 */
public class Game implements Runnable {

    private         Grid            grid;
    private         GraphicsContext context;
    private         int             height;
    private         int             width;
    private final   float           interval = 1000.0f/20;

    private boolean running;
    private boolean paused;
    private boolean keyIsPressed;

    /**
     * Default constructor.
     * @param grid Grid for the game to take place on.
     * @param context GraphicsContext for the game to be drawn on.
     */
    public Game(Grid grid, GraphicsContext context){
        this.grid       = grid;
        this.height     = height;
        this.width      = width;
        this.context    = context;
        running         = true;
        paused          = false;
        keyIsPressed    = false;
    }

    /**
     * Updates the game state and maintains time interval.
     */
    @Override
    public void run() {
        while(running && !paused){
            float time = System.currentTimeMillis();
            keyIsPressed = false;

            grid.update();

            Graphics.paint(grid, context);

            if(!grid.getSnake().isSafe()){
                pause();
                Graphics.paintResetMessage(context);
                break;
            }

            time = System.currentTimeMillis() - time;
            if(time < interval){
                try{
                    Thread.sleep((long)(interval - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Sets running flag to false.
     */
    public void stop(){
        running = false;
    }

    /**
     * Sets paused flag to false.
     */
    public void resume(){
        paused = false;
    }

    /**
     * Sets paused flag to true.
     */
    public void pause(){
        paused = true;
    }

    /**
     * Gets value of paused.
     * @return boolean paused.
     */
    public boolean isPaused(){
        return paused;
    }

    /**
     * Gets value of keyIsPressed.
     * @return boolean keyIsPressed.
     */
    public boolean isKeyIsPressed(){
        return keyIsPressed;
    }

    /**
     * Sets keyIsPressed flag to true.
     */
    public void setKeyPressed(){
        keyIsPressed = true;
    }

}
