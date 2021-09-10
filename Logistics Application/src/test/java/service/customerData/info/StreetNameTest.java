package service.customerData.info;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class StreetNameTest {

    @Test
    public void isValidTest1() {
        String testStreetName = "21 Street";
        StreetName streetName = new StreetName();
        boolean actual = streetName.isValid(testStreetName);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest2() {
        String testStreetName = "21//!Street";
        StreetName streetName = new StreetName();
        boolean actual = streetName.isValid(testStreetName);
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
