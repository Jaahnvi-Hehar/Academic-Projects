package service.coupons;

import databaseLayer.coupons.ICouponDatabase;

public class CouponIOValidation implements ICouponIOValidation {

    private final ICouponDatabase iCouponDatabase;
    public CouponIOValidation(ICouponDatabase icd){
        this.iCouponDatabase = icd;
    }

    @Override
    public boolean validateCouponCode(String code) {
        boolean isvalid;
        isvalid = code.length() <= 8 && code.length() >= 4 && iCouponDatabase.getCouponDetails(code) == null;
        return isvalid;
    }

    @Override
    public boolean validateDiscount(int discount) {
        boolean isvalid;
        isvalid = discount > 0 && discount < 100;
        return isvalid;
    }

    @Override
    public boolean validateIsActive(byte isactive){
        boolean isvalid;
        isvalid = isactive == 0 || isactive == 1;
        return isvalid;
        }

    @Override
    public boolean validateMinOrderAmount(int minorderamount){
        boolean isvalid;
        isvalid = minorderamount > 0 && minorderamount < 1000;
        return isvalid;
    }

    @Override
    public boolean validateMaxDiscountAmount(int maxdiscountamount){
        boolean isvalid;
        isvalid = maxdiscountamount > 0 && maxdiscountamount < 500;
        return isvalid;
    }
}
