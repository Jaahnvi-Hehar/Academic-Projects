package service.cardvalidation;

public class VerifyDateOfExpiry implements CardValidator{

    private String inputvalue;

    public VerifyDateOfExpiry(String iv){
        this.inputvalue = iv;
    }

    @Override
    public boolean isVerified(CardDetails ActualValue) {
        if(this.inputvalue.equalsIgnoreCase(ActualValue.getDateofExpiry())){
            return true;
        }else {
            return false;
        }
    }
}
