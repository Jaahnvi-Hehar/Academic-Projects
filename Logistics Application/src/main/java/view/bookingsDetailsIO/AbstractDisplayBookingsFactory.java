package view.bookingsDetailsIO;

public abstract class AbstractDisplayBookingsFactory
{
    private static AbstractDisplayBookingsFactory uniqueInstance = null;

    public static AbstractDisplayBookingsFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new DisplayBookingsDetailsFactory();
        }
        return uniqueInstance;
    }

    public static void setDisplayBookingsFactory(AbstractDisplayBookingsFactory abstractDisplayBookingsFactory)
    {
        uniqueInstance = abstractDisplayBookingsFactory;
    }

    public abstract IBookingMainIO createBookingIO();
    public abstract IPackageTrackIO createPackageTrackIO();
}
