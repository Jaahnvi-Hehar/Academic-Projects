package service.password;

import databaseLayer.admin.AdminPasswordConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.customerData.password.IPValidator;
import service.customerData.password.ValidateLength;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateLengthTest {


    @Test
    void checkTrueLength() {
        IPValidator test = new ValidateLength();
        AdminPasswordConfig admin = mock(AdminPasswordConfig.class);
        Assertions.assertTrue(test.isValid("Hello1234", admin));
    }

    @Test
    void checkFalseLength() {

        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPValidator test = mock(ValidateLength.class);
        boolean expectedResult = false;
        when(test.isValid("Hello", admin)).thenReturn(false);
        boolean actualResult = test.isValid("Hello", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }

}

