package main;

import databaseLayer.admin.AbstractAdminFactory;
import databaseLayer.admin.IAdminPasswordConfig;
import databaseLayer.connection.LogisticsDatabaseConnection;
import databaseLayer.coupons.CouponDatabase;
import databaseLayer.customer.AbstractCustomerDatabaseFactory;
import databaseLayer.customer.IInfoInsertor;
import databaseLayer.customer.IInfoSelector;
import service.coupons.CouponAdd;
import service.coupons.CouponIOValidation;
import service.coupons.ICouponAdd;
import service.customerAccount.ILoginController;
import service.customerAccount.ISignupController;
import service.customerAccount.IUpdateController;
import service.customerAccount.ISignupHelper;
import service.customerAccount.AbstractCustomerAccountFactory;
import service.customerData.AbstractCustomerDataFactory;
import service.customerData.ICredentialController;
import service.customerData.ICredentialHelper;
import service.customerData.IInfoController;
import service.digitalSignature.AbstractDigitalSignatureFactory;
import service.encrypt.AbstractConvertor;
import service.encrypt.AbstractConvertorFactory;
import service.mail.AbstractMailFactory;
import service.mail.IEmailSender;
import view.bookingsDetailsIO.AbstractDisplayBookingsFactory;
import view.bookingsDetailsIO.DisplayBookingsDetailsFactory;
import view.coupons.CouponIO;
import view.customer.AbstractCustomerViewFactory;
import view.customer.IInfo;
import view.customer.IInvalidValue;
import view.operation.IO;
import view.runner.AbstractRunnerFactory;
import view.runner.IMessage;
import view.runner.IUserInput;

public class LogisticsRunner
{
    private static final String LOGIN = "login";
    private static final String SIGNUP = "signup";
    private static final String UPDATE_EMAIL_ADDRESS = "updateemailaddress";
    private static final String UPDATE_PASSWORD = "updatepassword";
    private static final String UPDATE_PROFILE = "updateprofile";
    private static final String MAKE_BOOKING = "makebooking";
    private static final String DIGITAL_SIGNATURE = "addsignature";
    private static final String LOGOUT = "logout";
    private static final String PACKAGE_TRACKING = "packagetracking";
    private static final String ADD_COUPON = "addcoupon";
    private static final String EXIT = "exit";
    private static final String ADMIN_PASSWORD = "Admin@123";
    private static ILoginController loginController;
    private static ISignupController signupController;
    private static IUpdateController updateController;
    private static IUserInput userInput;
    private static IMessage message;
    private static int customerID;


    public static void initialization() {
        customerID = -1;
        userInput = AbstractRunnerFactory.instance().createUserInput();
        message = AbstractRunnerFactory.instance().createMessage();
        AbstractDisplayBookingsFactory.setDisplayBookingsFactory(new DisplayBookingsDetailsFactory());
    }


    private static void  createInstancesForCustomerProfile() {
        IAdminPasswordConfig passwordConfig = AbstractAdminFactory.instance().createAdminPasswordConfig();
        IInfoSelector selector = AbstractCustomerDatabaseFactory.instance().createInfoSelector();
        IInfoInsertor insertor = AbstractCustomerDatabaseFactory.instance().createInfoInsertor();

        AbstractConvertor encryptor = AbstractConvertorFactory.instance().createEncryptor();
        IEmailSender emailSender = AbstractMailFactory.instance().createEmailSender();
        IInfo info = AbstractCustomerViewFactory.instance().createInfo();
        IInvalidValue invalidValue = AbstractCustomerViewFactory.instance().createInvalidValue();
        IInfoController infoController = AbstractCustomerDataFactory.instance().createInfoController();

        ICredentialHelper credentialHelper = AbstractCustomerDataFactory.instance().createCredentialHelper();
        credentialHelper.setPasswordConfig(passwordConfig);
        credentialHelper.setEmailSender(emailSender);
        credentialHelper.setEncryptor(encryptor);
        credentialHelper.setInfo(info);
        credentialHelper.setInvalidValue(invalidValue);
        ICredentialController credentialController = AbstractCustomerDataFactory.instance().createCredentialController(credentialHelper);

        ISignupHelper signupHelper = AbstractCustomerAccountFactory.instance().createSignupHelper();
        signupHelper.setInsertor(insertor);
        signupHelper.setSelector(selector);
        signupHelper.setCredentialController(credentialController);
        signupHelper.setInfoController(infoController);
        signupHelper.setInvalidValue(invalidValue);

        loginController = AbstractCustomerAccountFactory.instance().createLoginController();
        signupController = AbstractCustomerAccountFactory.instance().createSignupController(signupHelper);
        updateController = AbstractCustomerAccountFactory.instance().createUpdateController(credentialHelper);
    }


    public static void main(String[] args)
    {
        initialization();
        createInstancesForCustomerProfile();
        message.printCommand();
        while (true) {
            String command = userInput.readCommand();
            if (command.equals(EXIT)) {
                break;
            }
            else if (command.equals(LOGIN)) {
                performLogin(loginController);
            }
            else if (command.equals(SIGNUP)) {
                performSignup(signupController);
            }
            else if ((command.equals(UPDATE_EMAIL_ADDRESS)) && (customerID > 0)) {
                checkUpdate(updateController.updateEmailAddress(customerID));
            }
            else if ((command.equals(UPDATE_PASSWORD)) && (customerID > 0)) {
                checkUpdate(updateController.updatePassword(customerID));
            }
            else if ((command.equals(UPDATE_PROFILE)) && (customerID > 0)) {
                checkUpdate(updateController.updateInfo(customerID));
            }
            else if (command.equals(MAKE_BOOKING) && (customerID > 0)) {
                makeBooking();
            }
            else if(command.equals(DIGITAL_SIGNATURE) && (customerID > 0))
            {
                readDigitalSignature();
            }
            else if (command.equals(LOGOUT)) {
                logout();
            }
            else if ((command.equals(PACKAGE_TRACKING)) && callAuthentication()) {
                customerID = -1;
                packageTracking();
            }
            else if ((command.equals(ADD_COUPON)) && callAuthentication()) {
                couponCreation();
            }
            else {
                message.printBadValue();
            }
        }
    }


    private static void performLogin(ILoginController loginController) {
        customerID = loginController.attemptLogin();
        if (customerID == -1) {
            message.printAuthenticationFailure();
        }
        else {
            message.printSuccessfulLogin();
        }
    }


    private static void readDigitalSignature()
    {
        AbstractDigitalSignatureFactory signatureFactory = AbstractDigitalSignatureFactory.instance();
        String emailAddress = userInput.readCustomerEmailAddress();
        signatureFactory.createDigitalSignature(emailAddress);
        message.printDigitalSignatureRecorded();
    }


    private static void performSignup(ISignupController signupController) {
        customerID = signupController.attemptSignupAddCredentials();
        if (customerID == -1) {
            message.printAuthenticationFailure();
        }
        else {
            signupControllerAddInfo(signupController);
        }
    }


    private static void signupControllerAddInfo(ISignupController signupController) {
        boolean isCreated = signupController.attemptSignupAddInfo(customerID);
        if (isCreated)  {
            message.printCustomerProfileCreated();
        }
        else {
            message.printCustomerProfileCreationFailure();
            signupControllerAddInfo(signupController);
        }
    }


    private static void checkUpdate(boolean isUpdated) {
        if (isUpdated) {
            message.printUpdateSuccessful();
        }
        else {
            message.printUpdateFailure();
        }
    }


    public static void logout(){
        if (customerID == -1) {
            message.printFailureLogout();
        }
        else {
            message.printLogout();
        }
    }


    private static void makeBooking() {
        AbstractDisplayBookingsFactory abstractDisplayBookingsFactory = AbstractDisplayBookingsFactory.instance();
        abstractDisplayBookingsFactory.createBookingIO().execute(customerID);
    }


    private static void packageTracking() {
        AbstractDisplayBookingsFactory abstractDisplayBookingsFactory = AbstractDisplayBookingsFactory.instance();
        abstractDisplayBookingsFactory.createPackageTrackIO().execute();
    }


    private static boolean callAuthentication() {
        String password = userInput.readAdminPassword();
        if (ADMIN_PASSWORD.equals(password)) {
            return true;
        }
        else {
            message.printAuthenticationFailure();
            return false;
        }
    }


    public static void couponCreation() {
        ICouponAdd couponAdd1 = new CouponAdd(new CouponDatabase(new LogisticsDatabaseConnection()),new CouponIO(new IO(),new CouponIOValidation(new CouponDatabase(new LogisticsDatabaseConnection()))),new IO());
        couponAdd1.addCoupon();
    }
}
