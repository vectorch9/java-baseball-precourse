package baseball.support;

import java.util.List;

import baseball.domain.NumberGenerator;
import baseball.domain.Numbers;

public class StubNumberGenerator implements NumberGenerator {

    private final List<Integer> numbers;

    public StubNumberGenerator(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Numbers generate() {
        return new Numbers(numbers);
    }
}
