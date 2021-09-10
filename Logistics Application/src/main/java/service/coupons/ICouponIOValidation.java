package service.coupons;

public interface ICouponIOValidation {

    public boolean validateCouponCode(String code);
    public boolean validateDiscount(int discount);
    public boolean validateIsActive(byte isactive);
    public boolean validateMinOrderAmount(int minorderamount);
    public boolean validateMaxDiscountAmount(int maxdiscountamount);

}
