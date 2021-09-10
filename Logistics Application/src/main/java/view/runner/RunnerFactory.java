package view.runner;

import view.operation.IIO;
import view.operation.IO;

public class RunnerFactory extends AbstractRunnerFactory {
    private final IIO io;

    public RunnerFactory() {
        io = new IO();
    }

    @Override
    public IUserInput createUserInput() {
        return new UserInput(io);
    }

    @Override
    public IMessage createMessage() {
        return new Message(io);
    }
}
