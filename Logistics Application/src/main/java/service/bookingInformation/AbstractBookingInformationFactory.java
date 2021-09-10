package service.bookingInformation;

public abstract class AbstractBookingInformationFactory
{
    private static AbstractBookingInformationFactory uniqueInstance = null;

    public static AbstractBookingInformationFactory instance()
    {
        if(null == uniqueInstance)
    {
        uniqueInstance = new BookingInformationFactory();
    }
        return uniqueInstance;
    }

    public static void setBookingInformationFactory(AbstractBookingInformationFactory abstractBookingInformationFactory)
    {
        uniqueInstance = abstractBookingInformationFactory;
    }

    public abstract ICityService createCity();
    public abstract IBookingService createBooking();
    public abstract IPackageService createPackage();
    public abstract ITransportModeService createTransport();
}
