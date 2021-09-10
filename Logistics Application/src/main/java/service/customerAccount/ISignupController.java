package service.customerAccount;

public interface ISignupController {
    int attemptSignupAddCredentials();
    boolean attemptSignupAddInfo(int customerID);
}