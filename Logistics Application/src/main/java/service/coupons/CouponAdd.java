package service.coupons;

import databaseLayer.coupons.ICouponDatabase;
import view.coupons.ICouponIO;
import view.operation.IIO;
import java.util.HashMap;
import java.util.Map;

public class CouponAdd implements ICouponAdd {

    private final ICouponIO iCouponIO;
    private final ICouponDatabase iCouponDatabase;
    private final IIO inputOutput;

    public CouponAdd(ICouponDatabase Icd, ICouponIO icio, IIO iio){
        this.iCouponDatabase=Icd;
        this.iCouponIO=icio;
        this.inputOutput = iio;
    }

    @Override
    public int addCoupon() {

        int couponid;
        Map<String,String> couponinfo = new HashMap<>();

        couponinfo.put("couponcode", iCouponIO.inputCouponCode());
        couponinfo.put("discount", String.valueOf(iCouponIO.inputDiscount()));
        couponinfo.put("isactive", String.valueOf(iCouponIO.inputIsActive()));
        couponinfo.put("minorderamt", String.valueOf(iCouponIO.inputMinOrderAmount()));
        couponinfo.put("maxdiscountamt", String.valueOf(iCouponIO.inputMaxDiscountAmount()));

        couponid = iCouponDatabase.insertCoupon(couponinfo);
        if(couponid==-1){
            inputOutput.writeOutput("Some Error Genrating the Coupon");
        }
        else{
            inputOutput.writeOutput("Successfully genrated the Coupon");
        }
        return  couponid;
    }
}
