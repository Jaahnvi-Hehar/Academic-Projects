package service.customerData.info;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HouseNoTest {

    @Test
    public void isValidTest1() {
        String houseNoTest = "Flat No. 21";
        HouseNo houseNo = new HouseNo();
        boolean actual = houseNo.isValid(houseNoTest);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidTest2() {
        String houseNoTest = "";
        HouseNo houseNo = new HouseNo();
        boolean actual = houseNo.isValid(houseNoTest);
        Assertions.assertFalse(actual);
    }
}
