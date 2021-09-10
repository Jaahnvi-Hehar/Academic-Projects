package service.payment;

import java.util.Map;

public interface IDebitCardVerification {

    public boolean verifyDebitCard(Map<String,String> debitCardDetails);
}
