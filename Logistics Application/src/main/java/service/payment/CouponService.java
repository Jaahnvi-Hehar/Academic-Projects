package service.payment;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import databaseLayer.coupons.CouponDatabase;
import databaseLayer.coupons.ICouponDatabase;
import service.coupons.CouponDisplay;
import service.coupons.CouponVerification;
import service.coupons.ICouponDisplay;
import service.coupons.ICouponVerification;
import view.coupons.CustomerCouponIO;
import view.coupons.ICustomerCouponIO;
import view.operation.IIO;

public class CouponService implements ICouponService {

    private final IIO inputoutput;
    private final ILogisticsDatabaseConnection iLogisticsDatabaseConnection;
    private final ICouponVerification iCouponVerification;
    private final ICouponDisplay iCouponDisplay;
    private final ICustomerCouponIO iCustomerCouponIO;
    private final ICouponDatabase iCouponDatabase;

    public CouponService(IIO iio, ILogisticsDatabaseConnection ildbc){
        this.inputoutput = iio;
        this.iLogisticsDatabaseConnection = ildbc;
        this.iCouponDatabase = new CouponDatabase(this.iLogisticsDatabaseConnection);
        this.iCouponVerification = new CouponVerification(this.iCouponDatabase);
        this.iCouponDisplay = new CouponDisplay(this.iCouponDatabase,this.inputoutput);
        this.iCustomerCouponIO = new CustomerCouponIO(this.inputoutput);
    }

    @Override
    public float couponOperation(float cost) {

        iCouponDisplay.displayCouponToCustomer();

        String code;
        if(iCouponVerification.verifyCouponCode(code = iCustomerCouponIO.CouponCode(),cost)){
            float DiscountAmount = cost * iCouponDatabase.getDiscountPercentage(code) / 100;
            float MaxAmount = iCouponDatabase.getMaxAmount(code);
            if(DiscountAmount > MaxAmount){
                return cost - MaxAmount;
            }else{
                return cost - DiscountAmount;
            }
        }else{
            return cost;
        }
    }
}
