package view.coupons;

public interface ICouponIO {

    public String inputCouponCode();
    public int inputDiscount();
    public byte inputIsActive();
    public int inputMinOrderAmount();
    public int inputMaxDiscountAmount();

}
