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

public class CashOnDeliveryTest {

    @InjectMocks
    IPaymentMethod iPaymentMethod;

    @Mock
    ILogisticsDatabaseConnection mockiLogisticsDatabaseConnection;

    @Mock
    IPaymentDB mockiPaymentDB;

    @BeforeEach
    void settingup(){
        mockiPaymentDB = Mockito.spy(new PaymentDB(mockiLogisticsDatabaseConnection));
        iPaymentMethod = new CashOnDelivery(mockiPaymentDB);
    }

    @Test
    void trueCashOnDeliveryTest(){
        doReturn(12).when(mockiPaymentDB).createPayment(new HashMap<String, String>(){{
            put("bookingid","1");
            put("amount","10000");
            put("paymentmode","3");
            put("Cardid","-1");
        }});
        Assertions.assertTrue(iPaymentMethod.PaymentMethodOperation(new HashMap<String, String>(){{
            put("BookingId","1");
            put("Cost","10000");
            put("PaymentType","3");
            put("Cardid","-1");
        }}));
    }

    @Test
    void falseCashOnDeliveryTest(){
        doReturn(-1).when(mockiPaymentDB).createPayment(new HashMap<String, String>(){{
            put("bookingid","1");
            put("amount","10000");
            put("paymentmode","3");
            put("Cardid","-1");
        }});
        Assertions.assertFalse(iPaymentMethod.PaymentMethodOperation(new HashMap<String, String>(){{
            put("BookingId","1");
            put("Cost","10000");
            put("PaymentType","3");
            put("Cardid","-1");
        }}));
    }
}
