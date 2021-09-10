package service.password;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import service.customerData.AbstractCustomerDataFactory;
import service.customerData.CustomerDateFactory;
import service.customerData.password.CountUpperCase;

import static org.mockito.MockitoAnnotations.openMocks;

public class CountUpperCaseTest {

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
    void countUpperCaseTest1()
    {
        CountUpperCase test = (CountUpperCase) abstractPasswordFactory.createValidateUpperCase();
        Assertions.assertEquals(2, test.countUpperCase("MyWorld"));
    }

    @Test
    void countUpperCaseTest2()
    {
        CountUpperCase test = (CountUpperCase) abstractPasswordFactory.createValidateUpperCase();
        Assertions.assertNotEquals(10, test.countUpperCase("MyWorld"));
    }
}
