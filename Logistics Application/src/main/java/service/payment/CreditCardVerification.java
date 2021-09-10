package service.payment;

import databaseLayer.cards.ICardDB;
import service.cardvalidation.*;
import view.operation.IIO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreditCardVerification implements ICreditCardVerification{

    private final ICardDB iCardDB;
    private CardDetails cardDetails;
    private final IIO inputoutput;

    List<CardValidator> verifiers= new ArrayList<>();

    CreditCardVerification(ICardDB icdb,IIO io){
        this.iCardDB = icdb;
        this.inputoutput = io;
    }

    @Override
    public boolean verifyCreditCard(Map<String, String> creditCardDetails) {
        boolean flag = true;
        this.cardDetails = new CardDetails(iCardDB);
        if(cardDetails.setValues(Long.parseLong(creditCardDetails.get("CreditCardNumber")))) {

            verifiers.add(new VerifyCardNumber(creditCardDetails.get("CreditCardNumber")));
            verifiers.add(new VerifyCardBalance(creditCardDetails.get("Cost")));
            verifiers.add(new VerifyCardCvv(creditCardDetails.get("CreditCardCvv")));
            verifiers.add(new VerifyCardHolderName(creditCardDetails.get("CreditCardHolderName")));
            verifiers.add(new VerifyDateOfExpiry(creditCardDetails.get("CreditCardDateOfExpiry")));
            verifiers.add(new VerifyCardType(creditCardDetails.get("PaymentType")));

            for (CardValidator verifier : verifiers) {
                if (verifier.isVerified(this.cardDetails)) {
                    continue;
                } else {
                    flag = false;
                    break;
                }
            }
        }else{
            inputoutput.writeOutput("Card Does not Exists");
            return false;
        }
        return flag;
    }
}
