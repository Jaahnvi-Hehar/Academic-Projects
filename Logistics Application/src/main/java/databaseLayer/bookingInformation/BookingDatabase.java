package databaseLayer.bookingInformation;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import enums.PackageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class BookingDatabase implements IBookingDatabase
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingDatabase.class);
    private static final String BOOKING_ID_PREFIX = "B";

    private final ILogisticsDatabaseConnection databaseConnection;

    public BookingDatabase(ILogisticsDatabaseConnection databaseConnection)
    {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public HashMap<String,Integer> getBookings() {
        HashMap<String ,Integer> bookingIdStatus = new HashMap<>();
        try {
            String query = "select * from booking";
            ResultSet resultSet = databaseConnection.executeQuery(query);
            while (resultSet.next()) {
                bookingIdStatus.put(BOOKING_ID_PREFIX+String.format("%04d",resultSet.getInt(1)),resultSet.getInt(6));
            }
            resultSet.close();
            this.databaseConnection.closeConnection();

        }  catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return bookingIdStatus;
    }

    @Override
    public HashMap<String,Integer> getBookingsBasedOnCustomerId(int customerId)
    {
        HashMap<String,Integer> bookingIdStatus = new HashMap<>();
        try {
            String query = "select * from booking where customer_id = "+ customerId;
            ResultSet resultSet = this.databaseConnection.executeQuery(query);
            while (resultSet.next()) {
                bookingIdStatus.put(BOOKING_ID_PREFIX + String.format("%04d",resultSet.getInt(1)),resultSet.getInt(6));
            }
            resultSet.close();
            this.databaseConnection.closeConnection();

        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return bookingIdStatus;
    }

    @Override
    public String insertBooking(int sourceCityId, int destinationCityId, int packageId, int customerId) {
        int bookingId;
        String formatBookingId = "";
        try {
            String sql = "insert into booking (source_Id,destination_Id,package_Id,customer_Id,packageStatus) values(?,?,?,?,?)";
            PreparedStatement statement = this.databaseConnection.createPreparedStatement(sql);
            statement.setInt(1, sourceCityId);
            statement.setInt(2, destinationCityId);
            statement.setInt(3, packageId);
            statement.setInt(4,customerId);
            statement.setInt(5,PackageStatus.COLLECTED.getNumber());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                bookingId = resultSet.getInt(1);
                formatBookingId = String.format("%04d", bookingId);
                formatBookingId = BOOKING_ID_PREFIX + formatBookingId;

            }
            statement.close();
            this.databaseConnection.closeConnection();

        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return formatBookingId;
    }

    @Override
    public boolean deleteBooking(int bookingId) {
        String sql = "delete from booking where booking_Id="+bookingId;
        int numberOfRecords = this.databaseConnection.executeUpdate(sql);
        if(numberOfRecords > 0) {
            return true;
        }
        databaseConnection.closeConnection();
        return false;
    }

    @Override
    public  HashMap<String,Integer> getBookingByBookingID(int bookingId)
    {
        HashMap<String,Integer> bookingIds = new HashMap<>();
        try {
            String sql = "select * from booking where booking_Id ="+bookingId;
            ResultSet resultSet = this.databaseConnection.executeQuery(sql);
            while (resultSet.next()) {
                bookingIds.put("Booking ID" , resultSet.getInt(1));
                bookingIds.put("Source ID" , resultSet.getInt(2));
                bookingIds.put("Destination ID" , resultSet.getInt(3));
                bookingIds.put("Package ID " , resultSet.getInt(4));
            }
            resultSet.close();
            this.databaseConnection.closeConnection();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return bookingIds;
    }

    @Override
    public boolean updateStatus(int bookingId,int updateStatus)
    {
        String sql = "UPDATE booking set packageStatus = " + "'" + updateStatus + "'" + " where booking_Id =" + bookingId;
        Statement statement = databaseConnection.createStatement();
        try {
            statement.executeUpdate(sql);
            statement.close();
            databaseConnection.closeConnection();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
}


