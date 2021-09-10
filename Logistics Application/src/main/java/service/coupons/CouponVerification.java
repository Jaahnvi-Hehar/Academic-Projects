package service.coupons;

import databaseLayer.coupons.ICouponDatabase;
import java.util.Map;

public class CouponVerification implements ICouponVerification{

    ICouponDatabase iCouponDatabase;

    public CouponVerification(ICouponDatabase iCouponDatabase){
        this.iCouponDatabase = iCouponDatabase;
    }

    @Override
    public boolean verifyCouponCode(String code,float Amt) {
        Map<String,String> couponinfo = iCouponDatabase.getCouponDetails(code.toUpperCase());
        boolean isverified=false;

        isverified= couponinfo != null && Integer.parseInt(couponinfo.get("isactive")) == 1 && Integer.parseInt(couponinfo.get("minorderamt")) <= Amt;
        return isverified;
    }
}
