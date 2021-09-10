package service.packageTracking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import static org.mockito.MockitoAnnotations.openMocks;

public class SubjectTest {

    @InjectMocks
    private Subject subjectTest;

    @Mock
    private IObserver observer;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        subjectTest = new Subject();
        observer = new DispatchedObserver();
        observer = new DeliveredObserver();
        observer = new ShippedObserver();
    }

    @AfterEach
    public void tearDown() throws Exception
    {
        mockitoCloseable.close();
    }

    @Test
    public void testAttach()
    {
        subjectTest.attach(observer);
        Assertions.assertNotNull(subjectTest);
    }

    @Test
    public void testDetach()
    {
        subjectTest.detach(observer);
        Assertions.assertNotNull(subjectTest);

    }

    @Test
    public void testNotifyObservers()
    {
        subjectTest.notifyObservers();
        Assertions.assertNotNull(subjectTest);
    }
}
