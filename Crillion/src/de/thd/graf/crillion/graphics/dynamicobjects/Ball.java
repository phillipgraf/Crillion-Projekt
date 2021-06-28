package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.staticobjects.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * The player object. A (color changing) ball which is controlled by the Player.
 */
public class Ball extends CollidingGameObject {
    public enum StatusColor {RED, BLUE, GREEN, YELLOW, PURPLE}
    private StatusColor statusColor;

    private String ballImage;
    private boolean shooting;
    private boolean changeDirectionTopToBottom;
    private final BoundaryLeft boundaryLeft;
    private final BoundaryRight boundaryRight;
    private final BoundaryTop boundaryTop;
    private final BoundaryBottom boundaryBottom;
    private boolean pauseUserInput;
    private boolean leftSideHit;
    private boolean rightSideHit;
    private int bounceIndex;

    /**
     * Constant to activate diagonal movement
     */
    public final static boolean DIAGONAL_MOVEMENT = true;
    /**
     * Constant to change player object
     */
    public final static boolean PLAYER_GRAPHIC = true;

    /**
     * Create the PlayerObject
     *
     * @param gameView             get important Code from GameView
     * @param objectsToCollideWith Game objects this game object can collide with.
     */
    public Ball(GameView gameView, StatusColor statusColor, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.position = new Position(GameView.WIDTH / 2, GameView.HEIGHT / 2);
        this.shooting = false;
        this.speedInPixel = 2;
        this.size = 1.25;
        this.width = 11 * (int) size;
        this.height = 11 * (int) size;
        this.hitBox.width = (int) (this.width - 1 * size);
        this.hitBox.height = (int) (this.height - 1 * size);
        this.changeDirectionTopToBottom = false;
        this.pauseUserInput = false;
        this.leftSideHit = false;
        this.rightSideHit = false;
        this.bounceIndex = 0;

        this.boundaryTop = (BoundaryTop) objectsToCollideWith.get(0);
        this.boundaryLeft = (BoundaryLeft) objectsToCollideWith.get(1);
        this.boundaryBottom = (BoundaryBottom) objectsToCollideWith.get(2);
        this.boundaryRight = (BoundaryRight) objectsToCollideWith.get(3);

        switch (statusColor) {
            case BLUE:
                this.ballImage = "Blue-Ball.png";
                break;
            case RED:
                this.ballImage = "Red-Ball.png";
                break;
            case GREEN:
                this.ballImage = "Green-Ball.png";
                break;
            case PURPLE:
                this.ballImage = "Purple-Ball.png";
                break;
            case YELLOW:
                this.ballImage = "Yellow-Ball.png";
                break;
        }
        this.statusColor = statusColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) (this.position.x + 3 * size);
        this.hitBox.y = (int) (this.position.y + 3 * size);
    }

    /**
     * {@inheritDoc}
     *
     * @param otherObject The other GameObject that is involved in the collision.
     */
    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        if (collidesWith(boundaryLeft) || collidesWith(boundaryRight)) {
        } else {
            this.gameView.playSound("bounce.wav", false);
        }
    }

    /**
     * Move PlayerObject to the left
     */
    public void left() {
        if (collidesWith(boundaryLeft)) {
            this.position.left(0);
        } else {
            this.position.left(this.speedInPixel);
        }
    }

    /**
     * Move PlayerObject to the right
     */
    public void right() {
        if (collidesWith(boundaryRight)) {
            this.position.right(0);
        } else {
            this.position.right(this.speedInPixel);
        }
    }

    /**
     * Move PlayerObject up
     */
    public void up() {
        this.position.up(this.speedInPixel);
    }

    /**
     * Move PlayerObject down
     */
    public void down() {
        this.position.down(this.speedInPixel);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        if (!PLAYER_GRAPHIC) {
            if (shooting) {
                gameView.addTextToCanvas("O", this.position.x, this.position.y, this.size, Color.WHITE, this.rotation);
                shooting = false;
            } else
                gameView.addTextToCanvas("X", this.position.x, this.position.y, this.size, Color.WHITE, this.rotation);
        } else
            gameView.addImageToCanvas(this.ballImage, this.position.x, this.position.y, this.size, this.rotation);
    }


    private void updatePositition() {
        if (leftSideHit && this.bounceIndex <= 10) {
            left();
            moveTopToBottom();
            this.bounceIndex++;
        } else if (rightSideHit && this.bounceIndex <= 10) {
            right();
            moveTopToBottom();
            this.bounceIndex++;
        } else {
            this.leftSideHit = false;
            this.rightSideHit = false;
            this.bounceIndex = 0;
            this.pauseUserInput = false;
            moveTopToBottom();
        }


        if (collidesWith(boundaryTop)) {
            this.changeDirectionTopToBottom = true;
        } else if (collidesWith(boundaryBottom)) {
            this.changeDirectionTopToBottom = false;
        }
    }

    private void moveTopToBottom() {
        if (changeDirectionTopToBottom) {
            this.down();
        } else {
            this.up();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
        updatePositition();
    }



    /**
     * @param changeDirectionTopToBottom boolean changeDirectionTopToBottom
     */
    public void setChangeDirectionTopToBottom(boolean changeDirectionTopToBottom) {
        this.changeDirectionTopToBottom = changeDirectionTopToBottom;
    }

    /**
     * Pause the user Input
     *
     * @param pauseUserInput blooean pauseUserInput
     */
    public void setPauseUserInput(boolean pauseUserInput) {
        this.pauseUserInput = pauseUserInput;
    }

    /**
     * Get the PauseUserInput
     *
     * @return the boolean pauseUserInput
     */
    public boolean isPauseUserInput() {
        return pauseUserInput;
    }

    /**
     * Set the boolean leftSideHit
     *
     * @param leftSideHit boolean leftSideHit
     */
    public void setLeftSideHit(boolean leftSideHit) {
        this.leftSideHit = leftSideHit;
    }

    /**
     * Set value of rightSideHit
     *
     * @param rightSideHit boolean rightSideHit
     */
    public void setRightSideHit(boolean rightSideHit) {
        this.rightSideHit = rightSideHit;
    }

    /**
     * Get the Color of the ball
     * @return
     */
    public String getBallColor() {
        return String.valueOf(statusColor);
    }

    /**
     * Set the ball color
     * @param statusColor
     */
    public void setStatusColor(String statusColor) {
        switch (statusColor) {
            case "BLUE":
                this.statusColor = StatusColor.BLUE;
                this.ballImage = "Blue-Ball.png";
                break;
            case "RED":
                this.statusColor = StatusColor.RED;
                this.ballImage = "Red-Ball.png";
                break;
            case "GREEN":
                this.statusColor = StatusColor.GREEN;
                this.ballImage = "Green-Ball.png";
                break;
            case "PURPLE":
                this.statusColor = StatusColor.PURPLE;
                this.ballImage = "Purple-Ball.png";
                break;
            case "YELLOW":
                this.statusColor = StatusColor.YELLOW;
                this.ballImage = "Yellow-Ball.png";
                break;
        }
    }
}
