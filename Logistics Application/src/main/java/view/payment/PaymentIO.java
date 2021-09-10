package view.payment;

import view.operation.IIO;

import java.util.Map;

public class PaymentIO implements IPaymentIO{

    private IIO inputoutput;
    private ICardDetails iCardDetails;
    private int choice;

    public PaymentIO(IIO io){
        this.inputoutput = io;
    }


    @Override
    public Map<String,String> paymentui() {
        inputoutput.writeOutput("If you are satisfied with the billing amount enter then enter your mode of payment:");
        inputoutput.writeOutput("1.Credit Card");
        inputoutput.writeOutput("2.Debit Card");
        inputoutput.writeOutput("3.Cash on Delivery");
        choice = Integer.parseInt(inputoutput.readInput());

        if(choice == 1){
            iCardDetails = new CreditCardIO(this.inputoutput);
        }
        else if(choice == 2){
            iCardDetails = new DebitCardIO(this.inputoutput);
        }
        else if(choice == 3){
            iCardDetails = new CashOnDeliveryIO(this.inputoutput);
        }else{
            inputoutput.writeOutput("You entered an Invalid choice");
        }

        Map<String,String > paymentDetails = iCardDetails.inputDetails();

        return paymentDetails;
    }
}
