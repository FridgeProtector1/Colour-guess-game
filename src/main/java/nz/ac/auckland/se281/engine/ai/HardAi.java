package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.engine.GameState;

public class HardAi extends Ai {

  public HardAi(GameState gameState) {
    super(gameState);
  }

  @Override
  public void chooseColours() {
    if (gameState.getCurrentRound() <= 2) {
      super.chooseColours();

      return;
    }

    if (gameState.getCurrentRound() == 3) {
      setStrategy(leastUsedGameStrategy);
      super.chooseColours();

      return;
    }

    if (!gameState.getAiLastGuessResult()) {
      if (this.guessColourStrategy == avoidLastGameStrategy) {
        setStrategy(leastUsedGameStrategy);
      } else {
        setStrategy(avoidLastGameStrategy);
      }
    }

    super.chooseColours();
  }
}
