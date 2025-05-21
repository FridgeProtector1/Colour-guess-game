package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.engine.Stats;

public class EasyAi extends Ai {

  public EasyAi(Stats stats) {
    super(stats);
    setStrategy(randomGameStrategy);
  }
}
