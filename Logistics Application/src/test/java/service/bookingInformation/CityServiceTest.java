package service.bookingInformation;

import databaseLayer.bookingInformation.ICityDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CityServiceTest {

    @InjectMocks
    private CityService cityServiceTest;

    @Mock
    private ICityDatabase mockCityDatabase;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException {
        mockitoCloseable = openMocks(this);
        cityServiceTest = new CityService(mockCityDatabase);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testGetCities() {
        when(mockCityDatabase.getCities()).thenReturn(new ArrayList<>(List.of("Hyderabad")));
        cityServiceTest.getCities();
    }

    @Test
    public void testGetCities_ICityDbReturnsNoItems() {
        when(mockCityDatabase.getCities()).thenReturn(new ArrayList<>());
        cityServiceTest.getCities();
    }

    @Test
    public void testInsertCity()
    {
        when(mockCityDatabase.insertCity("Chennai")).thenReturn(false);
        cityServiceTest.insertCity("Chennai");
    }

    @Test
    public void testDeleteCity() {
        when(mockCityDatabase.deleteCity(1)).thenReturn(false);
        cityServiceTest.deleteCity(1);
    }

}
