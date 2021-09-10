package databaseLayer.customer;

import databaseLayer.connection.LogisticsDatabaseConnection;

public abstract class AbstractCustomerDatabaseFactory {
    private static AbstractCustomerDatabaseFactory uniqueInstance = null;

    public static AbstractCustomerDatabaseFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new CustomerDatabaseFactory(new LogisticsDatabaseConnection());
        }
        return uniqueInstance;
    }

    public static void setUniqueInstance(AbstractCustomerDatabaseFactory instance) {
        uniqueInstance = instance;
    }

    public abstract IInfoSelector createInfoSelector();

    public abstract IInfoUpdator createInfoUpdator();

    public abstract IInfoInsertor createInfoInsertor();
}
