package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.engine.GameState;

public class MediumAi extends Ai {
  MediumAi(GameState gameState) {
    super(gameState);
  }

  @Override
  public void chooseColours() {
    if (gameState.getCurrentRound() >= 2) {
      setStrategy(avoidLastGameStrategy);
    }
    super.chooseColours();
  }
}
