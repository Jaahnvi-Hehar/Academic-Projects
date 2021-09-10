package service.customerData.info;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DateOfBirthTest {

    @Test
    public void isValidTest1() {
        String testDateOfBirth = "01-01-2005";
        DateOfBirth dateOfBirth = new DateOfBirth();
        boolean actual = dateOfBirth.isValid(testDateOfBirth);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest2() {
        String testDateOfBirth = "01|01|2005";
        DateOfBirth dateOfBirth = new DateOfBirth();
        boolean actual = dateOfBirth.isValid(testDateOfBirth);
        Assertions.assertFalse(actual);
    }

    @Test
    public void isValidTest3() {
        String testDateOfBirth = "";
        DateOfBirth dateOfBirth = new DateOfBirth();
        boolean actual = dateOfBirth.isValid(testDateOfBirth);
        Assertions.assertFalse(actual);
    }

}
