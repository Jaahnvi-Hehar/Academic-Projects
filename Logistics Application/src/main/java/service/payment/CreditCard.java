package service.payment;

import databaseLayer.cards.ICardDB;
import databaseLayer.payment.IPaymentDB;
import view.operation.IIO;
import java.util.HashMap;
import java.util.Map;

public class CreditCard implements IPaymentMethod{

    private final ICardDB iCardDB;
    private final IPaymentDB iPaymentDB;
    private final IIO inputoutput;
    private final service.payment.ICreditCardVerification iCreditCardVerification;

    CreditCard(ICardDB icdb, IIO io,IPaymentDB ipdb,ICreditCardVerification iccv){
        this.iCardDB = icdb;
        this.inputoutput = io;
        this.iPaymentDB = ipdb;
        this.iCreditCardVerification = iccv;
    }

    @Override
    public boolean PaymentMethodOperation(Map<String, String> inputdetails) {
            if(iCreditCardVerification.verifyCreditCard(inputdetails)){
                String cardId = String.valueOf(iCardDB.getCardId(Long.parseLong(inputdetails.get("CreditCardNumber"))));
                Map<String,String > paymentDetails = new HashMap<>();
                paymentDetails.put("bookingid",inputdetails.get("BookingId"));
                paymentDetails.put("amount",inputdetails.get("Cost"));
                paymentDetails.put("paymentmode",inputdetails.get("PaymentType"));
                paymentDetails.put("Cardid",cardId);

                return iPaymentDB.createPayment(paymentDetails) != -1;
            }else {
                inputoutput.writeOutput("You Have Entered wrong Card Details");
                return false;
            }
    }
}
