package nz.ac.auckland.se281.engine.ai.gamestrategy;

import nz.ac.auckland.se281.engine.GameState;
import nz.ac.auckland.se281.model.Colour;

public interface GameStrategy {

  Colour chooseColour(GameState gameState);
}
