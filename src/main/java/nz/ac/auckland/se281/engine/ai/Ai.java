package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.engine.Game;
import nz.ac.auckland.se281.engine.Stats;
import nz.ac.auckland.se281.engine.ai.gamestrategy.AvoidLastGameStrategy;
import nz.ac.auckland.se281.engine.ai.gamestrategy.GameStrategy;
import nz.ac.auckland.se281.engine.ai.gamestrategy.LeastUsedGameStrategy;
import nz.ac.auckland.se281.engine.ai.gamestrategy.RandomGameStrategy;
import nz.ac.auckland.se281.model.Colour;

public abstract class Ai {
  protected Colour ownColour;
  protected Colour guessColour;

  protected GameStrategy gameStrategy;
  protected GameStrategy randomGameStrategy;
  protected GameStrategy avoidLastGameStrategy;
  protected GameStrategy leastUsedGameStrategy;

  protected Stats stats;

  public Ai() {
    this.randomGameStrategy = new RandomGameStrategy();
    this.avoidLastGameStrategy = new AvoidLastGameStrategy();
    this.leastUsedGameStrategy = new LeastUsedGameStrategy();
    setStrategy(this.randomGameStrategy);
  }

  public Ai(Stats stats) {
    this();
    this.stats = stats;
  }

  public void chooseColours() {
    this.ownColour = gameStrategy.chooseColour(stats);
    this.guessColour = gameStrategy.chooseColour(stats);
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
