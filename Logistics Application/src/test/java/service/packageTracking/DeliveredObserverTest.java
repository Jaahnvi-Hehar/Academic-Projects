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

public class DeliveredObserverTest
{
    private static final Integer TEST_DELIVERED_ID = 3;
    private static final Integer TEST_BOOKING_ID = 1;

    @InjectMocks
    private DeliveredObserver deliveredObserverTest;

    @Mock
    IBookingService iBookingService;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        AbstractBookingInformationFactory.setBookingInformationFactory(new BookingInformationFactory());
        deliveredObserverTest = new DeliveredObserver();
    }

    @AfterEach
    public void tearDown() throws Exception
    {
        mockitoCloseable.close();
    }

    @Test
    public void testUpdate()
    {
        when(iBookingService.updateStatus(TEST_BOOKING_ID,TEST_DELIVERED_ID)).thenReturn(true);
        Assertions.assertEquals(TEST_DELIVERED_ID, PackageStatus.DELIVERED.getNumber());
    }

    @Test
    public void testSetBookingId(){
        deliveredObserverTest.setBookingId(TEST_BOOKING_ID);
        Assertions.assertNotNull(deliveredObserverTest);
    }
}
