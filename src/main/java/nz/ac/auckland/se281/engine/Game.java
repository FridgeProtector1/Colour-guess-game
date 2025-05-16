package nz.ac.auckland.se281.engine;
import nz.ac.auckland.se281.cli.MessageCli;

import nz.ac.auckland.se281.Main.Difficulty;

public class Game {
  public static String AI_NAME = "HAL-9000";
  int totalRounds;
  int currentRound;



  public Game() {}

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    String namePlayer = options[0];
    MessageCli.WELCOME_PLAYER.printMessage(namePlayer);
    this.totalRounds = numRounds;
    this.currentRound = 0;

  }

  public void play() {
    if (currentRound < totalRounds) {
      currentRound++;
      MessageCli.START_ROUND.printMessage(currentRound, totalRounds);
    } else {
      MessageCli.PRINT_END_GAME.printMessage();
    }

  }

  public void showStats() {}
}
