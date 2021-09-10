package databaseLayer.admin;

public abstract class AbstractAdminFactory {
    private static AbstractAdminFactory uniqueInstance = null;

    public static AbstractAdminFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new AdminFactory();
        }
        return uniqueInstance;
    }

    public static void setUniqueInstance(AbstractAdminFactory instance) {
        uniqueInstance = instance;
    }

    public abstract IAdminPasswordConfig createAdminPasswordConfig();
}
