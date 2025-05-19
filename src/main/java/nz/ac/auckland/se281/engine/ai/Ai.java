package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.engine.Game;
import nz.ac.auckland.se281.engine.ai.gamestrategy.GameStrategy;
import nz.ac.auckland.se281.model.Colour;

public abstract class Ai {
  protected Colour ownColour;
  protected Colour guessColour;
  protected GameStrategy gameStrategy;
  private int score = 0;

  public void chooseColour() {
    this.ownColour = gameStrategy.chooseColour();
    this.guessColour = gameStrategy.chooseColour();
  }

  public Colour getOwnColour() {
    return ownColour;
  }

  public Colour getGuessColour() {
    return guessColour;
  }

  public void setStrategy(GameStrategy gameStrategy) {
    this.gameStrategy = gameStrategy;
  }

  public void AiConfirmMessage() {
    MessageCli.PRINT_INFO_MOVE.printMessage(Game.AI_NAME, ownColour, guessColour);
  }
}
