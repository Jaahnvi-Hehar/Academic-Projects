package service.customerData.info;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PostalCodeTest {

    @Test
    public void isValidTest1() {
        String testPostalCode = "100002";
        PostalCode postalCode = new PostalCode();
        boolean actual = postalCode.isValid(testPostalCode);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest2() {
        String testPostalCode = "1@@@@2";
        PostalCode postalCode = new PostalCode();
        boolean actual = postalCode.isValid(testPostalCode);
        Assertions.assertFalse(actual);
    }

    @Test
    public void isValidTest3() {
        String testPostalCode = "";
        PostalCode postalCode = new PostalCode();
        boolean actual = postalCode.isValid(testPostalCode);
        Assertions.assertFalse(actual);
    }
}
