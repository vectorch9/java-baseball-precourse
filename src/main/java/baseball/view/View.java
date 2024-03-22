package baseball.view;

import java.util.Scanner;

import baseball.domain.Judgement;

public class View {

    private static final String START_MESSAGE = "숫자 야구 게임을 시작합니다.";
    private static final String RESTART_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 다른 수를 입력하세요.";
    private static final String PREDICT_MESSAGE = "숫자를 입력해주세요 : ";
    private static final String BALL_MESSAGE = "%d볼 ";
    private static final String STRIKE_MESSAGE = "%d스트라이크";
    private static final String NOTHING_MESSAGE = "낫싱";
    private static final String WIN_MESSAGE = "3개의 숫자를 모두 맞히셨습니다!";
    private static final String ERROR_PREFIX = "[ERROR] ";

    private final Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public void printForStart() {
        System.out.println(START_MESSAGE);
    }

    public boolean printAndReadForRestart() {
        System.out.println(RESTART_MESSAGE);
        int input = scanner.nextInt();
        return input == 1;
    }

    public String printAndReadForPrediction() {
        System.out.print(PREDICT_MESSAGE);
        return scanner.next();
    }

    public void printForJudgement(final Judgement judgement) {
        if (judgement.isNothing()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        if (judgement.hasBall()) {
            System.out.printf(BALL_MESSAGE, judgement.getBallCount());
        }
        if (judgement.hasStrike()) {
            System.out.printf(STRIKE_MESSAGE, judgement.getStrikeCount());
        }
        System.out.println();
    }

    public void printForWin() {
        System.out.println(WIN_MESSAGE);
    }

    public void printException(Exception exception) {
        System.out.print(ERROR_PREFIX);
        System.out.println(exception.getMessage());
    }
}
