package service.customerData.info;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class NameTest {

    @Test
    public void isValidTest1() {
        String testName = "Jacob Watson";
        Name name = new Name();
        boolean actual = name.isValid(testName);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest2() {
        String testName = "Jacob#1";
        Name name = new Name();
        boolean actual = name.isValid(testName);
        Assertions.assertFalse(actual);
    }

    @Test
    public void isValidTest3() {
        String testStreetName = "";
        StreetName streetName = new StreetName();
        boolean actual = streetName.isValid(testStreetName);
        Assertions.assertFalse(actual);
    }
}
