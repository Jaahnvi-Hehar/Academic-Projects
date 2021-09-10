package service.bookingInformation;

import databaseLayer.bookingInformation.IPackageDatabase;
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

public class PackageServiceTest {

    @InjectMocks
    private PackageService packageServiceTest;

    @Mock
    private IPackageDatabase mockPackageDatabase;

    HashMap<String, Float> packageDetails;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        packageServiceTest = new PackageService(mockPackageDatabase);
        packageDetails = new HashMap<String, Float>();
        packageDetails.put("packageHeight", 3.0F);
        packageDetails.put("packageWidth", 3.0F);
        packageDetails.put("packageLength", 3.0F);
        packageDetails.put("packageWeight", 3.0F);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testGetPackages() {
        packageServiceTest.getPackages();
        verify(mockPackageDatabase).getPackages();
    }

    @Test
    public void testInsertPackage() {
        when(mockPackageDatabase.insertPackage(packageDetails, 1)).thenReturn("P001");
        when(packageServiceTest.insertPackage(packageDetails, 1)).thenReturn("P001");
        packageServiceTest.insertPackage(packageDetails, 1);
        Assertions.assertNotNull(packageServiceTest);
    }

    @Test
    public void testDeletePackage() {
        when(mockPackageDatabase.deletePackage(1)).thenReturn(false);
        packageServiceTest.deletePackage(1);
    }

    @Test
    public void testGetPackageByPackageID() {
        packageServiceTest.getPackageByPackageID(1);
        verify(mockPackageDatabase).getPackageByPackageID(1);
    }
}
