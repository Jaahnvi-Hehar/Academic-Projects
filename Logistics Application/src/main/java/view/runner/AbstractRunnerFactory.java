package view.runner;

public abstract class AbstractRunnerFactory {
    private static AbstractRunnerFactory uniqueInstance = null;

    public static AbstractRunnerFactory instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new RunnerFactory();
        }
        return uniqueInstance;
    }

    public static void setUniqueInstance(AbstractRunnerFactory instance) {
        uniqueInstance = instance;
    }

    public abstract IUserInput createUserInput();

    public abstract IMessage createMessage();
}
