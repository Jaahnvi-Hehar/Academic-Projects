package service.packageTracking;

public interface ISubject
{
    public void attach(IObserver observer);
    public void detach(IObserver observer);
    public void notifyObservers();
}
