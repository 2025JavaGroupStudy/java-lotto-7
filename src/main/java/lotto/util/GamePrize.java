package lotto.util;

public enum GamePrize {
    PRIZE_5TH(3, 5000, false, 0),
    PRIZE_4TH(4, 50000, false, 1),
    PRIZE_3RD(5, 1500000, false, 2),
    PRIZE_2ND(5, 30000000, true, 3),
    PRIZE_1ST(6, 2000000000, false, 4);

    private final int matchingBall;
    private final String formattedPrizeMoney;
    private final int prizeMoney;
    private final boolean isBonus;
    private final int index;
    private final String message;

    GamePrize(int matchingBall, int prizeMoney, boolean isBonus, int index) {
        this.matchingBall = matchingBall;
        this.prizeMoney = prizeMoney;
        //아래는 1000을 1,000과 같이 스트링 형식으로 바꾸어줌
        this.formattedPrizeMoney = String.format("%,d", prizeMoney);
        this.isBonus = isBonus;
        this.index = index;
        this.message = setMessage();
    }

    public String getMessage() {
        return message;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getIndex() {
        return index;
    }

    public String setMessage() {
        StringBuilder message = new StringBuilder();
        message.append(matchingBall).append("개 일치");
        if (isBonus) {
            message.append(", 보너스 볼 일치");
        }

        message.append(" ");

        message.append("(").append(formattedPrizeMoney).append("원)");
        message.append(" - ");
        return message.toString();
    }
}
