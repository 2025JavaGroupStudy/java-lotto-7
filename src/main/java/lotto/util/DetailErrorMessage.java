package lotto.util;

public enum DetailErrorMessage {
    BLANK("빈칸 입력됨"),
    NOT_NUMBER("수 외의 문자 입력됨"),
    NOT_ALPHABET("알파벳 외의 문자 입력됨"),
    NOT_COMMA(", 외의 구분자 입력됨"),
    NOT_LENGTH("6개 아님"),
    NOT_RANGE("1부터 45까지의 숫자 아님"),
    DUPLICATED("중복된 번호 입력됨"),
    NOT_MULTIPLE("1000의 배수 아님"),
    ZERO_NEGATIVE("0 또는 음수 입력됨"),
    DEV_TYPE_WRONG("개발 오류, 지원되지 않는 자료형입니다.");

    private String message;

    DetailErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return " : " + message;
    }
}
