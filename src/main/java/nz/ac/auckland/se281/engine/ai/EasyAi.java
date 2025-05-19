package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.engine.ai.gamestrategy.RandomGameStrategy;

public class EasyAi extends Ai {

  public EasyAi() {
    setStrategy(new RandomGameStrategy());
  }
}
