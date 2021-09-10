package service.packageTracking;

import enums.PackageStatus;
import java.util.HashMap;

public class PackageTracker implements IPackageTracker
{
    private final AbstractObserverInformationFactory abstractObserverInformationFactory;
    private final ISubject packageManagerSubject;

    public PackageTracker(ISubject packageManagerSubject)
    {
        abstractObserverInformationFactory = AbstractObserverInformationFactory.instance();
        this.packageManagerSubject = packageManagerSubject;
    }

    @Override
    public void updatePackageStatus(HashMap<String, Integer> bookings, String[] bookingIdSeparated) {
        for (String bookingId : bookingIdSeparated) {
            Integer bookingStatus = bookings.get(bookingId);
            if ( bookingStatus < PackageStatus.DELIVERED.getNumber()) {
                int updatedBookingStatus = bookingStatus + 1;
                IObserver observer = getObserverBasedOnBookingStatus(updatedBookingStatus);
                observer.setBookingId(Integer.parseInt(bookingId.substring(1)));
                this.packageManagerSubject.attach(observer);
            }
        }
        this.packageManagerSubject.notifyObservers();
    }

    @Override
    public IObserver getObserverBasedOnBookingStatus(int updatedStatus)
    {
        if(updatedStatus == PackageStatus.DISPATCHED.getNumber()) {
            return abstractObserverInformationFactory.createDispatchedObserver();
        }
        else if(updatedStatus == PackageStatus.SHIPPED.getNumber()) {
            return abstractObserverInformationFactory.createShippedObserver();
        }
        else {
            return abstractObserverInformationFactory.createDeliveredObserver();
        }
    }
}
