package service.cardvalidation;

import databaseLayer.cards.ICardDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.doReturn;

public class VerifyCardDateOfExpiryTest {

    @InjectMocks
    VerifyDateOfExpiry testobject;

    @Mock
    ICardDB mockiCardDB;

    @Mock
    CardDetails mockdetails;

    @BeforeEach
    void settingup(){
        testobject = new VerifyDateOfExpiry("11/2025");
        mockdetails = Mockito.spy(new CardDetails(mockiCardDB));
    }

    @Test
    void checkTrueCardHolderName(){
        doReturn("11/2025").when(mockdetails).getDateofExpiry();
        Assertions.assertTrue(testobject.isVerified(mockdetails));
    }

    @Test
    void checkFalseCardHolderName(){
        doReturn("08/2027").when(mockdetails).getDateofExpiry();
        Assertions.assertFalse(testobject.isVerified(mockdetails));
    }

}
