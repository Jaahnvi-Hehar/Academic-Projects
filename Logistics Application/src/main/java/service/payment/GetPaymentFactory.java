package service.payment;

import databaseLayer.cards.CardDB;
import databaseLayer.cards.ICardDB;
import databaseLayer.connection.ILogisticsDatabaseConnection;
import databaseLayer.payment.PaymentDB;
import view.operation.IIO;

public class GetPaymentFactory {

    private final ILogisticsDatabaseConnection iLogisticsDatabaseConnection;
    private final IIO inputoutput;
    private final ICardDB iCardDB;

    GetPaymentFactory(IIO io,ILogisticsDatabaseConnection ildbc){
        iLogisticsDatabaseConnection = ildbc;
        this.inputoutput =io;
        iCardDB = new CardDB(iLogisticsDatabaseConnection);
    }

    public IPaymentMethod getMethod(int paymentType){

        if(paymentType==1){
            return new CreditCard(this.iCardDB,this.inputoutput,new PaymentDB(iLogisticsDatabaseConnection),new CreditCardVerification(this.iCardDB,this.inputoutput));
        }
        else if(paymentType == 2){
            return new DebitCard(this.iCardDB,this.inputoutput,new PaymentDB(iLogisticsDatabaseConnection),new DebitCardVerification(this.iCardDB,this.inputoutput));
        }
        else if(paymentType == 3){
            return new CashOnDelivery(new PaymentDB(iLogisticsDatabaseConnection));
        }
        else{
            return null;
        }
    }
}

