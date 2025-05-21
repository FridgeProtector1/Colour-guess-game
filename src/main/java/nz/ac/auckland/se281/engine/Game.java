package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.cli.Utils;
import nz.ac.auckland.se281.engine.ai.Ai;
import nz.ac.auckland.se281.engine.ai.AiFactory;
import nz.ac.auckland.se281.model.Colour;

public class Game {
  public static final String aiName = "HAL-9000";
  private String playerName;
  private Ai ai;
  private GameState gameState;

  public int addScore(
      Colour guessColour, Colour otherPlayersColour, Colour powerColour, boolean isPlayer) {
    int points = 0;

    // Assigns the amount of points that are earned if the guessed colour is correct and additional
    // points if it is also a power colour
    if (guessColour == otherPlayersColour) {
      points = 1;
      if (guessColour == powerColour) {
        points = 3;
      }
    }
    // Determines who to distribute the points to.
    if (isPlayer) {
      this.gameState.setPlayerScore(points);
    } else {
      this.gameState.setAiScore(points);
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
    // gameSate is only null when newGame() hasn't been called since it is made in newGame()
    if (gameState == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    MessageCli.START_ROUND.printMessage(gameState.getCurrentRound(), gameState.getTotalRounds());

    MessageCli.ASK_HUMAN_INPUT.printMessage();
    String[] parts = Utils.scanner.nextLine().trim().split("\\s+");

    // continually prompts the user to input valid colours, until they input valid the valid colours
    while (parts.length != 2
        || Colour.fromInput(parts[0]) == null
        || Colour.fromInput(parts[1]) == null) {
      MessageCli.INVALID_HUMAN_INPUT.printMessage();
      parts = Utils.scanner.nextLine().trim().split("\\s+");
    }

    // Determines the power colour for this round, only every 3 rounds.
    Colour powerColour = null;
    if (gameState.getCurrentRound() % 3 == 0) {
      powerColour = Colour.getRandomColourForPowerColour();
      MessageCli.PRINT_POWER_COLOUR.printMessage(powerColour);
    }
    Colour ownColour = Colour.fromInput(parts[0]);
    Colour guessColour = Colour.fromInput(parts[1]);

    ai.chooseColours();
    ai.aiConfirmMessage();
    gameState.setLastColour(ownColour);
    MessageCli.PRINT_INFO_MOVE.printMessage(this.playerName, ownColour, guessColour);

    MessageCli.PRINT_OUTCOME_ROUND.printMessage(
        this.playerName, addScore(guessColour, ai.getOwnColour(), powerColour, true));
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(
        aiName, addScore(ai.getGuessColour(), ownColour, powerColour, false));
    gameState.setCurrentRound();

    if (gameState.getCurrentRound() > gameState.getTotalRounds()) {
      showStats();
    }
  }

  public void showStats() {
    // gameSate is only null when newGame() hasn't been called since it is made in newGame()
    if (gameState == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    MessageCli.PRINT_PLAYER_POINTS.printMessage(playerName, gameState.getPlayerScore());
    MessageCli.PRINT_PLAYER_POINTS.printMessage(aiName, gameState.getAiScore());
    MessageCli.PRINT_END_GAME.printMessage();

    // Determines who won, or if there was a tie
    if (gameState.getPlayerScore() > gameState.getAiScore()) {
      MessageCli.PRINT_WINNER_GAME.printMessage(playerName);
    } else if (gameState.getAiScore() > gameState.getPlayerScore()) {
      MessageCli.PRINT_WINNER_GAME.printMessage(aiName);
    } else {
      MessageCli.PRINT_TIE_GAME.printMessage();
    }
  }
}
