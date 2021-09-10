package service.password;

import databaseLayer.admin.AdminPasswordConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.customerData.password.IPValidator;
import service.customerData.password.ValidateSpecialCharacter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateSpecialCharacterTest {

    @Test
    void checkSpecialCaseTrueTest()
    {
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPValidator test = mock(ValidateSpecialCharacter.class);
        boolean expectedResult = true;
        when(test.isValid("hello@##123", admin)).thenReturn(true);
        boolean actualResult = test.isValid("hello@##123", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkSpecialCaseFalseTest()
    {
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPValidator test = mock(ValidateSpecialCharacter.class);
        boolean expectedResult = false;
        when(test.isValid("hello", admin)).thenReturn(false);
        boolean actualResult = test.isValid("hello@", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }

}
