package service.cardvalidation;

public class VerifyCardHolderName implements CardValidator{

    String inputvalue;

    public VerifyCardHolderName(String iv){
        this.inputvalue = iv;
    }

    @Override
    public boolean isVerified(CardDetails ActualValue) {
        if(this.inputvalue.equalsIgnoreCase(ActualValue.getCardHolderName())){
            return true;
        }else {
            return false;
        }
    }
}
