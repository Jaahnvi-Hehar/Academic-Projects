package service.cardvalidation;

public class VerifyCardBalance implements CardValidator{

    String inputvalue;

    public VerifyCardBalance(String iv){
        this.inputvalue = iv;
    }

    @Override
    public boolean isVerified(CardDetails ActualValue) {
        if(Float.parseFloat(this.inputvalue) <= ActualValue.getCardBalance()){
            return true;
        }else {
            return false;
        }
    }
}
