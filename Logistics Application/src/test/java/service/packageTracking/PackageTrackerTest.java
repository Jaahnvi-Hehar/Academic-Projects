package service.packageTracking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.MockitoAnnotations.openMocks;

public class PackageTrackerTest {

    @InjectMocks
    private PackageTracker packageTrackerTest;

    @Mock
    private ISubject subject;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        AbstractObserverInformationFactory.setObserverFactory(new ObserverInformationFactory());
        packageTrackerTest = new PackageTracker(subject);
        subject = new Subject();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testUpdatePackageStatusDispatched()
    {
        final HashMap<String, Integer> bookings = new HashMap<>(Map.ofEntries(Map.entry("B001", 0)));
        packageTrackerTest.updatePackageStatus(bookings, new String[]{"B001"});
        Assertions.assertNotNull(packageTrackerTest);
    }

    @Test
    public void testUpdatePackageStatusShipped()
    {
        final HashMap<String, Integer> bookings = new HashMap<>(Map.ofEntries(Map.entry("B002", 1)));
        packageTrackerTest.updatePackageStatus(bookings, new String[]{"B002"});
        Assertions.assertNotNull(packageTrackerTest);
    }

    @Test
    public void testUpdatePackageStatusDelivered()
    {
        final HashMap<String, Integer> bookings = new HashMap<>(Map.ofEntries(Map.entry("B003", 2)));
        packageTrackerTest.updatePackageStatus(bookings, new String[]{"B003"});
        Assertions.assertNotNull(packageTrackerTest);
    }
}
