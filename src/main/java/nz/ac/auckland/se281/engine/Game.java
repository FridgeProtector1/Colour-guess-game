package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.cli.MessageCli;

import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.Utils;
import nz.ac.auckland.se281.engine.ai.Ai;
import nz.ac.auckland.se281.engine.ai.AiFactory;
import nz.ac.auckland.se281.model.Colour;

public class Game {
  public static String AI_NAME = "HAL-9000";
  private String playerName;
  private Ai ai;
  private Stats stats;

  public Game() {}

  public int addScore(
      Colour guessColour, Colour otherPlayersColour, Colour powerColour, boolean isPlayer) {
    if (guessColour != otherPlayersColour) {
      return 0;
    }

    int points = 1;
    if (guessColour == powerColour) {
      points = 3;
    }

    if (isPlayer) {
      this.stats.setPlayerScore(points);
    } else {
      this.stats.setAIScore(points);
    }
    return points;
  }

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    this.stats = new Stats();
    this.playerName = options[0];
    MessageCli.WELCOME_PLAYER.printMessage(this.playerName);
    this.stats.setTotalRounds(numRounds);
    this.ai = AiFactory.createAi(difficulty);
  }

  public void play() {
    if (stats.getCurrentRound() > stats.getTotalRounds()) {
      MessageCli.PRINT_END_GAME.printMessage();
    }
    MessageCli.START_ROUND.printMessage(stats.getCurrentRound(), stats.getTotalRounds());

    MessageCli.ASK_HUMAN_INPUT.printMessage();
    String[] parts = Utils.scanner.nextLine().trim().split("\\s+");

    while (parts.length != 2
        || Colour.fromInput(parts[0]) == null
        || Colour.fromInput(parts[1]) == null) {
      MessageCli.INVALID_HUMAN_INPUT.printMessage();
      parts = Utils.scanner.nextLine().trim().split("\\s+");
    }
    boolean isPowerColourRnd = false;
    Colour powerColour = null;
    if (stats.getCurrentRound() % 3 == 0) {
      isPowerColourRnd = true;
      powerColour = Colour.getRandomColourForPowerColour();
      MessageCli.PRINT_POWER_COLOUR.printMessage(powerColour);
    }
    Colour ownColor = Colour.fromInput(parts[0]);
    Colour guessColour = Colour.fromInput(parts[1]);
    ai.chooseColours();
    ai.AiConfirmMessage();
    MessageCli.PRINT_INFO_MOVE.printMessage(this.playerName, ownColor, guessColour);

    int playerEarnedPoints = 0;
    int aiEarnedPoints = 0;

    playerEarnedPoints = addScore(guessColour, ai.getOwnColour(), powerColour, true);

    aiEarnedPoints = addScore(ai.getGuessColour(), ownColor, powerColour, false);

    MessageCli.PRINT_OUTCOME_ROUND.printMessage(this.playerName, playerEarnedPoints);
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(Game.AI_NAME, aiEarnedPoints);
    stats.setCurrentRound();
  }

  public void showStats() {}
}
