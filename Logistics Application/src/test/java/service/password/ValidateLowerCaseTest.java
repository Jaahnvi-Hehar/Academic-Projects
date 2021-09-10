package service.password;

import databaseLayer.admin.AdminPasswordConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.customerData.password.IPValidator;
import service.customerData.password.ValidateLowerCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateLowerCaseTest {

    @Test
    void checkLowerCaseTrueTest()
    {
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPValidator test = mock(ValidateLowerCase.class);
        boolean expectedResult = true;
        when(test.isValid("hello", admin)).thenReturn(true);
        boolean actualResult = test.isValid("hello", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkLowerCaseFalseTest()
    {
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPValidator test = mock(ValidateLowerCase.class);
        boolean expectedResult = false;
        when(test.isValid("hello", admin)).thenReturn(false);
        boolean actualResult = test.isValid("hello", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }

}
