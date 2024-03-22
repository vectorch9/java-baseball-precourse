package baseball.domain;

import java.util.List;

public class Numbers {

    public static final int LENGTH = 3;
    public static final int MAX_INT = 9;
    public static final int MIN_INT = 1;

    private final List<Integer> numbers;

    public Numbers(List<Integer> numbers) {
        this.numbers = numbers;
        validate();
    }

    private void validate() {
        validateLength();
        validateDuplication();
        validateEachInteger();
    }

    private void validateLength() {
        if (numbers.size() != LENGTH) {
            throw new IllegalArgumentException("Number length should be 3.");
        }
    }

    private void validateDuplication() {
        numbers.forEach(number -> {
            if (numbers.indexOf(number) != numbers.lastIndexOf(number)) {
                throw new IllegalArgumentException("Duplicated numbers.");
            }
        });
    }

    private void validateEachInteger() {
        numbers.forEach(number -> {
            if (number < MIN_INT || number > MAX_INT) {
                throw new IllegalArgumentException("Number should be greater than 0 and less than 10");
            }
        });

    }

    public int countStrike(final Numbers prediction) {
        int result = 0;
        for (int index = 0; index < LENGTH; index++) {
            if (this.containsAndExactlySameIndexOf(prediction.numbers.get(index), index)) {
                result += 1;
            }
        }
        return result;
    }

    private boolean containsAndExactlySameIndexOf(final int number, final int index) {
        return numbers.indexOf(number) == index;
    }

    public int countBall(final Numbers prediction) {
        int result = 0;
        for (int index = 0; index < LENGTH; index++) {
            if (this.containsAndNonSameIndexOf(prediction.numbers.get(index), index)) {
                result += 1;
            }
        }
        return result;
    }

    private boolean containsAndNonSameIndexOf(final int number, final int index) {
        return numbers.contains(number) && numbers.indexOf(number) != index;
    }
}
