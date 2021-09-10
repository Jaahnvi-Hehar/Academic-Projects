package service.packageTracking;

import enums.PackageStatus;
import service.bookingInformation.AbstractBookingInformationFactory;
import service.bookingInformation.IBookingService;

public class ShippedObserver implements IObserver
{
    private final AbstractBookingInformationFactory abstractBookingInformationFactory;

    private int bookingId;

    public ShippedObserver()
    {
        abstractBookingInformationFactory = AbstractBookingInformationFactory.instance();
    }

    @Override
    public void update()
    {
        IBookingService iBookingService = abstractBookingInformationFactory.createBooking();
        iBookingService.updateStatus(this.bookingId, PackageStatus.SHIPPED.getNumber());
    }

    @Override
    public void setBookingId(int bookingId)
    {
        this.bookingId = bookingId;
    }
}
