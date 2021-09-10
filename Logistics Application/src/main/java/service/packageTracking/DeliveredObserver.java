package service.packageTracking;

import enums.PackageStatus;
import service.bookingInformation.AbstractBookingInformationFactory;
import service.bookingInformation.IBookingService;

public class DeliveredObserver implements IObserver
{
    private final AbstractBookingInformationFactory abstractBookingInformationFactory;

    private int bookingId;

    public DeliveredObserver()
    {
        this.abstractBookingInformationFactory = AbstractBookingInformationFactory.instance();
    }

    @Override
    public void update()
    {
        IBookingService iBookingService = this.abstractBookingInformationFactory.createBooking();
        iBookingService.updateStatus(this.bookingId, PackageStatus.DELIVERED.getNumber());
    }

    @Override
    public void setBookingId(int bookingId)
    {
        this.bookingId = bookingId;
    }
}
