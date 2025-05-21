package nz.ac.auckland.se281.engine.ai.gamestrategy;

import java.util.HashMap;
import nz.ac.auckland.se281.engine.GameState;
import nz.ac.auckland.se281.model.Colour;

public class LeastUsedGameStrategy implements GameStrategy {
  @Override
  public Colour chooseColour(GameState gameState) {

    HashMap<Colour, Integer> colourUsagesMap = gameState.getColourUsagesMap();
    int min = Integer.MAX_VALUE;
    Colour leastUsedColour = null;
    // Loops in the order that the COLOUR enums canonical order  if there is a tie in the least used
    // colour, then the colour that comes first in canonical order by default.
    for (Colour colour : Colour.values()) {
      int count = colourUsagesMap.getOrDefault(colour, 0);
      // Can't be less than previous colour when there is a tie
      if (count < min) {
        min = count;
        leastUsedColour = colour;
      }
    }
    return leastUsedColour;
  }
}
