package service.cardvalidation;

public class VerifyCardType implements CardValidator{

    private String inputvalue;

    public VerifyCardType(String iv){
        this.inputvalue = iv;
    }

    @Override
    public boolean isVerified(CardDetails ActualValue) {
        if(Integer.parseInt(this.inputvalue) == ActualValue.getCardType()){
            return true;
        }else {
            return false;
        }
    }

}
