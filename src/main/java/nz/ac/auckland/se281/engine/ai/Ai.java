package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.engine.Game;
import nz.ac.auckland.se281.engine.GameState;
import nz.ac.auckland.se281.engine.ai.gamestrategy.AvoidLastGameStrategy;
import nz.ac.auckland.se281.engine.ai.gamestrategy.GameStrategy;
import nz.ac.auckland.se281.engine.ai.gamestrategy.LeastUsedGameStrategy;
import nz.ac.auckland.se281.engine.ai.gamestrategy.RandomGameStrategy;
import nz.ac.auckland.se281.model.Colour;

public abstract class Ai {
  protected Colour ownColour;
  protected Colour guessColour;

  protected GameStrategy guessColourStrategy;
  protected GameStrategy ownColourStrategy;

  protected GameStrategy randomGameStrategy;
  protected GameStrategy avoidLastGameStrategy;
  protected GameStrategy leastUsedGameStrategy;

  protected GameState gameState;

  public Ai() {
    this.randomGameStrategy = new RandomGameStrategy();
    this.avoidLastGameStrategy = new AvoidLastGameStrategy();
    this.leastUsedGameStrategy = new LeastUsedGameStrategy();
    this.ownColourStrategy = this.randomGameStrategy;
    setStrategy(this.randomGameStrategy);
  }

  public Ai(GameState gameState) {
    this();
    this.gameState = gameState;
  }

  public void chooseColours() {
    this.ownColour = ownColourStrategy.chooseColour(gameState);
    this.guessColour = guessColourStrategy.chooseColour(gameState);
  }

  public Colour getOwnColour() {
    return ownColour;
  }

  public Colour getGuessColour() {
    return guessColour;
  }

  public void setStrategy(GameStrategy gameStrategy) {
    this.guessColourStrategy = gameStrategy;
  }

  public void AiConfirmMessage() {
    MessageCli.PRINT_INFO_MOVE.printMessage(Game.AI_NAME, ownColour, guessColour);
  }
}
