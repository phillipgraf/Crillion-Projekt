package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.basicobjects.ScoreObjects;

import java.awt.*;

class Level extends ScoreObjects {

 /**
  * Create the Level object
  * @param gameView get important Code from GameView
  */
 public Level(GameView gameView){
  super(gameView);
  this.position = new Position(GameView.WIDTH  - 200, GameView.HEIGHT - GameView.HEIGHT + 40);
  this.name = "Level";
  this.score = 0;
  this.size = 30;
  this.color = Color.WHITE;
  this.rotation = 0;
 }


 private void addPointsToScore(){}

 /**
  * {@inheritDoc}
  */
 @Override
 public void addToCanvas() {
  gameView.addTextToCanvas(this.name, this.position.x, this.position.y,this.size, this.color, this.rotation);
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public void updateStatus() {

 }
}