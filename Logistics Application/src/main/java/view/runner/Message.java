package view.runner;

import view.operation.IIO;

public class Message implements IMessage {
    private final IIO io;

    public Message(IIO io) {
        this.io = io;
    }

    @Override
    public void printCommand() {
        io.writeOutput("\n_____________|AKYLAS LOGISTICS PROVIDERS|_____________");
        io.writeOutput("\n----CUSTOMER OPERATION----");
        io.writeOutput("login");
        io.writeOutput("signup");
        io.writeOutput("updateEmailAddress");
        io.writeOutput("updatePassword");
        io.writeOutput("updateProfile");
        io.writeOutput("makeBooking");
        io.writeOutput("addSignature");
        io.writeOutput("\n---ADMIN OPERATION---");
        io.writeOutput("packageTracking");
        io.writeOutput("addCoupon");
        io.writeOutput("\n---EXIT---");
        io.writeOutput("exit\n");
    }

    @Override
    public void printBadValue() {
        io.writeOutput("\nFAILURE: Bad Value!");
    }


    @Override
    public void printAuthenticationFailure() {
        io.writeOutput("\nFAILURE: Authentication Failure!");
    }


    @Override
    public void printCustomerProfileCreated() {
        io.writeOutput("\nSUCCESS: Customer Profile Created!");
    }

    @Override
    public void printCustomerProfileCreationFailure() {
        io.writeOutput("\nFAILURE: Customer Profile Creation Failure! Please try again!");
    }

    @Override
    public void printUpdateSuccessful() {
        io.writeOutput("\nSUCCESS: Customer profile updated");
    }

    @Override
    public void printUpdateFailure() {
        io.writeOutput("\nFAILURE: Customer profile could not be updated!");
    }

    @Override
    public void printSuccessfulLogin() {
        io.writeOutput("\nSUCCESS: CustomerProfile Login");
    }

    @Override
    public void printLogout() {
        io.writeOutput("\nSUCCESS: CustomerProfile Logout ");
    }

    @Override
    public void printFailureLogout() {
        io.writeOutput("\nFAILURE: No CustomerProfile logged into!");
    }

    @Override
    public void printDigitalSignatureRecorded() {
        io.writeOutput("\nSUCCESS: Digital Signature recorded!");
    }
}
