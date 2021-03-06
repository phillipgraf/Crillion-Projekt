package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.dynamicobjects.Ball;

import java.awt.event.KeyEvent;

class InputManager {

    private final GameView gameView;
    private final Ball ball;

    /**
     * Create the Input Manager
     * @param gameView get important code from GameView
     * @param ball
     */
    public InputManager(GameView gameView, Ball ball) {
        this.gameView = gameView;
        this.ball = ball;
    }

    /**
     * Get the user input and take action. Move the player object with the arrow keys.
     */
    public void updateUserInputs() {
        Integer[] gedruekteTasten = gameView.getKeyCodesOfCurrentlyPressedKeys();

        for (int keyCode : gedruekteTasten) {

            switch (keyCode) {
                case KeyEvent.VK_RIGHT:
                    this.ball.right();
                    break;
                case KeyEvent.VK_LEFT:
                    this.ball.left();
                    break;
                case KeyEvent.VK_UP:
                    this.ball.up();
                    break;
                case KeyEvent.VK_DOWN:
                    this.ball.down();
                    break;
            }

            if (Ball.DIAGONAL_MOVEMENT == false) {
                break;
            }
        }
    }

}
