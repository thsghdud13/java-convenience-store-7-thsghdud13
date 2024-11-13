package store.view;

import store.exception.GeneralException;

public class OutputView {
    public static void print(String output) {
        System.out.println(output);
    }

    public static void printError(GeneralException exception) {
        System.out.println(exception.getErrorMessage());
    }
}
