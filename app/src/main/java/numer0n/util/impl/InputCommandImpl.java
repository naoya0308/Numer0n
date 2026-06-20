package numer0n.util.impl;

import java.util.Scanner;
import static numer0n.constant.GameConfig.*;
import numer0n.constant.MessageConfig;
import numer0n.util.InputCommand;

public class InputCommandImpl implements InputCommand {
    private String text;

    private boolean isEmpty;

    private boolean isError;

    private Scanner scanner;

    public InputCommandImpl() {
        this.scanner = new Scanner(System.in);

        this.reset();
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public boolean isError() {
        return isError;
    }

    @Override
    public void input(String message) {
        this.reset();

        while (true) {
            System.out.print(message);

            this.text = this.scanner.nextLine();

            this.validateText();

            if (!this.isEmpty && !this.isError) {
                break;
            }

            System.out.println(MessageConfig.INVALID_INPUT_MESSAGE);
        }
    }

    private void validateText() {
        if (this.text == null || this.text.isEmpty()) {
            this.isEmpty = true;
            return;
        }

        if (this.text.length() == 1) {
            this.isError = !this.text.matches("^[a-z]$");
        } else if (this.text.length() == NUMBER_DIGITS) {
            this.isError = !this.text.matches("^[0-9]{3}$");
        } else {
            this.isError = true;
        }
    }

    private void reset() {
        this.text = "";
        this.isEmpty = false;
        this.isError = false;
    }
}
