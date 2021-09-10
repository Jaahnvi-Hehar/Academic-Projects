package service.packageTracking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.exceptions.base.MockitoException;
import static org.mockito.MockitoAnnotations.openMocks;

public class ObserverInformationTest {

    @InjectMocks
    private ObserverInformationFactory observerInformationTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        observerInformationTest = new ObserverInformationFactory();
    }

    @AfterEach
    public void tearDown() throws Exception
    {
        mockitoCloseable.close();
    }

    @Test
    public void testCreateDispatchedObserver()
    {
        final DispatchedObserver dispatchedObserver = observerInformationTest.createDispatchedObserver();
        Assertions.assertNotNull(dispatchedObserver);
    }

    @Test
    public void testCreateShippedObserver()
    {
        final ShippedObserver shippedObserver = observerInformationTest.createShippedObserver();
        Assertions.assertNotNull(shippedObserver);
    }

    @Test
    public void testCreateDeliveredObserver()
    {
        final DeliveredObserver deliveredObserver = observerInformationTest.createDeliveredObserver();
        Assertions.assertNotNull(deliveredObserver);
    }

    @Test
    public void testCreateSubject() {
        final ISubject subject = observerInformationTest.createSubject();
        Assertions.assertNotNull(subject);
    }

    @Test
    public void testCreatePackageTracker(){
        final IPackageTracker packageTracker = observerInformationTest.createPackageTracker();
        Assertions.assertNotNull(packageTracker);
    }
}
