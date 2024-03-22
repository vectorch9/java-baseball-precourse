package baseball.domain;

public class Judgement {

    private final int strikeCount;

    private final int ballCount;

    private Judgement(int strikeCount, int ballCount) {
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
        validateNonNegative();
    }

    private void validateNonNegative() {
        if (strikeCount < 0 || ballCount < 0) {
            throw new IllegalArgumentException("Count cannot be negative.");
        }
    }

    public static Judgement judgeOf(final Numbers answer, final Numbers prediction) {
        return new Judgement(answer.countStrike(prediction), answer.countBall(prediction));
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isNothing() {
        return strikeCount == 0 && ballCount == 0;
    }

    public boolean hasBall() {
        return ballCount != 0;
    }

    public boolean hasStrike() {
        return strikeCount != 0;
    }

    public boolean hasWon() {
        return strikeCount == Numbers.LENGTH;
    }
}
