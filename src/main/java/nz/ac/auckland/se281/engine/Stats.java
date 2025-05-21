package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.model.Colour;

import java.util.HashMap;

public class Stats {
  private int totalRounds;
  private int currentRound = 1;
  private int playerScore = 0;
  private int aiScore = 0;
  private HashMap<Colour, Integer> colourUsagesMap = new HashMap<>();

  private Colour lastColour;

  public HashMap<Colour, Integer> getColourUsagesMap() {
    return colourUsagesMap;
  }

  public void setLastColour(Colour lastColour) {
    this.lastColour = lastColour;
    colourUsagesMap.putIfAbsent(lastColour, 0);
    colourUsagesMap.put(lastColour, colourUsagesMap.get(lastColour) + 1);
  }

  public Colour getLastColour() {
    return lastColour;
  }

  public void setTotalRounds(int totalRounds) {
    this.totalRounds = totalRounds;
  }

  public void setCurrentRound() {
    this.currentRound++;
  }

  public void setPlayerScore(int playerScore) {
    this.playerScore = playerScore;
  }

  public void setAIScore(int aiScore) {
    this.aiScore = aiScore;
  }

  public int getTotalRounds() {
    return totalRounds;
  }

  public int getCurrentRound() {
    return currentRound;
  }

  public int getPlayerScore() {
    return playerScore;
  }

  public int getAIScore() {
    return aiScore;
  }
}
