package service.cardvalidation;

import databaseLayer.cards.ICardDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.doReturn;

public class VerifyCardCvvTest {

    @InjectMocks
    VerifyCardCvv testobject;

    @Mock
    ICardDB mockiCardDB;

    @Mock
    CardDetails mockdetails;

    @BeforeEach
    void settingup(){
        testobject = new VerifyCardCvv("123");
        mockdetails = Mockito.spy(new CardDetails(mockiCardDB));
    }

    @Test
    void checkTrueCardBalance(){
        doReturn(123).when(mockdetails).getCardCvv();
        Assertions.assertTrue(testobject.isVerified(mockdetails));
    }

    @Test
    void checkFalseCardBalance(){
        doReturn(000).when(mockdetails).getCardCvv();
        Assertions.assertFalse(testobject.isVerified(mockdetails));
    }

}
