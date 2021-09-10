package service.cardvalidation;

public class VerifyCardNumber implements CardValidator{

    private String inputvalue;

    public VerifyCardNumber(String iv){
        this.inputvalue = iv;
    }

    @Override
    public boolean isVerified(CardDetails ActualValue) {
        if(Long.parseLong(this.inputvalue) == ActualValue.getCardNumber()){
            return true;
        }else {
            return false;
        }
    }
}
