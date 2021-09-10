package service.packageTracking;

public abstract class AbstractObserverInformationFactory
{
    private static AbstractObserverInformationFactory uniqueInstance = null;

    public static AbstractObserverInformationFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new ObserverInformationFactory();
        }
        return uniqueInstance;
    }

    public static void setObserverFactory(AbstractObserverInformationFactory abstractObserverInformationFactory)
    {
        uniqueInstance = abstractObserverInformationFactory;
    }

    public abstract DispatchedObserver createDispatchedObserver();
    public abstract ShippedObserver createShippedObserver();
    public abstract DeliveredObserver createDeliveredObserver();
    public abstract ISubject createSubject();
    public abstract IPackageTracker createPackageTracker();
}
