package service.payment;

import databaseLayer.cards.ICardDB;
import databaseLayer.payment.IPaymentDB;
import view.operation.IIO;
import java.util.HashMap;
import java.util.Map;

public class DebitCard implements IPaymentMethod{

    private final ICardDB iCardDB;
    private final IIO inputoutput;
    private final IPaymentDB iPaymentDB;
    private final IDebitCardVerification iDebitCardVerification;

    DebitCard(ICardDB icdb, IIO io,IPaymentDB ipdb,IDebitCardVerification idcv){
        this.iCardDB = icdb;
        this.inputoutput = io;
        this.iPaymentDB = ipdb;
        this.iDebitCardVerification=idcv;
    }

    @Override
    public boolean PaymentMethodOperation(Map<String, String> inputdetails) {
        if(iDebitCardVerification.verifyDebitCard(inputdetails)){
            String cardId = String.valueOf(iCardDB.getCardId(Long.parseLong(inputdetails.get("DebitCardNumber"))));
            Map<String,String > paymentDetails = new HashMap<>();
            paymentDetails.put("bookingid",inputdetails.get("BookingId"));
            paymentDetails.put("amount",inputdetails.get("Cost"));
            paymentDetails.put("paymentmode",inputdetails.get("PaymentType"));
            paymentDetails.put("Cardid",cardId);

            return iPaymentDB.createPayment(paymentDetails) != -1;
        }else {
            inputoutput.writeOutput("You Have Entered wrong Debit Card Details");
            return false;
        }
    }
}
