package service.customerData;

import databaseLayer.admin.IAdminPasswordConfig;
import service.encrypt.AbstractConvertor;
import service.mail.IEmailSender;
import view.customer.IInfo;
import view.customer.IInvalidValue;

import java.util.List;

public class CredentialController implements ICredentialController {

    private static final String VERIFICATION_EMAIL_SUBJECT = "Akylas - Verification Code";
    private static final String VERIFICATION_EMAIL_CONTENT_START = "Hello,<br>For account activation, please enter the following:\n" +
            "<br>Verification Code: <b style=\"color: #fd7e14;\"> ";
    private static final String VERIFICATION_EMAIL_CONTENT_END = " </b>";
    private static final String[] PASSWORD_PARAMETERS = {"Digit", "Length", "LowerCase", "SpecialCharacter", "Uppercase"};
    //Referred source: https://stackoverflow.com/questions/8204680/java-regex-email
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String ALPHANUMERIC_CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
    private static final int LENGTH = 6;
    private final IAdminPasswordConfig passwordConfig;
    private final IEmailSender emailSender;
    private final AbstractConvertor encryptor;
    private final IInfo info;
    private final IInvalidValue invalidValue;


    public CredentialController(ICredentialHelper credentialHelper) {
        this.passwordConfig = credentialHelper.getPasswordConfig();
        this.emailSender = credentialHelper.getEmailSender();
        this.encryptor = credentialHelper.getEncryptor();
        this.info = credentialHelper.getInfo();
        this.invalidValue = credentialHelper.getInvalidValue();
    }


    public boolean validateEmailAddress(String emailAddress) {
        return emailAddress.matches(EMAIL_REGEX);
    }


    public boolean checkVerificationCode(String verificationCode) {
        String confirmVerificationCode = info.getVerificationCode();
        return verificationCode.equals(confirmVerificationCode);
    }


    //Referred source: https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    public String generateCode() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = (int) (ALPHANUMERIC_CODE.length() * Math.random());
            sb.append(ALPHANUMERIC_CODE.charAt(index));
        }
        return sb.toString();
    }


    public boolean verifyEmailAddress(String emailAddress) {
        String verificationCode = generateCode();
        String emailContent = VERIFICATION_EMAIL_CONTENT_START + verificationCode + VERIFICATION_EMAIL_CONTENT_END;
        if (emailSender.sendEmail(emailAddress, VERIFICATION_EMAIL_SUBJECT, emailContent)) {
            return checkVerificationCode(verificationCode);
        }
        return false;
    }


    @Override
    public String readEmailAddress() {
        return info.getEmailAddress();
    }


    @Override
    public String readPassword() {
        return info.getPassword();
    }


    public IPasswordValidator getPasswordValidator() {
        return AbstractCustomerDataFactory.instance().createPasswordValidator();
    }


    public boolean validatePassword(String password) {
        IPasswordValidator passwordValidator = getPasswordValidator();
        List<Boolean> validPassword = passwordValidator.isPasswordValid(password, passwordConfig);
        boolean isPasswordValid = true;
        for (int i = 0; i < validPassword.size(); i++) {
            String constraint = PASSWORD_PARAMETERS[i];
            boolean isValueValid = validPassword.get(i);
            if (isValueValid) {
                continue;
            } else {
                isPasswordValid = false;
                invalidValue.printInvalid(constraint, password);
            }
        }
        return isPasswordValid;
    }


    @Override
    public String getEncryptedEmailAddress() {
        String emailAddress = readEmailAddress();
        if (validateEmailAddress(emailAddress) && verifyEmailAddress(emailAddress)) {
            return encryptor.alterText(emailAddress);
        }
        return null;
    }


    @Override
    public String getEncryptedPassword() {
        String password = readPassword();
        String confirmPassword = info.getConfirmPassword();
        if (password.equals(confirmPassword) && validatePassword(password)) {
            return encryptor.alterText(password);
        }
        return null;
    }

}