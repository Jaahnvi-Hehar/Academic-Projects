package databaseLayer.customer;

import databaseLayer.connection.ILogisticsDatabaseConnection;

public class CustomerDatabaseFactory extends AbstractCustomerDatabaseFactory {
    ILogisticsDatabaseConnection databaseConnection;

    public CustomerDatabaseFactory(ILogisticsDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public IInfoSelector createInfoSelector() {
        return new InfoSelector(databaseConnection);
    }

    @Override
    public IInfoUpdator createInfoUpdator() {
        return new InfoUpdator(databaseConnection);
    }

    @Override
    public IInfoInsertor createInfoInsertor() {
        return new InfoInsertor(databaseConnection);
    }
}
