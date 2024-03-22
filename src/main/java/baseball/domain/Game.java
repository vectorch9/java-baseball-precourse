package baseball.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private final Numbers answer;

    public Game(final NumberGenerator numberGenerator) {
        this.answer = numberGenerator.generate();
    }

    public Judgement play(final String input) {
        final Numbers prediction = parseString(input);
        return Judgement.judgeOf(answer, prediction);
    }

    private Numbers parseString(final String input) {
        int dividend = Integer.parseInt(input);
        final List<Integer> numbers = new ArrayList<>();
        while (dividend > 0) {
            numbers.add(dividend % 10);
            dividend /= 10;
        }
        Collections.reverse(numbers);
        return new Numbers(numbers);
    }
}
