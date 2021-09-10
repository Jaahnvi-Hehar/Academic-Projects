package databaseLayer.bookingInformation;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CityDatabase implements ICityDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityDatabase.class);

    private final ILogisticsDatabaseConnection databaseConnection;

    public CityDatabase(ILogisticsDatabaseConnection databaseConnection)
    {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<String> getCities()
    {
        ArrayList<String> cityArrayList = new ArrayList<>();
        try {
            Statement statement = this.databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from cities");
            while (resultSet.next()) {
                cityArrayList.add(resultSet.getString(2));
            }
            resultSet.close();
            statement.close();
            this.databaseConnection.closeConnection();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return cityArrayList;
    }

    @Override
    public boolean insertCity(String cityName)
    {
        try {
            String sql = "insert into cities (city_Id,city_name) values(?,?)";
            PreparedStatement statement = this.databaseConnection.createPreparedStatement(sql);
            statement.setString(2,cityName);
            int numberOfRecords = statement.executeUpdate(sql);
            if(numberOfRecords > 0) {
                LOGGER.debug(" {} records inserted",numberOfRecords);
                return true;
            }
            else {
               LOGGER.error("Insert operation failed! Please try again");
            }
            statement.close();
            this.databaseConnection.closeConnection();

        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCity(int cityId)
    {
        String sql = "delete from cities where city_Id="+cityId;
        int numberOfRecords = this.databaseConnection.executeUpdate(sql);
        if(numberOfRecords > 0) {
           LOGGER.info(numberOfRecords + "records Deleted");
           return true;
        }
        else {
            LOGGER.error("Deleted operation failed! Please try again");
        }
        this.databaseConnection.closeConnection();
        return false;
    }
}


