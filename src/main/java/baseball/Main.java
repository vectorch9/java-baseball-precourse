package baseball;

import baseball.controller.Controller;
import baseball.infra.RandomNumberGenerator;
import baseball.view.View;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new RandomNumberGenerator(), new View());
        controller.run();
    }
}
