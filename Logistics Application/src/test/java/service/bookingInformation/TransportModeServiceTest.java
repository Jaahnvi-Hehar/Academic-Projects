package service.bookingInformation;

import databaseLayer.bookingInformation.ITransportModeDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class TransportModeServiceTest {

    @InjectMocks
    private TransportModeService transportModeServiceTest;

    @Mock
    private ITransportModeDatabase mockTransportModeDatabase;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException {
        mockitoCloseable = openMocks(this);
        transportModeServiceTest = new TransportModeService(mockTransportModeDatabase);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testGetModes() {
        when(mockTransportModeDatabase.getModes()).thenReturn(new ArrayList<>(List.of("Airways","Roadways","Railways")));
        final ArrayList<String> result = transportModeServiceTest.getModes();
        Assertions.assertEquals(result,List.of("Airways","Roadways","Railways"));
    }

    @Test
    public void testGetModesITransportModeDbReturnsNoItems() {
        when(mockTransportModeDatabase.getModes()).thenReturn(new ArrayList<>());
        final ArrayList<String> result = transportModeServiceTest.getModes();
        Assertions.assertEquals(result,new ArrayList<>(List.of()));
    }

    @Test
    public void testInsertMode(){
      when(transportModeServiceTest.insertMode("Ship")).thenReturn(true);
      Assertions.assertTrue(transportModeServiceTest.insertMode("Ship"));
    }
}
