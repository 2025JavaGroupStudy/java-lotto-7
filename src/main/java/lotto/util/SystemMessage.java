package lotto.util;

public enum SystemMessage {
    START_MONEY_GUIDE("구입금액을 입력해 주세요."),
    RESULT_MONEY_GUIDE("%d개를 구매했습니다."),
    START_TARGETLIST_GUIDE("당첨 번호를 입력해 주세요."),
    START_BONUS_GUIDE("보너스 번호를 입력해 주세요."),
    RESULT_JACKPOT_GUIDE("당첨 통계\n---"),
    RESULT_PROFIT_GUIDE("총 수익률은 %.1f%%입니다.");

    private final String message;

    SystemMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
