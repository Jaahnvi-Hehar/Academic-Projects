package databaseLayer.bookingInformation;

import java.util.HashMap;

public interface IBookingDatabase {

    public HashMap<String,Integer> getBookings();
    public String insertBooking(int sourceCityId, int destinationCityId, int packageId,int customerId);
    public boolean deleteBooking(int bookingId);
    public HashMap<String,Integer> getBookingByBookingID(int bookingId);
    public boolean updateStatus(int bookingId,int updateStatus);
    public HashMap<String,Integer> getBookingsBasedOnCustomerId(int customerId);
}
