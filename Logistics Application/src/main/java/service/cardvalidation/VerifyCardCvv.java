package service.cardvalidation;

public class VerifyCardCvv implements CardValidator{

    String inputvalue;

    public VerifyCardCvv(String iv){
        this.inputvalue = iv;
    }

    @Override
    public boolean isVerified(CardDetails ActualValue) {
        if(Integer.parseInt(this.inputvalue) == ActualValue.getCardCvv()){
            return true;
        }else {
            return false;
        }
    }

}
