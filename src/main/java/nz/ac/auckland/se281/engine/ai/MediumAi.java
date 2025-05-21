package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.engine.Stats;

public class MediumAi extends Ai {
  MediumAi(Stats stats) {
    super(stats);
  }

  @Override
  public void chooseColours() {

    if (stats.getCurrentRound() >= 2) {
      setStrategy(randomGameStrategy);
      this.ownColour = gameStrategy.chooseColour(stats);
      setStrategy(avoidLastGameStrategy);
      this.guessColour = gameStrategy.chooseColour(stats);
    } else {
      super.chooseColours();
    }
  }
}
