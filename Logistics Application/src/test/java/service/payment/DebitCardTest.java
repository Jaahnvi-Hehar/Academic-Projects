package service.payment;

import databaseLayer.cards.CardDB;
import databaseLayer.cards.ICardDB;
import databaseLayer.connection.ILogisticsDatabaseConnection;
import databaseLayer.payment.IPaymentDB;
import databaseLayer.payment.PaymentDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import view.operation.IIO;
import view.operation.IO;

import java.util.HashMap;

import static org.mockito.Mockito.doReturn;

public class DebitCardTest {

    @InjectMocks
    IPaymentMethod iPaymentMethod;

    @Mock
    ILogisticsDatabaseConnection mockiLogisticsDatabaseConnection;

    @Mock
    IDebitCardVerification mockiDebitCardVerification;

    @Mock
    ICardDB mockiCardDB;

    @Mock
    IIO mockinputoutput;

    @Mock
    IPaymentDB mockiPaymentDB;

    @BeforeEach
    void settingup(){
        mockinputoutput = Mockito.spy(new IO());
        mockiCardDB = Mockito.spy(new CardDB(mockiLogisticsDatabaseConnection));
        mockiDebitCardVerification = Mockito.spy(new DebitCardVerification(mockiCardDB,mockinputoutput));
        mockiPaymentDB = Mockito.spy(new PaymentDB(mockiLogisticsDatabaseConnection));
        iPaymentMethod = new DebitCard(mockiCardDB,mockinputoutput,mockiPaymentDB,mockiDebitCardVerification);
    }

    @Test
    void trueDebitCardTest(){
        doReturn(true).when(mockiDebitCardVerification).verifyDebitCard(new HashMap<String, String>(){{
            put("PaymentType","2");
            put("DebitCardNumber","1234567890123456");
            put("DebitCardCvv","123");
            put("DebitCardHolderName","samplecard");
            put("DebitCardDateOfExpiry","11/2025");
            put("BookingId","1");
            put("Cost","10000");
        }});
        doReturn(1).when(mockiCardDB).getCardId(Long.parseLong("1234567890123456"));
        doReturn(1).when(mockiPaymentDB).createPayment(new HashMap<String, String>(){{
            put("bookingid","1");
            put("amount","10000");
            put("paymentmode","2");
            put("Cardid","1");
        }});
        Assertions.assertTrue(iPaymentMethod.PaymentMethodOperation(new HashMap<String, String>(){{
            put("PaymentType","2");
            put("DebitCardNumber","1234567890123456");
            put("DebitCardCvv","123");
            put("DebitCardHolderName","samplecard");
            put("DebitCardDateOfExpiry","11/2025");
            put("BookingId","1");
            put("Cost","10000");
        }}));
    }

    @Test
    void falseDebitCardTest(){
        doReturn(false).when(mockiDebitCardVerification).verifyDebitCard(new HashMap<String, String>(){{
            put("PaymentType","2");
            put("DebitCardNumber","1234567890123456");
            put("DebitCardCvv","123");
            put("DebitCardHolderName","samplecard");
            put("DebitCardDateOfExpiry","11/2025");
            put("BookingId","1");
            put("Cost","10000");
        }});
        doReturn(1).when(mockiCardDB).getCardId(Long.parseLong("1234567890123456"));
        doReturn(1).when(mockiPaymentDB).createPayment(new HashMap<String, String>(){{
            put("bookingid","2");
            put("amount","10000");
            put("paymentmode","1");
            put("Cardid","1");
        }});
        Assertions.assertFalse(iPaymentMethod.PaymentMethodOperation(new HashMap<String, String>(){{
            put("PaymentType","2");
            put("DebitCardNumber","1234567890123456");
            put("DebitCardCvv","123");
            put("DebitCardHolderName","samplecard");
            put("DebitCardDateOfExpiry","11/2025");
            put("BookingId","1");
            put("Cost","10000");
        }}));
    }
}
