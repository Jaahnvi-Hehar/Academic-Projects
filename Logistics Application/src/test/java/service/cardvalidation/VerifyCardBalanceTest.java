package service.cardvalidation;

import databaseLayer.cards.ICardDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;

public class VerifyCardBalanceTest {

    @InjectMocks
    VerifyCardBalance testobject;

    @Mock
    ICardDB mockiCardDB;

    @Mock
    CardDetails mockdetails;

    @BeforeEach
    void settingup(){
        testobject = new VerifyCardBalance("5000");
        mockdetails = Mockito.spy(new CardDetails(mockiCardDB));
    }

    @Test
    void checkTrueCardBalance(){
        doReturn(50000).when(mockdetails).getCardBalance();
        Assertions.assertTrue(testobject.isVerified(mockdetails));
    }

    @Test
    void checkFalseCardBalance(){
        doReturn(500).when(mockdetails).getCardBalance();
        Assertions.assertFalse(testobject.isVerified(mockdetails));
    }
}
