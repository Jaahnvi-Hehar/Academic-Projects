package databaseLayer.customer;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoSelector implements IInfoSelector {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoSelector.class);
    private final ILogisticsDatabaseConnection databaseConnection;

    public InfoSelector(ILogisticsDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public String getPassword(String emailAddress) {
        String password = null;
        String query = "Select Password From CustomerCredentials Where EmailAddress = '" + emailAddress + "';";
        try {
            ResultSet resultSet = databaseConnection.executeQuery(query);
            resultSet.next();
            password = resultSet.getString(1);
            resultSet.close();
            databaseConnection.closeConnection();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return password;
    }


    @Override
    public int getCustomerID(String emailAddress) {
        int customerID = -1;
        String query = "Select CustomerID From CustomerCredentials Where EmailAddress = '" + emailAddress + "';";
        try {
            ResultSet resultSet = databaseConnection.executeQuery(query);
            resultSet.next();
            customerID = resultSet.getInt(1);
            resultSet.close();
            databaseConnection.closeConnection();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return customerID;
    }


    public String getName(int customerId)
    {
        String query = "SELECT Name FROM CustomerInfo WHERE CustomerID = '" + customerId + "' ;";
        String name = "null";
        try
        {
            ResultSet resultSet = databaseConnection.executeQuery(query);
            while(resultSet.next())
            {
                name = resultSet.getString(1);
                resultSet.close();
            }
            databaseConnection.closeConnection();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return name;
    }


    public String getDOB(int customerId)
    {
        String query = "SELECT DOB FROM CustomerInfo WHERE CustomerID = '" + customerId + "' ;";
        String DOB = "null";
        try
        {
            ResultSet resultSet = databaseConnection.executeQuery(query);
            while(resultSet.next())
            {
                DOB = resultSet.getString(2);
                resultSet.close();
            }
            databaseConnection.closeConnection();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return DOB;
    }
}