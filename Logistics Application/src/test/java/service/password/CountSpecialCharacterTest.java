package service.password;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import service.customerData.AbstractCustomerDataFactory;
import service.customerData.CustomerDateFactory;
import service.customerData.password.CountSpecialCharacter;

import static org.mockito.MockitoAnnotations.openMocks;

public class CountSpecialCharacterTest {

    @Mock
    AbstractCustomerDataFactory abstractPasswordFactory;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException {
        mockitoCloseable = openMocks(this);
        abstractPasswordFactory = new CustomerDateFactory();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void countSpecialCharacterTest()
    {
        CountSpecialCharacter test = (CountSpecialCharacter) abstractPasswordFactory.createValidateSpecialCharacter();
        Assertions.assertEquals(2, test.countSpecialCharacter("#Wo@h"));
    }

    @Test
    void specialCharacterTest()
    {
        CountSpecialCharacter test = (CountSpecialCharacter) abstractPasswordFactory.createValidateSpecialCharacter();
        Assertions.assertNotEquals(4, test.specialCharacter('@'));
    }

}
