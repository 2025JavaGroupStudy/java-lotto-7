package lotto.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import lotto.model.Game;
import lotto.model.Player;
import lotto.util.GamePrize;

public class LottoService {
    private Game game;
    private Player player;
    private static final int PRIZE_LENGTH = GamePrize.values().length;

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int[] calculateLottoResultType() {
        int[] resultList = new int[PRIZE_LENGTH];
        player.getPurchasedLottoList().forEach(lotto -> updateResultList(resultList, lotto.getNumbers()));
        return resultList;
    }

    public float calculateLottoResultProfit(int[] resultList) {
        int[] prizeMoneyList = Arrays.stream(GamePrize.values()).mapToInt(GamePrize::getPrizeMoney).toArray();
        int earnedMoney = IntStream.range(0, PRIZE_LENGTH).map(i -> resultList[i] * prizeMoneyList[i]).sum();
        player.setProfitMoney(earnedMoney);
        return player.getProfitPercent();
    }

    void updateResultList(int[] resultList, List<Integer> singleLotto) {
        if (checkPrizeIndex(singleLotto) > -1) {
            resultList[checkPrizeIndex(singleLotto)]++;
        }
    }

    int checkPrizeIndex(List<Integer> singleLotto) {
        Set<Integer> jackpotNumbers = game.getJackpotNumbers();
        //일치하는 로또 공 개수 셈
        int matchingLottoBall = (int) singleLotto.stream().filter(jackpotNumbers::contains).count();
        if (matchingLottoBall == 6) {
            if (hasBonusMatch(singleLotto)) {
                return GamePrize.PRIZE_2ND.getIndex();
            }
            return GamePrize.PRIZE_1ST.getIndex();
        }
        return matchingLottoBall - 3;
    }

    boolean hasBonusMatch(List<Integer> singleLotto) {
        int bonus = game.getBonus();
        return singleLotto.contains(bonus);
    }
}
