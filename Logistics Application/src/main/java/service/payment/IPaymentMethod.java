package service.payment;

import java.util.Map;

public interface IPaymentMethod {

    public boolean PaymentMethodOperation(Map<String,String> inputdetails);

}
