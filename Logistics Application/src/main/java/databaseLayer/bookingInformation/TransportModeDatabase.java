package databaseLayer.bookingInformation;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransportModeDatabase implements ITransportModeDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransportModeDatabase.class);

    private final ILogisticsDatabaseConnection databaseConnection;

    public TransportModeDatabase(ILogisticsDatabaseConnection databaseConnection)
    {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<String> getModes() {

        ArrayList<String> modeArrayList = new ArrayList<>();
        try {
            String sql = "select * from transportMode";
            ResultSet resultSet = this.databaseConnection.executeQuery(sql);
            while (resultSet.next()) {
                modeArrayList.add(resultSet.getString(2));
            }
            resultSet.close();
            this.databaseConnection.closeConnection();

        }  catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return modeArrayList;
    }
    @Override
    public boolean insertMode(String modeName)
    {
        try {
            String sql = "insert into transportMode (ModeId,ModeName) values(?,?)";
            PreparedStatement statement = this.databaseConnection.createPreparedStatement(sql);
            statement.setString(2,modeName);
            int numberOfRecords = statement.executeUpdate(sql);
            if(numberOfRecords > 0) {
                LOGGER.debug("{} records inserted",numberOfRecords);
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
}
