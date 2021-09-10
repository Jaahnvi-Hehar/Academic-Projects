package service.customerData.info;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CityTest {

    @Test
    public void isValidTest1() {
        String testCity = "London";
        City city = new City();
        boolean actual = city.isValid(testCity);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest2() {
        String testCity = "San Francisco";
        City city = new City();
        boolean actual = city.isValid(testCity);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest3() {
        String testCity = "";
        BuildingName building = new BuildingName();
        boolean actual = building.isValid(testCity);
        Assertions.assertFalse(actual);
    }
}
