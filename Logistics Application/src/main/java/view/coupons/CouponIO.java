package view.coupons;

import service.coupons.ICouponIOValidation;
import view.operation.IIO;

public class CouponIO implements ICouponIO {

    private IIO inputOutput;
    private ICouponIOValidation iCouponIOValidation;

    public CouponIO(IIO io, ICouponIOValidation icv){
        this.inputOutput = io;
        this.iCouponIOValidation = icv;
    }

    @Override
    public String inputCouponCode() {
        inputOutput.writeOutput("Coupon Code:");
        String code = inputOutput.readInput();
        if(iCouponIOValidation.validateCouponCode(code)) {
            return code;
        }else{
            inputOutput.writeOutput("Enter a Code between 4-8 Characters or CouponCode Already Exists!!!\n");
            code=inputCouponCode();
        }
        return code;
    }

    @Override
    public int inputDiscount() {
        int discount=0;
        try{
            inputOutput.writeOutput("Discount Percentage:");
            discount = Integer.parseInt(inputOutput.readInput());
            if(iCouponIOValidation.validateDiscount(discount)) {
                return discount;
            }else{
                inputOutput.writeOutput("Enter Discount Percentage between 0-100 !!!\n");
                discount=inputDiscount();
            }
        }
        catch (Exception e){
            inputOutput.writeOutput("Please Enter a Number\n");
            discount=inputDiscount();
        }
        return discount;
    }

    @Override
    public byte inputIsActive() {

        byte isactive=0;
        try{
            inputOutput.writeOutput("Is Active ? (Enter 0 for Active and 1 for Inactive):");
            isactive = Byte.parseByte(inputOutput.readInput());
            if(iCouponIOValidation.validateIsActive(isactive)) {
                return isactive;
            }else{
                inputOutput.writeOutput("Enter 0 or 1\n");
               isactive= inputIsActive();
            }
        }
        catch (Exception e){
            inputOutput.writeOutput("Please Enter 0 or 1\n");
            isactive=inputIsActive();
        }

        return isactive;
    }

    @Override
    public int inputMinOrderAmount() {

        int minorderamt=0;
        try{
            inputOutput.writeOutput("Minimum Order Amount:");
            minorderamt = Integer.parseInt(inputOutput.readInput());
            if(iCouponIOValidation.validateMinOrderAmount(minorderamt)) {
                return minorderamt;
            }else{
                inputOutput.writeOutput("Enter Minimum Order Amount between 0-100 \n");
                minorderamt=inputMinOrderAmount();
            }
        }
        catch (Exception e){
            inputOutput.writeOutput("Please Enter a Number\n");
            minorderamt=inputMinOrderAmount();
        }

        return minorderamt;
    }

    @Override
    public int inputMaxDiscountAmount() {

        int maxdiscountamt=0;
        try{
            inputOutput.writeOutput("Maximum Discount Amount:");
             maxdiscountamt = Integer.parseInt(inputOutput.readInput());
            if(iCouponIOValidation.validateMaxDiscountAmount(maxdiscountamt)) {
                return maxdiscountamt;
            }else{
                inputOutput.writeOutput("Enter Maximum Discount Amount between 0-500 \n");
                maxdiscountamt=inputMaxDiscountAmount();
            }
        }
        catch (Exception e){
            inputOutput.writeOutput("Please Enter a Number\n");
            maxdiscountamt=inputMaxDiscountAmount();
        }

        return maxdiscountamt;
    }
}
