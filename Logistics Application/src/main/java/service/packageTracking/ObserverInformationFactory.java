package service.packageTracking;

public class ObserverInformationFactory extends AbstractObserverInformationFactory
{
    @Override
    public DispatchedObserver createDispatchedObserver()
    {
        return new DispatchedObserver();
    }

    @Override
    public ShippedObserver createShippedObserver()
    {
        return new ShippedObserver();
    }

    @Override
    public DeliveredObserver createDeliveredObserver()
    {
        return new DeliveredObserver();
    }

    @Override
    public ISubject createSubject()
    {
        return new Subject();
    }

    @Override
    public IPackageTracker createPackageTracker()
    {
        return new PackageTracker(new Subject());
    }
}
