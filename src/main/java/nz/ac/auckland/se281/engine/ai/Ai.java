package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.engine.Game;
import nz.ac.auckland.se281.engine.ai.gamestrategy.GameStrategy;
import nz.ac.auckland.se281.model.Colour;

public abstract class Ai {
  protected Colour ownColour;
  protected Colour guessColour;
  protected GameStrategy gameStrategy;

  public Colour chooseOwnColour() {
    this.ownColour = gameStrategy.chooseColour();
    return ownColour;
  }

  public Colour chooseGuessColour() {
    this.guessColour = gameStrategy.chooseColour();
    return guessColour;
  }

  public void setStrategy(GameStrategy gameStrategy) {
    this.gameStrategy = gameStrategy;
  }

  public void AiConfirmMessage() {
    MessageCli.PRINT_INFO_MOVE.printMessage(Game.AI_NAME, chooseOwnColour(), chooseGuessColour());
  }
}
