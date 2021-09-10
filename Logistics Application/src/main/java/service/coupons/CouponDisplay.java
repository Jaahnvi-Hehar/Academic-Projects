package service.coupons;

import databaseLayer.coupons.ICouponDatabase;
import view.operation.IIO;

import java.sql.ResultSet;

public class CouponDisplay implements ICouponDisplay {

    private final ICouponDatabase icd;
    private final IIO inputOutput;

    public CouponDisplay(ICouponDatabase icoupondatabase, IIO iio){
        this.icd = icoupondatabase;
        this.inputOutput = iio;
    }

    @Override
    public void displayCouponToCustomer() {
        ResultSet couponinfo = icd.viewCouponsCustomers();
        this.displayCoupons(couponinfo);
    }

    @Override
    public void displayCouponToAdmin() {
        ResultSet couponinfo = icd.viewCouponsAdmin();
        this.displayCoupons(couponinfo);
    }

    public void displayCoupons(ResultSet couponinfo){
        try {
            inputOutput.writeOutput("Available Coupons\n");
            while (couponinfo.next()) {
                inputOutput.writeOutput("Coupon Code:" + couponinfo.getString("CouponCode"));
                inputOutput.writeOutput("\tDiscount Percentage:" + couponinfo.getInt("Discount"));
                inputOutput.writeOutput("\tIsActive:" + couponinfo.getInt("IsActive"));
                inputOutput.writeOutput("\tMinimum Order Amount:" + couponinfo.getInt("MinOrderAmt"));
                inputOutput.writeOutput("\tMaximum Discount Money:" + couponinfo.getInt("MaxDiscountAmt") + "\n");
            }
        }
        catch (Exception e){
            inputOutput.writeOutput("Errro Fetching the Coupons");
        }
    }
}
