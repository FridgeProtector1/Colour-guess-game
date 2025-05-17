package nz.ac.auckland.se281.engine;
import nz.ac.auckland.se281.cli.MessageCli;

import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.Utils;
import nz.ac.auckland.se281.engine.ai.Ai;
import nz.ac.auckland.se281.engine.ai.AiFactory;
import nz.ac.auckland.se281.engine.ai.EasyAi;
import nz.ac.auckland.se281.model.Colour;

public class Game {
  public static String AI_NAME = "HAL-9000";
  int totalRounds;
  int currentRound;
  public String playerName;
Ai ai;


  public Game() {}

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    this.playerName = options[0];
    MessageCli.WELCOME_PLAYER.printMessage(this.playerName);
    this.totalRounds = numRounds;
    this.currentRound = 1;
    this.ai = AiFactory.createAi(difficulty);

  }

  public void play() {
    if (currentRound > totalRounds) {
      MessageCli.PRINT_END_GAME.printMessage();
    }
    MessageCli.START_ROUND.printMessage(currentRound, totalRounds);


    MessageCli.ASK_HUMAN_INPUT.printMessage();
    String[] parts = Utils.scanner.nextLine().trim().split("\\s+");

    while (parts.length != 2 ||
            Colour.fromInput(parts[0]) == null ||
            Colour.fromInput(parts[1]) == null) {
      MessageCli.INVALID_HUMAN_INPUT.printMessage();
    parts = Utils.scanner.nextLine().trim().split("\\s+");
    }
    if (currentRound % 3 == 0){
      Colour powerColour = Colour.getRandomColourForPowerColour();
      MessageCli.PRINT_POWER_COLOUR.printMessage(powerColour);
    }
    Colour ownColor = Colour.fromInput(parts[0]);
    Colour guessColour = Colour.fromInput(parts[1]);
    ai.AiConfirmMessage();
    MessageCli.PRINT_INFO_MOVE.printMessage(this.playerName,ownColor, guessColour);



    currentRound++;
  }


  public void showStats() {}
}
