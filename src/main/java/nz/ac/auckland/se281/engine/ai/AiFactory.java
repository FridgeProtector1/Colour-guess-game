package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.Main;
import nz.ac.auckland.se281.engine.GameState;

public class AiFactory {

  public static Ai createAi(Main.Difficulty difficulty, GameState gameState) {
    // Creates an AI instance according to the difficulty selected by the user.
    switch (difficulty) {
      case EASY:
        return new EasyAi();
      case MEDIUM:
        return new MediumAi(gameState);
      case HARD:
        return new HardAi(gameState);
    }
    return null;
  }
}
