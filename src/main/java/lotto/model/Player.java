package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lotto.util.DetailErrorMessage;

public class Player {
    final int purchaseMoney;
    int profitMoney;
    List<Lotto> purchasedLottoList;

    public static Player setLottoMoney(int purchaseMoney) {
        if (purchaseMoney <= 0) {
            throw new IllegalArgumentException(DetailErrorMessage.ZERO_NEGATIVE.getMessage());
        }
        if (purchaseMoney % 1000 > 0) {
            throw new IllegalArgumentException(DetailErrorMessage.NOT_MULTIPLE.getMessage());
        }
        return new Player(purchaseMoney);
    }

    Player(int purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
        purchasedLottoList = new ArrayList<>();
    }

    public void buyLotto() {
        int howMuch = purchaseMoney / 1000;
        IntStream.range(0, howMuch).forEach((i) -> {
            purchasedLottoList.add(Lotto.buyNew());
        });
    }

    public List<Lotto> getPurchasedLottoList() {
        return purchasedLottoList;
    }

    public int getPurchasedLottoNum() {
        return purchasedLottoList.size();
    }

    public void setProfitMoney(int profitMoney) {
        this.profitMoney = profitMoney;
    }

    public float getProfitPercent() {
        return (float) (profitMoney * 100) / purchaseMoney;
    }
}
