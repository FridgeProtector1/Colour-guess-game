package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.cli.MessageCli;

import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.Utils;
import nz.ac.auckland.se281.engine.ai.Ai;
import nz.ac.auckland.se281.engine.ai.AiFactory;
import nz.ac.auckland.se281.model.Colour;

public class Game {
  public static final String AI_NAME = "HAL-9000";
  private String playerName;
  private Ai ai;
  private GameState gameState;

  public Game() {}

  public int addScore(
      Colour guessColour, Colour otherPlayersColour, Colour powerColour, boolean isPlayer) {
    int points = 0;

    if (guessColour == otherPlayersColour) {
      points = 1;
      if (guessColour == powerColour) {
        points = 3;
      }
    }
    if (isPlayer) {
      this.gameState.setPlayerScore(points);
    } else {
      this.gameState.setAIScore(points);
    }
    return points;
  }

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    this.gameState = new GameState();
    this.playerName = options[0];
    this.gameState.setTotalRounds(numRounds);
    this.ai = AiFactory.createAi(difficulty, gameState);

    MessageCli.WELCOME_PLAYER.printMessage(this.playerName);
  }

  public void play() {
    if (gameState.getCurrentRound() > gameState.getTotalRounds()) {
      MessageCli.PRINT_END_GAME.printMessage();
    }
    MessageCli.START_ROUND.printMessage(gameState.getCurrentRound(), gameState.getTotalRounds());

    MessageCli.ASK_HUMAN_INPUT.printMessage();
    String[] parts = Utils.scanner.nextLine().trim().split("\\s+");

    while (parts.length != 2
        || Colour.fromInput(parts[0]) == null
        || Colour.fromInput(parts[1]) == null) {
      MessageCli.INVALID_HUMAN_INPUT.printMessage();
      parts = Utils.scanner.nextLine().trim().split("\\s+");
    }
    Colour powerColour = null;
    if (gameState.getCurrentRound() % 3 == 0) {
      powerColour = Colour.getRandomColourForPowerColour();
      MessageCli.PRINT_POWER_COLOUR.printMessage(powerColour);
    }
    Colour ownColour = Colour.fromInput(parts[0]);
    Colour guessColour = Colour.fromInput(parts[1]);

    ai.chooseColours();
    ai.AiConfirmMessage();
    gameState.setLastColour(ownColour);
    MessageCli.PRINT_INFO_MOVE.printMessage(this.playerName, ownColour, guessColour);

    MessageCli.PRINT_OUTCOME_ROUND.printMessage(
        this.playerName, addScore(guessColour, ai.getOwnColour(), powerColour, true));
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(
        Game.AI_NAME, addScore(ai.getGuessColour(), ownColour, powerColour, false));
    gameState.setCurrentRound();
  }

  public void showStats() {}
}
