package service.customerData.info;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;

public class ContactNoTest {

    @Test
    public void isValidTest1() {
        String testContactNo = "9012345678";
        ContactNo contactNo = new ContactNo();
        boolean actual = contactNo.isValid(testContactNo);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest2() {
        String testContactNo = "--27678877";
        ContactNo contactNo = new ContactNo();
        boolean actual = contactNo.isValid(testContactNo);
        Assertions.assertFalse(actual);
    }

    @Test
    public void isValidTest3() {
        String testContactNo = "";
        ContactNo contactNo = new ContactNo();
        boolean actual = contactNo.isValid(testContactNo);
        Assertions.assertFalse(actual);
    }
}
