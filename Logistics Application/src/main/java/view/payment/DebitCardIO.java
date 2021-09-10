package view.payment;

import view.operation.IIO;

import java.util.HashMap;
import java.util.Map;

public class DebitCardIO implements ICardDetails{

    private String PaymentType="2";
    private IIO inputoutput;

    DebitCardIO(IIO io){
        this.inputoutput = io;
    }

    @Override
    public Map<String,String> inputDetails() {

        Map<String,String> debitCardDetails = new HashMap<>();

        debitCardDetails.put("PaymentType",PaymentType);

        inputoutput.writeOutput("Enter Your Debit Card Number:");
        debitCardDetails.put("DebitCardNumber",inputoutput.readInput());

        inputoutput.writeOutput("\nEnter your CVV");
        debitCardDetails.put("DebitCardCvv",inputoutput.readInput());

        inputoutput.writeOutput("\nEnter Card Holder's Name");
        debitCardDetails.put("DebitCardHolderName",inputoutput.readInput());

        inputoutput.writeOutput("\nEnter your Date of Expiry(MM/YYYY)");
        debitCardDetails.put("DebitCardDateOfExpiry",inputoutput.readInput());

        return debitCardDetails;
    }
}
