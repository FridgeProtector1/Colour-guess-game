package nz.ac.auckland.se281.engine.ai.gamestrategy;

import nz.ac.auckland.se281.engine.Stats;
import nz.ac.auckland.se281.model.Colour;

public interface GameStrategy {

  Colour chooseColour(Stats stats);
}
