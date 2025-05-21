package nz.ac.auckland.se281.engine.ai.gamestrategy;

import nz.ac.auckland.se281.engine.Stats;
import nz.ac.auckland.se281.model.Colour;

import java.util.HashMap;

public class LeastUsedGameStrategy implements GameStrategy {
  public Colour chooseColour(Stats stats) {
    HashMap<Colour, Integer> colourUsagesMap = stats.getColourUsagesMap();
    int min = Integer.MAX_VALUE;
    Colour leastUsedColour = null;
    for (Colour colour : Colour.values()) {
      int count = colourUsagesMap.getOrDefault(colour, 0);
      if (count < min) {
        min = count;
        leastUsedColour = colour;
      }
    }
    return leastUsedColour;
  }
}
