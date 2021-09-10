package view.payment;

import view.operation.IIO;

import java.util.HashMap;
import java.util.Map;

public class CashOnDeliveryIO implements ICardDetails{

    private String paymentType = "3";
    private IIO inputoutput;

    CashOnDeliveryIO(IIO io){
        this.inputoutput = io;
    }

    @Override
    public Map<String,String> inputDetails() {

        Map<String,String> cashondelivery= new HashMap<>();

        cashondelivery.put("PaymentType",paymentType);
        inputoutput.writeOutput("You have Succesfully Choosen for Cash on Delivery \n");
        return cashondelivery;
    }

}
