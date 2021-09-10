package service.payment;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import view.operation.IIO;
import view.payment.IPaymentIO;
import view.payment.PaymentIO;
import java.util.Map;

public class PaymentService implements IPaymentService{

    private final IPaymentIO iPaymentIO;
    private final IIO inputoutput;
    private final ICouponService iCouponService;
    private final ILogisticsDatabaseConnection iLogisticsDatabaseConnection;

    public PaymentService(IIO iio,ILogisticsDatabaseConnection ildbc)
    {
        this.inputoutput = iio;
        this.iLogisticsDatabaseConnection = ildbc;
        this.iPaymentIO = new PaymentIO(this.inputoutput);
        this.iCouponService = new CouponService(this.inputoutput,this.iLogisticsDatabaseConnection);
    }

    public void PaymentOperation(int bookingid,float cost){

        inputoutput.writeOutput("Do you wish to enter a Coupon Code ???");
        String readChoice = inputoutput.readInput();

        if (readChoice.equalsIgnoreCase("Y")){
            cost = iCouponService.couponOperation(cost);
        }

        GetPaymentFactory paymentFactory = new GetPaymentFactory(this.inputoutput,this.iLogisticsDatabaseConnection);
        Map<String,String> paymentinfo =  iPaymentIO.paymentui();
        paymentinfo.put("BookingId",String.valueOf(bookingid));
        paymentinfo.put("Cost",String.valueOf(cost));

        IPaymentMethod iPaymentMethod = paymentFactory.getMethod(Integer.parseInt(paymentinfo.get("PaymentType")));

        if(iPaymentMethod.PaymentMethodOperation(paymentinfo)){
            inputoutput.writeOutput("Payment Succesfully Completed");
        }else {
            inputoutput.writeOutput("Payment Could Not be processed Please try Again");
        }
    }
}
