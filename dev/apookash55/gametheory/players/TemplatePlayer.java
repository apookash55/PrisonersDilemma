package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.definition.Decision;
import dev.apookash55.gametheory.definition.Player;

/*
    Methods that you can use:
    getScore() : Returns the score you have currently
    getCurrentRound() : Returns the current round starting from 0 to total rounds - 1
    getLastRoundHistory() : Returns the last round data as an Array of Decision with first and second index
                            is your choice and other player's choice respectively
    getRoundsHistory() : Returns the full rounds' data as List of Array of Decision with first and second index
                            is your choice and other player's choice respectively
    NOTE : total rounds in not known to the player
*/
public class TemplatePlayer extends Player {


    /*
    Return Decision.COOPERATE or Decision.DEFECT
    NOTE: If null is returned then it will consider it as Decision.DEFECT
     */
    @Override
    public Decision makeDecision() {
        /*
        Add your logic here
         */
        return null;
    }
}
