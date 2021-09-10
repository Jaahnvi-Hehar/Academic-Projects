package service.password;

import databaseLayer.admin.AdminPasswordConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.customerData.password.IPValidator;
import service.customerData.password.ValidateDigit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateDigitTest {

    @Test
    void checkTrueDigit()
    {
        IPValidator test = new ValidateDigit();
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        Assertions.assertTrue(test.isValid("Hello123", admin));
    }

    @Test
    void checkFalseDigit()
    {
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPValidator test = mock(ValidateDigit.class);
        boolean expectedResult = false;
        when(test.isValid("Hello", admin)).thenReturn(false);
        boolean actualResult = test.isValid("Hello", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }

}
