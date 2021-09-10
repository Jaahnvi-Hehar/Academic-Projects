package service.packageTracking;

import java.util.ArrayList;
import java.util.List;

public class Subject implements ISubject
{
    private final List<IObserver> observers;

    public Subject()
    {
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(IObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers()
    {
        this.observers.forEach(IObserver::update);
    }

}

