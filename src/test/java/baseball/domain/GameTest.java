package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import baseball.support.StubNumberGenerator;

public class GameTest {

    @Test
    public void 게임은_매_회마다_판정을_계산한다() {
        // given
        List<Integer> answer = List.of(1, 2, 3);
        NumberGenerator numberGenerator = new StubNumberGenerator(answer);
        Game game = new Game(numberGenerator);

        // when
        Judgement judgement = game.play("456");

        // then
        assertThat(judgement.isNothing()).isTrue();
    }
}
