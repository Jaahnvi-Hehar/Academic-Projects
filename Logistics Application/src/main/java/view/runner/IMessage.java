package view.runner;

public interface IMessage {
    void printCommand();
    void printBadValue();
    void printAuthenticationFailure();
    void printCustomerProfileCreated();
    void printCustomerProfileCreationFailure();
    void printUpdateSuccessful();
    void printUpdateFailure();
    void printSuccessfulLogin();
    void printLogout();
    void printFailureLogout();
    void printDigitalSignatureRecorded();
}
