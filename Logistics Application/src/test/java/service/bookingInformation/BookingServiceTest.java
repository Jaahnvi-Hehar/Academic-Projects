package service.bookingInformation;

import databaseLayer.bookingInformation.IBookingDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import java.util.HashMap;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingServiceTest;

    @Mock
    private IBookingDatabase mockBookingDatabase;

    @Mock
    private IPackageService mockPackageService;

    HashMap<String, Float> packageDetails;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        bookingServiceTest = new BookingService(mockBookingDatabase,mockPackageService);
        packageDetails = new HashMap<String, Float>();
        packageDetails.put("packageHeight", 3.0F);
        packageDetails.put("packageWidth", 3.0F);
        packageDetails.put("packageLength", 3.0F);
        packageDetails.put("packageWeight", 30.0F);
    }
    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testGetBookings() {
        when(mockBookingDatabase.getBookings()).thenReturn(new HashMap<>());
        bookingServiceTest.getBookings();
    }

    @Test
    public void testGeCustomerBookings() {
        when(mockBookingDatabase.getBookingsBasedOnCustomerId(1)).thenReturn(new HashMap<>());
        bookingServiceTest.getBookingsBasedOnCustomerId(1);
    }

    @Test
    public void testGetBookings_IBookingDbReturnsNoItems() {
        when(mockBookingDatabase.getBookings()).thenReturn(new HashMap<>());
        bookingServiceTest.getBookings();
    }
    @Test
    public void testInsertBookingSuccess() {
        when(mockPackageService.insertPackage(packageDetails, 1)).thenReturn("P001");
        when(mockBookingDatabase.insertBooking(1, 2, 1,1)).thenReturn("B001");
        bookingServiceTest.insertBooking(1, 2, packageDetails, 1,1);
        Assertions.assertNotNull(mockBookingDatabase);
    }

    @Test
    public void testBookingIdEmpty() {
        when(mockPackageService.insertPackage(packageDetails, 1)).thenReturn("P001");
        when(mockBookingDatabase.insertBooking(1, 2, 1,1)).thenReturn("");
        bookingServiceTest.insertBooking(1, 2, packageDetails, 1,1);
   }

    @Test
    public void testIncorrectPackageId() {
        when(mockPackageService.insertPackage(packageDetails, 0)).thenReturn("result");
        when(mockBookingDatabase.insertBooking(0, 0, 0,1)).thenReturn("result");
        bookingServiceTest.insertBooking(0, 0, packageDetails, 0,1);
    }
    @Test
    public void testBookingIdNull() {
        when(mockPackageService.insertPackage(packageDetails, 1)).thenReturn("P001");
        when(mockBookingDatabase.insertBooking(1, 2, 1,1)).thenReturn(null);
        bookingServiceTest.insertBooking(1, 2, packageDetails, 1,1);
    }

    @Test
    public void testDeleteBooking() {
        when(mockBookingDatabase.deleteBooking(1)).thenReturn(false);
        bookingServiceTest.deleteBooking(1);
    }

    @Test
    public void testGetBookingByBookingID() {
        bookingServiceTest.getBookingByBookingID(1);
        verify(mockBookingDatabase).getBookingByBookingID(1);
    }

    @Test
    public void testGetUpdateStatus() {
        bookingServiceTest.updateStatus(1,2);
        verify(mockBookingDatabase).updateStatus(1,2);
    }

}