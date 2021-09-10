package databaseLayer.coupons;

import java.sql.ResultSet;
import java.util.Map;

public interface ICouponDatabase {

    public int insertCoupon(Map<String,String> details);
    public ResultSet viewCouponsAdmin();
    public ResultSet viewCouponsCustomers();
    public Map<String,String> getCouponDetails(String code);
    public int getDiscountPercentage(String Code);
    public int getMaxAmount(String Code);
}
