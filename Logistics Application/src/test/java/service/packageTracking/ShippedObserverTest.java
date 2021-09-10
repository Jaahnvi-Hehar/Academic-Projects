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

public class ShippedObserverTest {

    private static final Integer TEST_SHIPPING_ID = 2;
    private static final Integer TEST_BOOKING_ID = 1;

    @InjectMocks
    private ShippedObserver shippedObserverTest;

    @Mock
    IBookingService iBookingService;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        AbstractBookingInformationFactory.setBookingInformationFactory(new BookingInformationFactory());
        shippedObserverTest = new ShippedObserver();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testUpdate()
    {
        when(iBookingService.updateStatus(TEST_BOOKING_ID,TEST_SHIPPING_ID)).thenReturn(true);
        Assertions.assertEquals(TEST_SHIPPING_ID, PackageStatus.SHIPPED.getNumber());
    }

    @Test
    public void testSetBookingId(){
        shippedObserverTest.setBookingId(TEST_BOOKING_ID);
        Assertions.assertNotNull(shippedObserverTest);
    }
}