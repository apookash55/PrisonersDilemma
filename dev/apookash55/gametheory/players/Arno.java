package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.definition.Decision;
import dev.apookash55.gametheory.definition.Player;

import java.util.List;

public class Arno extends Player {

    boolean isEnemyTitforTat = false;
    int enemyRevengeCount = 0;
    int count = 0;

    @Override
    public Decision makeDecision() {
        List<Decision[]> history = getRoundsHistory();
        this.count = history.size();

        if(isEnemyTitforTat) {
            return Decision.COOPERATE;
        }
        checkTitForTat(history);
        if(count == 0) {
            return Decision.DEFECT;
        } else if(count == 1) {
            return Decision.COOPERATE;
        } else if(count > 22) {
            return Decision.DEFECT;
        } else {

            if(history.get(count-1)[1] == Decision.COOPERATE && history.get(count-2)[1] == Decision.COOPERATE) {
                return Decision.DEFECT;
            } else if(history.get(count-1)[1] == Decision.DEFECT) {
                return Decision.DEFECT;
            }
        }

        return Decision.COOPERATE;
    }

    void checkTitForTat(List<Decision[]> history) {

        if(count > 1) {
            if(history.get(count-1)[1] == Decision.DEFECT &&
                    history.get(count-2)[0] == Decision.DEFECT &&
                    history.get(count-1)[0] == Decision.COOPERATE &&
                    history.get(count-2)[1] == Decision.COOPERATE) {
                enemyRevengeCount++;
            }
            if(count >= 22 && enemyRevengeCount > 9) {
                isEnemyTitforTat = true;
            } else {
                isEnemyTitforTat = false;
            }
        }
    }
}

