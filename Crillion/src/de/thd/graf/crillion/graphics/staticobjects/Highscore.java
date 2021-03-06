package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.basicobjects.ScoreObjects;

class Highscore extends ScoreObjects {
    /**
     * Create the Level object
     * @param gameView get important Code from GameView
     */
    public Highscore(GameView gameView){
        super(gameView);
        this.position = new Position(GameView.WIDTH, 30);
        this.score = 5;
        this.name = "Highscore:" + this.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(this.name, this.position.x - this.name.length() * this.size, this.position.y,
                this.size, this.color, this.rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }
}
