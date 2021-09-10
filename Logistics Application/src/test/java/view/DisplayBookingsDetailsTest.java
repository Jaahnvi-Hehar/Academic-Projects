package view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.exceptions.base.MockitoException;
import service.bookingInformation.AbstractBookingInformationFactory;
import service.bookingInformation.BookingInformationFactory;
import service.costEstimation.AbstractCostEstimationInformationFactory;
import service.costEstimation.CostEstimationInformationFactory;
import view.bookingsDetailsIO.DisplayBookingsDetailsFactory;
import view.bookingsDetailsIO.IBookingMainIO;
import view.bookingsDetailsIO.IPackageTrackIO;
import static org.mockito.MockitoAnnotations.openMocks;

public class DisplayBookingsDetailsTest {

    @InjectMocks
    private DisplayBookingsDetailsFactory displayBookingsDetailsTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        AbstractBookingInformationFactory.setBookingInformationFactory(new BookingInformationFactory());
        AbstractCostEstimationInformationFactory.setCostInformationFactory(new CostEstimationInformationFactory());
        displayBookingsDetailsTest = new DisplayBookingsDetailsFactory();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCreateBookingIO()
    {
        final IBookingMainIO iBookingMainIO = displayBookingsDetailsTest.createBookingIO();
        Assertions.assertNotNull(iBookingMainIO);
    }

    @Test
    void testCreatePackageTrackIO()
    {
        final IPackageTrackIO iPackageTrackIO = displayBookingsDetailsTest.createPackageTrackIO();
        Assertions.assertNotNull(iPackageTrackIO);

    }
}
