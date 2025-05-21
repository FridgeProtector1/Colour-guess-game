package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.engine.Stats;
import nz.ac.auckland.se281.engine.ai.gamestrategy.GameStrategy;

public class HardAi extends Ai {
  GameStrategy previousStrategy = null;

  public HardAi(Stats stats) {
    super(stats);
  }

  @Override
  public void chooseColours() {
    if (stats.getCurrentRound() > 2) {
      setStrategy(randomGameStrategy);
      this.ownColour = gameStrategy.chooseColour(stats);

      if (previousStrategy == randomGameStrategy) {
        setStrategy(avoidLastGameStrategy);
        previousStrategy = avoidLastGameStrategy;
      } else {
        setStrategy(randomGameStrategy);
        previousStrategy = randomGameStrategy;
      }
      this.guessColour = gameStrategy.chooseColour(stats);

    } else {
      super.chooseColours();
    }
  }
}
