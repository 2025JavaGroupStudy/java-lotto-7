package lotto.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    private final static int VALIDATE_LENGTH = 6;

    //여기서는 number밖에 안썼지만 확장성을 고려해 설계해봤습니다.
    public enum dataType {NUMBER, ALPHABET;}

    public static <T> List<T> isMultipleInputType(String inputLine, dataType dataType, String separator) {
        isBlank(inputLine);

        List<String> process = List.of(splitter(inputLine, separator));
        isLength(process.size());

        return process.stream()
                .map(value -> isSingleInputType(value, dataType))
                .map(element -> (T) element)
                .toList();
    }

    public static <T> T isSingleInputType(String inputLine, dataType dataType) {
        isBlank(inputLine);
        if (dataType == Validator.dataType.ALPHABET) {
            isAlphabet(inputLine);
            return (T) inputLine;
        }
        if (dataType == Validator.dataType.NUMBER) {
            isNumber(inputLine);
            return (T) Integer.valueOf(inputLine);
        }
        throw new IllegalArgumentException(DetailErrorMessage.DEV_TYPE_WRONG.getMessage());
    }

    public static <T> Set<T> isListItemDuplicated(List<T> inputList) {
        Set<T> glossary = new HashSet<>();
        for (T item : inputList) {
            if (!glossary.add(item)) {
                throw new IllegalArgumentException(DetailErrorMessage.DUPLICATED.getMessage());
            }
        }
        return glossary;
    }

    public static void isListItemInRange(int startRange, int endRangeIncluded, List<Integer> inputList) {
        for (int item : inputList) {
            if (item > endRangeIncluded || item < startRange) {
                throw new IllegalArgumentException(DetailErrorMessage.NOT_RANGE.getMessage());
            }
        }
    }

    public static void isBlank(String inputLine) {
        if (inputLine.isBlank()) {
            throw new IllegalArgumentException(DetailErrorMessage.BLANK.getMessage());
        }
    }

    public static void isNumber(String inputLine) {
        if (!inputLine.matches("\\d+")) {
            throw new NumberFormatException(DetailErrorMessage.NOT_NUMBER.getMessage());
        }
    }

    public static String isAlphabet(String inputLine) {
        if (!inputLine.matches("/\\^[a-zA-Z ]*\\$/")) {
            throw new IllegalArgumentException(DetailErrorMessage.NOT_ALPHABET.getMessage());
        }
        return inputLine;
    }

    public static void isLength(int length) {
        if (length != VALIDATE_LENGTH) {
            throw new IllegalArgumentException(DetailErrorMessage.NOT_LENGTH.getMessage());
        }
    }

    static String[] splitter(String inputLine, String separator) {
        try {
            return inputLine.split(separator);
        } catch (Error e) {
            throw new IllegalArgumentException(DetailErrorMessage.NOT_COMMA.getMessage());
        }
    }
}
