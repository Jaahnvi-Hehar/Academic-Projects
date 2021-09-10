package service.password;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import service.customerData.AbstractCustomerDataFactory;
import service.customerData.CustomerDateFactory;
import service.customerData.password.CountLowerCase;

import static org.mockito.MockitoAnnotations.openMocks;

public class CountLowerCaseTest {

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
    void countLowerCaseTest1() {
        CountLowerCase test = (CountLowerCase) abstractPasswordFactory.createValidateLowerCase();
        Assertions.assertEquals(4, test.countLowerCase("Hello"));
    }

    @Test
    void countLowerCaseTest2() {
        CountLowerCase test = (CountLowerCase) abstractPasswordFactory.createValidateLowerCase();
        Assertions.assertNotEquals(4, test.countLowerCase("Yeah Last Case"));
    }
}
