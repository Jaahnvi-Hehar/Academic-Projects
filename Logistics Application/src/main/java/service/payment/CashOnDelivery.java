package service.payment;

import databaseLayer.payment.IPaymentDB;

import java.util.HashMap;
import java.util.Map;

public class CashOnDelivery implements IPaymentMethod{

    private final IPaymentDB iPaymentDB;

    CashOnDelivery(IPaymentDB ipdb){
        this.iPaymentDB = ipdb;
    }

    @Override
    public boolean PaymentMethodOperation(Map<String, String> inputdetails) {
        Map<String,String > paymentDetails = new HashMap<>();
        paymentDetails.put("bookingid",inputdetails.get("BookingId"));
        paymentDetails.put("amount",inputdetails.get("Cost"));
        paymentDetails.put("paymentmode",inputdetails.get("PaymentType"));
        paymentDetails.put("Cardid","-1");
        if(iPaymentDB.createPayment(paymentDetails)!=-1)
        {
            return true;
        }else {
            return false;
        }
    }
}
