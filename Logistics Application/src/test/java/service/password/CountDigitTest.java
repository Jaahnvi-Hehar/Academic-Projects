package service.password;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import service.customerData.AbstractCustomerDataFactory;
import service.customerData.CustomerDateFactory;
import service.customerData.password.CountDigit;

import static org.mockito.MockitoAnnotations.openMocks;

public class CountDigitTest {

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
    void countDigitTest1() {
        CountDigit test = (CountDigit) abstractPasswordFactory.createValidateDigit();
        Assertions.assertEquals(3, test.countDigit("Hello123"));
    }

    @Test
    void countDigitTest2() {
        CountDigit test = (CountDigit) abstractPasswordFactory.createValidateDigit();
        Assertions.assertNotEquals(4, test.countDigit("hey23"));
    }

    @Test
    void countDigitTest3() {
        CountDigit test = (CountDigit) abstractPasswordFactory.createValidateDigit();
        Assertions.assertEquals(0, test.countDigit("hey"));
    }

}
