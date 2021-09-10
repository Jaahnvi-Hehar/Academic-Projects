package service.cardvalidation;

import databaseLayer.cards.ICardDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.doReturn;

public class VerifyCardNumberTest {

    @InjectMocks
    VerifyCardNumber testobject;

    @Mock
    ICardDB mockiCardDB;

    @Mock
    CardDetails mockdetails;

    @BeforeEach
    void settingup(){
        testobject = new VerifyCardNumber("1234567890123456");
        mockdetails = Mockito.spy(new CardDetails(mockiCardDB));
    }

    @Test
    void checkTrueCardNumber(){
        doReturn(Long.parseLong("1234567890123456")).when(mockdetails).getCardNumber();
        Assertions.assertTrue(testobject.isVerified(mockdetails));
    }

    @Test
    void checkFalseCardNumber(){
        doReturn(Long.parseLong("123456789")).when(mockdetails).getCardNumber();
        Assertions.assertFalse(testobject.isVerified(mockdetails));
    }
}
