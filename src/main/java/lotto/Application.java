package lotto;

import lotto.config.AppConfig;
import lotto.controller.LottoController;

public class Application {
    static LottoController lottoController;

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        lottoController = AppConfig.getLottoController();
        lottoController.startGame();
    }
}
