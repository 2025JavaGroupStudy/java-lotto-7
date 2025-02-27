package lotto.model;

import java.util.Set;
import lotto.util.DetailErrorMessage;

public class Game {
    private Set<Integer> jackpotNumbers;
    private Integer bonus;

    Game(Set<Integer> jackpotNumbers) {
        this.jackpotNumbers = jackpotNumbers;
    }

    //static factory pattern
    public static Game setJackpot(Set<Integer> jackpotNumbers) {
        return new Game(jackpotNumbers);
    }

    public void setBonus(int bonus) {
        if (jackpotNumbers.contains(bonus)) {
            throw new IllegalArgumentException(DetailErrorMessage.DUPLICATED.getMessage());
        }
        this.bonus = bonus;
    }

    public Set<Integer> getJackpotNumbers() {
        return jackpotNumbers;
    }

    public Integer getBonus() {
        return bonus;
    }
}
