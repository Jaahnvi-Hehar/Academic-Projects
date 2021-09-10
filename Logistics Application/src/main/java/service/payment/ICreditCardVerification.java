package service.payment;

import java.util.Map;

public interface ICreditCardVerification {

    public boolean verifyCreditCard(Map<String,String> creditCardDetails);
}
