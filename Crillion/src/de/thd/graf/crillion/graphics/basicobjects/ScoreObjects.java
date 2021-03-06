package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

import java.awt.*;

/**
 * Subclass of GameObject to initial score variables
 */
public abstract class ScoreObjects extends GameObject{

    protected String name;
    protected int score;

    /**
     * Create the Scoreobject
     * @param gameView Get the Gameview
     */
    public ScoreObjects(GameView gameView){
        super(gameView);
        this.size = 20;
        this.rotation = 0;
        this.color = Color.WHITE;
    }

}
