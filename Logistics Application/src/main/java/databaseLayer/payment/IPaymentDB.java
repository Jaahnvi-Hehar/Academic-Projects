package databaseLayer.payment;

import java.util.Map;

public interface IPaymentDB {

    public int createPayment(Map<String,String> paymentdetails);

}
