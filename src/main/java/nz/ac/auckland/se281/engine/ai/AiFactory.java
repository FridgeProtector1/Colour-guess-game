package nz.ac.auckland.se281.engine.ai;

import nz.ac.auckland.se281.Main;

public class AiFactory {

    public static Ai createAi(Main.Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return new EasyAi();

            case MEDIUM:

            case HARD:

            default:
        }

        return null;
    }

}
