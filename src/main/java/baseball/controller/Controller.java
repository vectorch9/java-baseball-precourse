package baseball.controller;

import java.util.function.Supplier;

import baseball.domain.Game;
import baseball.domain.Judgement;
import baseball.domain.NumberGenerator;
import baseball.view.View;

public class Controller {

    private final int MAX_TRY = 3;

    private final NumberGenerator numberGenerator;
    private final View view;

    public Controller(final NumberGenerator numbersGenerator, final View view) {
        this.numberGenerator = numbersGenerator;
        this.view = view;
    }

    public void run() {
        view.printForStart();
        do {
            playGame();
        } while (view.printAndReadForRestart());
    }

    private void playGame() {
        final Game game = new Game(numberGenerator);
        Judgement judgement;
        do {
            judgement = retry(() -> readAndJudge(game));
            view.printForJudgement(judgement);
        } while (!judgement.hasWon());
        view.printForWin();
    }

    private Judgement readAndJudge(final Game game) {
        final String input = view.printAndReadForPrediction();
        return game.play(input);
    }

    private <T> T retry(final Supplier<T> supplier) {
        for (int time = 1; time <= MAX_TRY; time++) {
            try {
                return supplier.get();
            } catch (RuntimeException exception) {
                view.printException(exception);
            }
        }
        throw new IllegalStateException("Exceed max try.");
    }
}
