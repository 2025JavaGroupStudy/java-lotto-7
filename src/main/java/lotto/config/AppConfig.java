package lotto.config;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.LottoView;

public class AppConfig {
    private static final LottoView lottoView = new LottoView();
    private static final LottoService lottoService = new LottoService();

    public static LottoController getLottoController() {
        return new LottoController(lottoView, lottoService);
    }
}
