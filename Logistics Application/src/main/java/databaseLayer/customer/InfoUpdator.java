package databaseLayer.customer;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InfoUpdator implements IInfoUpdator {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoUpdator.class);
    private final ILogisticsDatabaseConnection databaseConnection;


    public InfoUpdator(ILogisticsDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public boolean updateEmailAddress(int customerID, String emailAddress) {
        String query = "Update CustomerCredentials set EmailAddress = ? where CustomerID = ?;";
        try {
            PreparedStatement statement = databaseConnection.createPreparedStatement(query);
            statement.setString(1, emailAddress);
            statement.setInt(2, customerID);
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
    public boolean updatePassword(int customerID, String password) {
        String query = "Update CustomerCredentials set Password = ? where CustomerID = ?;";
        try {
            PreparedStatement statement = databaseConnection.createPreparedStatement(query);
            statement.setString(1, password);
            statement.setInt(2, customerID);
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
    public boolean updateCustomerInfo(int customerID, List<String> customerInfo) {
        String query = "Update CustomerInfo set Name = ?, DOB = ?, HouseNo = ?, BuildingName = ?, " +
                "StreetName = ?, Area = ?, City = ?, PostalCode = ?, ContactNo = ? where CustomerID = ?;";
        try {
            PreparedStatement statement = databaseConnection.createPreparedStatement(query);
            for (int i = 0; i < customerInfo.size(); i++) {
                statement.setString((i + 1), customerInfo.get(i));
            }
            statement.setInt((customerInfo.size() + 1), customerID);
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