package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.Main;
import nz.ac.auckland.se281.engine.Stats;

public class AiFactory {

  public static Ai createAi(Main.Difficulty difficulty, Stats stats) {
    switch (difficulty) {
      case EASY:
        return new EasyAi(stats);

      case MEDIUM:
        return new MediumAi(stats);
      case HARD:

      default:
    }

    return null;
  }
}
