package databaseLayer.customer;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class InfoInsertor implements IInfoInsertor {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoInsertor.class);
    private final ILogisticsDatabaseConnection databaseConnection;

    public InfoInsertor(ILogisticsDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public boolean setCredentials(List<String> credentials) {
        String query = "Insert Into CustomerCredentials (EmailAddress, Password) Values (?,?);";
        try {
            PreparedStatement statement = databaseConnection.createPreparedStatement(query);
            for (int i = 0; i < credentials.size(); i++) {
                statement.setString((i + 1), credentials.get(i));
            }
            statement.execute();
            statement.close();
            databaseConnection.closeConnection();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public boolean setCustomerInfo(int customerID, List<String> customerInfo) {
        String query = "Insert Into CustomerInfo Values ( ?,?,?,?,?,?,?,?,?,? );";
        try {
            PreparedStatement statement = databaseConnection.createPreparedStatement(query);
            statement.setInt(1, customerID);
            for (int i = 0; i < customerInfo.size(); i++) {
                statement.setString((i + 2), customerInfo.get(i));
            }
            statement.execute();
            statement.close();
            databaseConnection.closeConnection();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
        return true;
    }
}