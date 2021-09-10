package service.cardvalidation;

import databaseLayer.cards.ICardDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.doReturn;

public class VerifyCardHolderNameTest {

    @InjectMocks
    VerifyCardHolderName testobject;

    @Mock
    ICardDB mockiCardDB;

    @Mock
    CardDetails mockdetails;

    @BeforeEach
    void settingup(){
        testobject = new VerifyCardHolderName("Sample Card");
        mockdetails = Mockito.spy(new CardDetails(mockiCardDB));
    }

    @Test
    void checkTrueCardHolderName(){
        doReturn("sample card").when(mockdetails).getCardHolderName();
        Assertions.assertTrue(testobject.isVerified(mockdetails));
    }

    @Test
    void checkFalseCardHolderName(){
        doReturn("xyz abc").when(mockdetails).getCardHolderName();
        Assertions.assertFalse(testobject.isVerified(mockdetails));
    }
}
