package service.packageTracking;

import java.util.HashMap;

public interface IPackageTracker
{
    public void updatePackageStatus(HashMap<String, Integer> bookings, String[] bookingIdSeparated);
    public IObserver getObserverBasedOnBookingStatus(int updatedStatus);
}
