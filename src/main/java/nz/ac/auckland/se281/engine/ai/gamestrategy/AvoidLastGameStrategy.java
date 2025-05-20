package nz.ac.auckland.se281.engine.ai.gamestrategy;

import nz.ac.auckland.se281.engine.Stats;
import nz.ac.auckland.se281.model.Colour;

public class AvoidLastGameStrategy implements GameStrategy {
  @Override
  public Colour chooseColour(Stats stats) {
    return Colour.getRandomColourExcluding(stats.getLastColour());
  }
}
