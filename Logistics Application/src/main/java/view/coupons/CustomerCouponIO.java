package view.coupons;

import view.operation.IIO;

public class CustomerCouponIO implements ICustomerCouponIO{

    private IIO inputoutput;

    public CustomerCouponIO(IIO io){
        this.inputoutput = io;
    }

    @Override
    public String CouponCode() {
        inputoutput.writeOutput("Enter the Coupon Code");
        return inputoutput.readInput();
    }
}
