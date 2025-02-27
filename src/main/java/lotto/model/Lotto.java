package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.util.Validator;

public class Lotto {
    private final List<Integer> numbers;
    static final int LOTTO_RANGE_START = 1;
    static final int LOTTO_RANGE_END = 45;
    static final int LOTTO_BALL_COUNT = 6;

    public Lotto(List<Integer> numbers) {
        Validator.isLength(numbers.size());
        Validator.isListItemDuplicated(numbers);
        this.numbers = numbers;
    }

    public static Lotto buyNew() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(LOTTO_RANGE_START, LOTTO_RANGE_END, LOTTO_BALL_COUNT));
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static int getLottoRangeStart() {
        return LOTTO_RANGE_START;
    }

    public static int getLottoRangeEnd() {
        return LOTTO_RANGE_END;
    }
}
