package baseball.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import baseball.domain.NumberGenerator;
import baseball.domain.Numbers;

public class RandomNumberGenerator implements NumberGenerator {

    private final Random random;

    public RandomNumberGenerator() {
        long seed = System.currentTimeMillis();
        this.random = new Random(seed);
    }

    @Override
    public Numbers generate() {
        final List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < Numbers.LENGTH) {
            final int pickedNumber = random.nextInt(Numbers.MAX_INT - 1) + 1;
            if (!numbers.contains(pickedNumber)) {
                numbers.add(pickedNumber);
            }
        }
        return new Numbers(numbers);
    }
}
