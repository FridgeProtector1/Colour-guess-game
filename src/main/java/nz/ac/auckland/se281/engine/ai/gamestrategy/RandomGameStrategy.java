package nz.ac.auckland.se281.engine.ai.gamestrategy;

import nz.ac.auckland.se281.model.Colour;

public class RandomGameStrategy implements GameStrategy {

  @Override
  public Colour chooseColour() {
    return Colour.getRandomColourForAi();
  }
}
