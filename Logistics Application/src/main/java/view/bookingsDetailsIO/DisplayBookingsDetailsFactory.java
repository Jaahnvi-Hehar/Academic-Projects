package view.bookingsDetailsIO;

import service.packageTracking.PackageTracker;
import service.packageTracking.Subject;
import view.operation.IO;

public class DisplayBookingsDetailsFactory extends AbstractDisplayBookingsFactory
{
    @Override
    public IBookingMainIO createBookingIO() {
        return new BookingMainIO(new IO());
    }

    @Override
    public IPackageTrackIO createPackageTrackIO() {
        return new PackageTrackIO(new IO(),new PackageTracker(new Subject()));
    }
}
