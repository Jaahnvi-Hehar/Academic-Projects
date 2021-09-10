package service.customerData;

import databaseLayer.admin.IAdminPasswordConfig;
import service.customerData.password.IPValidator;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator implements IPasswordValidator {

    private final List<IPValidator> validators;

    public PasswordValidator()
    {
        AbstractCustomerDataFactory customerFactory = AbstractCustomerDataFactory.instance();
        IPValidator digit = customerFactory.createValidateDigit();
        IPValidator length = customerFactory.createValidateLength();
        IPValidator lowercase = customerFactory.createValidateLowerCase();
        IPValidator specialCharacter = customerFactory.createValidateSpecialCharacter();
        IPValidator upperCase = customerFactory.createValidateUpperCase();
        validators = new ArrayList<>();
        validators.add(digit);
        validators.add(length);
        validators.add(lowercase);
        validators.add(specialCharacter);
        validators.add(upperCase);
    }

    public List<Boolean> isPasswordValid(String password, IAdminPasswordConfig admin)
    {
        List<Boolean> checker = new ArrayList<>();
        for (IPValidator valid : validators)
        {
            checker.add(valid.isValid(password, admin));
        }
        return checker;
    }

}