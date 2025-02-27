package lotto.util;

public enum MajorErrorMessage {
    LOTTONUM_WRONG("로또 번호 입력이 잘못되었습니다."),
    MONEY_WRONG("로또 구입 금액 입력이 잘못되었습니다.");

    static final String errorHeader = "[ERROR]";
    private String message;

    MajorErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return errorHeader + ' ' + message;
    }
}
