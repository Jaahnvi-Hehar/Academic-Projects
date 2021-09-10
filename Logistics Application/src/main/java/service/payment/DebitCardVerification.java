package service.payment;

import databaseLayer.cards.ICardDB;
import service.cardvalidation.*;
import view.operation.IIO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DebitCardVerification implements IDebitCardVerification{

    private final ICardDB iCardDB;
    private CardDetails cardDetails;
    private final IIO inputoutput;

    List<CardValidator> verifiers= new ArrayList<>();

    DebitCardVerification(ICardDB icdb,IIO io){
        this.iCardDB = icdb;
        this.inputoutput = io;
    }

    @Override
    public boolean verifyDebitCard(Map<String, String> debitCardDetails) {
        boolean flag = true;
        this.cardDetails = new CardDetails(iCardDB);
        if(cardDetails.setValues(Long.parseLong(debitCardDetails.get("DebitCardNumber")))) {

            verifiers.add(new VerifyCardNumber(debitCardDetails.get("DebitCardNumber")));
            verifiers.add(new VerifyCardBalance(debitCardDetails.get("Cost")));
            verifiers.add(new VerifyCardCvv(debitCardDetails.get("DebitCardCvv")));
            verifiers.add(new VerifyCardHolderName(debitCardDetails.get("DebitCardHolderName")));
            verifiers.add(new VerifyDateOfExpiry(debitCardDetails.get("DebitCardDateOfExpiry")));
            verifiers.add(new VerifyCardType(debitCardDetails.get("PaymentType")));

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
