package service.bookingInformation;

import databaseLayer.bookingInformation.BookingDatabase;
import databaseLayer.bookingInformation.CityDatabase;
import databaseLayer.bookingInformation.PackageDatabase;
import databaseLayer.bookingInformation.TransportModeDatabase;
import databaseLayer.connection.LogisticsDatabaseConnection;

public class BookingInformationFactory extends AbstractBookingInformationFactory
{
    @Override
    public ICityService createCity()
    {
        return new CityService(new CityDatabase(new LogisticsDatabaseConnection()));
    }

    @Override
    public IBookingService createBooking()
    {
        return new BookingService(new BookingDatabase(new LogisticsDatabaseConnection()), createPackage());
    }

    @Override
    public IPackageService createPackage()
    {
        return new PackageService(new PackageDatabase(new LogisticsDatabaseConnection()));
    }

    @Override
    public ITransportModeService createTransport()
    {
        return new TransportModeService(new TransportModeDatabase(new LogisticsDatabaseConnection()));
    }
}
