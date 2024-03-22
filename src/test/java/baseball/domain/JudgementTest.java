package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;

import org.junit.jupiter.api.Test;

public class JudgementTest {

    @Test
    public void 스트라이크와_볼의_수를_계산하여_저장한다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(1, 3, 2));

        // when
        Judgement judgement = Judgement.judgeOf(answer, prediction);

        // then
        assertSoftly(soft -> {
            soft.assertThat(judgement.getStrikeCount()).isEqualTo(1);
            soft.assertThat(judgement.getBallCount()).isEqualTo(2);
        });
    }

    @Test
    public void 일치하는_수가_하나도_없다면_낫싱이다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(4, 5, 6));

        // when
        Judgement judgement = Judgement.judgeOf(answer, prediction);

        // then
        assertThat(judgement.isNothing()).isTrue();
    }

    @Test
    public void 스트라이크가_하나라도_있는지_확인한다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(1, 5, 6));

        // when
        Judgement judgement = Judgement.judgeOf(answer, prediction);

        // then
        assertThat(judgement.hasStrike()).isTrue();
    }

    @Test
    public void 스트라이크가_하나도_없는지_확인한다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(4, 1, 6));

        // when
        Judgement judgement = Judgement.judgeOf(answer, prediction);

        // then
        assertThat(judgement.hasStrike()).isFalse();
    }

    @Test
    public void 볼이_하나라도_있는지_확인한다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(4, 1, 6));

        // when
        Judgement judgement = Judgement.judgeOf(answer, prediction);

        // then
        assertThat(judgement.hasBall()).isTrue();
    }

    @Test
    public void 볼이_하나도_없는지_확인한다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(1, 5, 6));

        // when
        Judgement judgement = Judgement.judgeOf(answer, prediction);

        // then
        assertThat(judgement.hasBall()).isFalse();
    }

    @Test
    public void 스트라이크가_3개라면_이긴다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(1, 2, 3));

        // when
        Judgement judgement = Judgement.judgeOf(answer, prediction);

        // then
        assertThat(judgement.hasWon()).isTrue();
    }

    @Test
    public void 스트라이크가_3개가_아니면_이기지_못한다() {
        // given
        Numbers answer = new Numbers(List.of(1, 2, 3));
        Numbers prediction = new Numbers(List.of(1, 2, 4));

        // when
        Judgement judgement = Judgement.judgeOf(answer, prediction);

        // then
        assertThat(judgement.hasWon()).isFalse();
    }
}
