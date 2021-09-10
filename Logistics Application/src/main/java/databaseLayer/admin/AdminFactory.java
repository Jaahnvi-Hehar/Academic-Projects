package databaseLayer.admin;

import databaseLayer.connection.LogisticsDatabaseConnection;

public class AdminFactory extends AbstractAdminFactory {

    @Override
    public IAdminPasswordConfig createAdminPasswordConfig() {
        return new AdminPasswordConfig(new LogisticsDatabaseConnection());
    }
}
