package view.payment;

import view.operation.IIO;

import java.util.HashMap;
import java.util.Map;

public class CreditCardIO implements ICardDetails{

    private String PaymentType="1";
    private IIO inputoutput;

    CreditCardIO(IIO io){
        this.inputoutput = io;
    }

    @Override
    public Map<String,String> inputDetails() {

        Map<String,String> cerditCardDetails= new HashMap<>();

        cerditCardDetails.put("PaymentType",PaymentType);

        inputoutput.writeOutput("Enter Your Credit Card Number:");
        cerditCardDetails.put("CreditCardNumber",inputoutput.readInput());

        inputoutput.writeOutput("\nEnter your CVV");
        cerditCardDetails.put("CreditCardCvv",inputoutput.readInput());

        inputoutput.writeOutput("\nEnter Card Holder's Name");
        cerditCardDetails.put("CreditCardHolderName",inputoutput.readInput());;

        inputoutput.writeOutput("\nEnter your Date of Expiry(MM/YYYY)");
        cerditCardDetails.put("CreditCardDateOfExpiry",inputoutput.readInput());

        return cerditCardDetails;
    }
}
