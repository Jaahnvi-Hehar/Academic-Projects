package service.customerData.info;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BuildingNameTest {

    @Test
    public void isValidTest1() {
        String testBuildingName = "21 Apartments";
        BuildingName building = new BuildingName();
        boolean actual = building.isValid(testBuildingName);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest2() {
        String testBuildingName = "21#Apartments";
        BuildingName building = new BuildingName();
        boolean actual = building.isValid(testBuildingName);
        Assertions.assertFalse(actual);
    }

    @Test
    public void isValidTest3() {
        String testBuildingName = "";
        BuildingName building = new BuildingName();
        boolean actual = building.isValid(testBuildingName);
        Assertions.assertFalse(actual);
    }
}
