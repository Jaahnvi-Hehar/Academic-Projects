package view.customer;

import view.operation.IIO;

public class InvalidValue implements IInvalidValue {
    private final IIO io;

    public InvalidValue(IIO io) {
        this.io = io;
    }


    @Override
    public String getReentryChoice() {
        io.writeOutput("Bad Value!");
        io.writeOutput("--- Would you like to re-enter values? (Y/N) ---");
        return io.readInput().toLowerCase();
    }


    @Override
    public void printInvalid(String field, String value) {
        io.writeOutput("Invalid " + field + ": " + value + "!");
    }
}
