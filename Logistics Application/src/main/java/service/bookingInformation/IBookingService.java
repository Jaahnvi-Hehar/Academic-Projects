package service.bookingInformation;

import java.util.HashMap;

public interface IBookingService {
    public HashMap<String,Integer> getBookings();
    public HashMap<String,Integer> getBookingsBasedOnCustomerId(int customerId);
    public void deleteBooking(int bookingId);
    public void getBookingByBookingID(int bookingId);
    public boolean updateStatus(int bookingId,int updateStatus);
    public String insertBooking(int sourceCityId, int destinationCityId, HashMap<String, Float> packageDetails, int packageId, int customerId);
}