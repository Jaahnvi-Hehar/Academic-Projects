package service.packageTracking;

import enums.PackageStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import service.bookingInformation.AbstractBookingInformationFactory;
import service.bookingInformation.BookingInformationFactory;
import service.bookingInformation.IBookingService;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class DispatchedObserverTest
{
    private static final Integer TEST_DISPATCHED_ID = 1;
    private static final Integer TEST_BOOKING_ID = 1;

    @InjectMocks
    private DispatchedObserver dispatchedObserverTest;

    @Mock
    IBookingService iBookingService;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        AbstractBookingInformationFactory.setBookingInformationFactory(new BookingInformationFactory());
        dispatchedObserverTest = new DispatchedObserver();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testUpdate()
    {
        when(iBookingService.updateStatus(TEST_BOOKING_ID,TEST_DISPATCHED_ID)).thenReturn(true);
        Assertions.assertEquals(TEST_DISPATCHED_ID, PackageStatus.DISPATCHED.getNumber());
    }

    @Test
    public void testSetBookingId(){
        dispatchedObserverTest.setBookingId(TEST_BOOKING_ID);
        Assertions.assertNotNull(dispatchedObserverTest);
    }
}
