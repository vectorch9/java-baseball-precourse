package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumbersTest {

    @Test
    public void Number는_3개의_1에서_9사이의_정수를_가진다() {
        // given
        List<Integer> integers = List.of(1, 2, 3);

        // when & then
        assertThatCode(() -> new Numbers(integers))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 4, 5 })
    public void Numbers는_길이가_3이_아니면_예외를_던진다(int size) {
        // given
        List<Integer> integers = generateIntegers(size);

        // when & then
        assertThatThrownBy(() -> new Numbers(integers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<Integer> generateIntegers(int size) {
        return Stream.iterate(1, n -> n + 1).limit(size).collect(Collectors.toList());
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 10 })
    public void Numbers는_1에서_9사이의_숫자가_아니면_예외를_던진다(int number) {
        // given
        List<Integer> integers = List.of(1, 2, number);

        // when & then
        assertThatThrownBy(() -> new Numbers(integers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void Number는_중복된_숫자를_가질_수_없다() {
        // given
        List<Integer> integers = List.of(1, 1, 3);

        // when & then
        assertThatThrownBy(() -> new Numbers(integers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 스트라이크는_위치와_숫자가_모두_일치하는_수의_개수다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(1, 2, 4));

        // when
        int count = answer.countStrike(prediction);

        // then
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void 볼은_위치가_다르고_숫자가_일치하는_수의_개수다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(1, 3, 2));

        // when
        int count = answer.countBall(prediction);

        // then
        assertThat(count).isEqualTo(2);
    }
}
