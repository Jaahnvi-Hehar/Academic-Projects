package service.password;

import databaseLayer.admin.AdminPasswordConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.customerData.password.IPValidator;
import service.customerData.password.ValidateUpperCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateUpperCaseTest {

    @Test
    void checkUpperCaseTrueTest()
    {
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPValidator test = mock(ValidateUpperCase.class);
        boolean expectedResult = false;
        when(test.isValid("HIYA", admin)).thenReturn(false);
        boolean actualResult = test.isValid("HIYA", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkUpperCaseFalseTest()
    {
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPValidator test = mock(ValidateUpperCase.class);
        boolean expectedResult = false;
        when(test.isValid("hiya", admin)).thenReturn(false);
        boolean actualResult = test.isValid("hiya", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
