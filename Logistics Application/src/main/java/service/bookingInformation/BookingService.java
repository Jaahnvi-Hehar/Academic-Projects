package service.bookingInformation;

import databaseLayer.bookingInformation.IBookingDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;

public class BookingService implements IBookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

    private final IBookingDatabase bookingDatabase;
    private final IPackageService packageService;

    public BookingService(IBookingDatabase bookingDatabase, IPackageService packageService) {
        this.bookingDatabase = bookingDatabase;
        this.packageService = packageService;
    }

    @Override
    public HashMap<String,Integer> getBookings()
    {
        return this.bookingDatabase.getBookings();
    }

    @Override
    public HashMap<String,Integer> getBookingsBasedOnCustomerId(int customerId) { return this.bookingDatabase.getBookingsBasedOnCustomerId(customerId);}

    @Override
    public String insertBooking(int sourceCityId, int destinationCityId, HashMap<String,Float> packageDetails, int userPackageType, int customerId)
    {
        String bookingId = "-1";
        try{
                String packageId = this.packageService.insertPackage(packageDetails, userPackageType);
                if(packageId.contains("P")) {
                    int unFormatPackageId = Integer.parseInt(packageId.split("P")[1]);
                    bookingId = this.bookingDatabase.insertBooking(sourceCityId, destinationCityId, unFormatPackageId,customerId);
                    if (bookingId.isEmpty()) {
                        LOGGER.error("Booking not confirmed, try again!");
                    } else {

                       LOGGER.debug("Congratulations, Your booking is confirmed!, Your Booking Id = " + bookingId);
                    }
                }
                else
                {
                    LOGGER.error("Package id invalid, please retry!");
                }
            }
         catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return bookingId;
    }

    @Override
    public void deleteBooking(int bookingId)
    {
        this.bookingDatabase.deleteBooking(bookingId);
    }

    @Override
    public void getBookingByBookingID(int bookingId)
    {
        this.bookingDatabase.getBookingByBookingID(bookingId);
    }

    @Override
    public boolean updateStatus(int bookingId, int updateStatus) {
        this.bookingDatabase.updateStatus(bookingId,updateStatus);
        return true;
    }
}
