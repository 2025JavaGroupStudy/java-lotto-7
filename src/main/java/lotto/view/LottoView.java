package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.IntStream;
import lotto.util.GamePrize;
import lotto.util.SystemMessage;

public class LottoView {

    public String inputPurchaseMoney() {
        System.out.println(SystemMessage.START_MONEY_GUIDE.getMessage());
        return Console.readLine();
    }

    public String inputJackpotNumber() {
        System.out.println(SystemMessage.START_TARGETLIST_GUIDE.getMessage());
        return Console.readLine();
    }

    public String inputBonusNumber() {
        System.out.println(SystemMessage.START_BONUS_GUIDE.getMessage());
        return Console.readLine();
    }

    public void outputPurchasedLottoNum(int lottoNum) {
        System.out.println(String.format(SystemMessage.RESULT_MONEY_GUIDE.getMessage(), lottoNum));
    }

    public void outputPurchasedLottoDetail(List<Integer> lotto) {
        System.out.println(lotto);
    }

    public void outputLottoResult(int[] resultList, float profitPercent) {
        GamePrize[] prizes = GamePrize.values();
        System.out.println(SystemMessage.RESULT_JACKPOT_GUIDE.getMessage());
        IntStream.range(0, prizes.length)
                .forEach(i -> System.out.println(prizes[i].getMessage() + resultList[i] + "개"));
        System.out.println(String.format(SystemMessage.RESULT_PROFIT_GUIDE.getMessage(), profitPercent));
    }
}
