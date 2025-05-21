package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.engine.GameState;

public class HardAi extends Ai {

  public HardAi(GameState gameState) {
    super(gameState);
  }

  @Override
  public void chooseColours() {
    // Picks colours randomly for the first 2 rounds.
    if (gameState.getCurrentRound() <= 2) {
      super.chooseColours();

      return;
    }

    if (gameState.getCurrentRound() == 3) {
      setStrategy(leastUsedGameStrategy);
      super.chooseColours();

      return;
    }

    // Cycles between least used color strategy and avoid last colour strategy, when the AI guess is
    // wrong
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
