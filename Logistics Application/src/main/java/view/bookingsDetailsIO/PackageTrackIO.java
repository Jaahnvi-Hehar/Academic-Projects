package view.bookingsDetailsIO;

import enums.PackageStatus;
import service.bookingInformation.AbstractBookingInformationFactory;
import service.bookingInformation.IBookingService;
import service.packageTracking.IPackageTracker;
import view.operation.IIO;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PackageTrackIO implements IPackageTrackIO
{
    private static final String YES_INPUT = "yes";

    private final IPackageTracker packageTracker;
    private final IIO inputOutput;

    public PackageTrackIO(IIO inputOutput,IPackageTracker packageTracker)
    {
        this.inputOutput = inputOutput;
        this.packageTracker = packageTracker;
    }

    @Override
    public void execute()
    {
        AbstractBookingInformationFactory abstractBookingInformationFactory = AbstractBookingInformationFactory.instance();
        IBookingService bookingService = abstractBookingInformationFactory.createBooking();
        boolean statusTrack = true;
        do{
            inputOutput.writeOutput("Change the package Status ? Yes/No");
            String statusCheck = inputOutput.readInput();
            if (statusCheck.equalsIgnoreCase(YES_INPUT)) {
                HashMap<String, Integer> bookings = bookingService.getBookings();
                getBookingsTable(bookings);
                try{
                    UpdateBookingStatus(bookingService, bookings);
                }catch (Exception exception){
                    inputOutput.writeOutput("Invalid Booking Id!");
                }
            }
            else {
                statusTrack = false;
            }
        } while (statusTrack);
    }

    private void UpdateBookingStatus(IBookingService bookingService, HashMap<String, Integer> bookings) {
        inputOutput.writeOutput("Enter single or multiple booking Ids with , :");
        String bookingIds = inputOutput.readInput();
        String[] bookingIdSeparated = bookingIds.split(",");
        packageTracker.updatePackageStatus(bookings, bookingIdSeparated);
        HashMap<String, Integer> afterBookings = bookingService.getBookings();
        getBookingsTable(afterBookings);
    }

    @Override
    public void getBookingsTable(HashMap<String, Integer> bookings) {
        inputOutput.writeOutput("___________|Bookings|_____________");
        inputOutput.writeOutput("Booking_Ids____|_____Status");
        Map<String, Integer> sortedBookings = new TreeMap<>(bookings);
        for (Map.Entry<String,Integer> booking: sortedBookings.entrySet()) {
            inputOutput.writeOutput(booking.getKey()+"___________|"+PackageStatus.values()[booking.getValue()]+"_______");
        }
    }
}