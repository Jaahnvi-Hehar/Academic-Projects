package service.cardvalidation;

import databaseLayer.cards.ICardDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.doReturn;

public class VerifyCardTypeTest {

    @InjectMocks
    VerifyCardType testobject;

    @Mock
    ICardDB mockiCardDB;

    @Mock
    CardDetails mockdetails;

    @BeforeEach
    void settingup(){
        testobject = new VerifyCardType("1");
        mockdetails = Mockito.spy(new CardDetails(mockiCardDB));
    }

    @Test
    void checkTrueCardHolderName(){
        doReturn(1).when(mockdetails).getCardType();
        Assertions.assertTrue(testobject.isVerified(mockdetails));
    }

    @Test
    void checkFalseCardHolderName(){
        doReturn(2).when(mockdetails).getCardType();
        Assertions.assertFalse(testobject.isVerified(mockdetails));
    }
}
