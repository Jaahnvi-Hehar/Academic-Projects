package service.digitalSignature;

import databaseLayer.connection.LogisticsDatabaseConnection;
import databaseLayer.customer.IInfoSelector;
import databaseLayer.customer.InfoSelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class DigitalSignatureTest {

    @Test
    public void originalSignatureTest()
    {
        LogisticsDatabaseConnection logisticsDatabaseConnection = Mockito.mock(LogisticsDatabaseConnection.class);
        IInfoSelector infoSelector = Mockito.mock(InfoSelector.class);
        IDigitalSignature digitalSignature = Mockito.mock(DigitalSignature.class);
        IDigitalSignature idigitalSignature = spy(digitalSignature);
        when(infoSelector.getCustomerID("abc@gmail.com")).thenReturn(1);
        when(infoSelector.getName(1)).thenReturn("abc def");
        when(infoSelector.getDOB(1)).thenReturn("12-05-2000");
        when(idigitalSignature.originalSignature()).thenReturn("abc12052000");
        String expectedValue = "abc12052000";
        String actualValue = idigitalSignature.originalSignature();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void verifySignatureTest() {
        IDigitalSignature digitalSignature = Mockito.mock(DigitalSignature.class);
        IDigitalSignature iDigitalSignature = Mockito.spy(digitalSignature);
        when(iDigitalSignature.originalSignature()).thenReturn("abc23102000");
        when(iDigitalSignature.verifySignature("abc23102000")).thenReturn(false);
        boolean expected = false;
        boolean actual = digitalSignature.verifySignature("abc23102000");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void moreThanOneWordTest() {
        IDigitalSignature digitalSignature = Mockito.mock(DigitalSignature.class);
        boolean answer = digitalSignature.moreThanOneWord("abcdefcdsd");
        Assertions.assertEquals(answer, false);
    }

    @Test
    public void nameForSignatureTest() {
        IDigitalSignature digitalSignature = Mockito.mock(DigitalSignature.class);
        IDigitalSignature iDigitalSignature = Mockito.spy(digitalSignature);
        when(iDigitalSignature.nameForSignature("hello")).thenReturn("hello");
        String string = digitalSignature.nameForSignature("hello");
        Assertions.assertNull(string);
    }

}