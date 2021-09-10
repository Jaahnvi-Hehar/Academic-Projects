package view.runner;

import view.operation.IIO;

public class UserInput implements IUserInput {
    private final IIO io;

    public UserInput(IIO io) {
        this.io = io;
    }

    @Override
    public String readCommand() {
        io.writeOutput("\nEnter command:");
        return io.readInput().trim().toLowerCase();
    }


    @Override
    public String readAdminPassword() {
        io.writeOutput("---Password---");
        return io.readInput();
    }

    @Override
    public String readCustomerEmailAddress()
    {
        io.writeOutput("---Email Address---");
        return io.readInput();
    }
}
