package service.customerData.info;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AreaTest {

    @Test
    public void isValidTest1() {
        String testArea = "21 Area";
        Area area = new Area();
        boolean actual = area.isValid(testArea);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest2() {
        String testArea = "21@Area";
        Area area = new Area();
        boolean actual = area.isValid(testArea);
        Assertions.assertFalse(actual);
    }

    @Test
    public void isValidTest3() {
        String testArea = "";
        Area area = new Area();
        boolean actual = area.isValid(testArea);
        Assertions.assertFalse(actual);
    }
}
