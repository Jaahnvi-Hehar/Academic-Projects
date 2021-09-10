package service.bookingInformation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;

import static org.mockito.MockitoAnnotations.openMocks;

public class BookingInformationTest {

    @InjectMocks
    private BookingInformationFactory bookingInformationTest;

    @Mock
    private ICityService iCityService;

    @Mock
    private IBookingService iBookingService;

    @Mock
    private IPackageService iPackageService;

    @Mock
    private ITransportModeService iTransportModeService;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        bookingInformationTest = new BookingInformationFactory();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCreateCity()
    {
        iCityService = bookingInformationTest.createCity();
        Assertions.assertNotNull(iCityService);
    }

    @Test
    public void testCreateBooking()
    {
        iBookingService = bookingInformationTest.createBooking();
        Assertions.assertNotNull(iBookingService);
    }

    @Test
    public void testCreatePackage()
    {
        iPackageService = bookingInformationTest.createPackage();
        Assertions.assertNotNull(iPackageService);
    }

    @Test
    public void testCreateTransport()
    {
        iTransportModeService = bookingInformationTest.createTransport();
        Assertions.assertNotNull(iTransportModeService);
    }

}
