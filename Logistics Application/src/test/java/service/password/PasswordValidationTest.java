package service.password;

import databaseLayer.admin.AdminPasswordConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.customerData.IPasswordValidator;
import service.customerData.PasswordValidator;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PasswordValidationTest {

    @Test
    void checkPasswordValidationFalseTest()
    {
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPasswordValidator test = mock(PasswordValidator.class);
        List<Boolean> expectedResult = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            expectedResult.add(true);
        }
        when(test.isPasswordValid("", admin)).thenReturn(expectedResult);
        List<Boolean> actualResult = test.isPasswordValid("", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkPasswordValidationTrueTest()
    {
        AdminPasswordConfig admin = Mockito.mock(AdminPasswordConfig.class);
        IPasswordValidator test = mock(PasswordValidator.class);
        List<Boolean> expectedResult = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            expectedResult.add(false);
        }
        when(test.isPasswordValid("HELLOworld@1234", admin)).thenReturn(expectedResult);
        List<Boolean> actualResult = test.isPasswordValid("HELLOworld@1234", admin);
        Assertions.assertEquals(expectedResult, actualResult);
    }


}
