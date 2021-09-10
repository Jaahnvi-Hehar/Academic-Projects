package service.customerData;

import service.customerData.info.IIValidator;
import service.customerData.password.IPValidator;

public abstract class AbstractCustomerDataFactory {
    private static AbstractCustomerDataFactory uniqueInstance = null;

    public static AbstractCustomerDataFactory instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CustomerDateFactory();
        }
        return uniqueInstance;
    }

    public static void setUniqueInstance(AbstractCustomerDataFactory instance) {
        uniqueInstance = instance;
    }

    public abstract IInfoValidator createInfoValidator();

    public abstract IIValidator createName();

    public abstract IIValidator createDateOfBirth();

    public abstract IIValidator createHouseNo();

    public abstract IIValidator createBuildingName();

    public abstract IIValidator createStreetName();

    public abstract IIValidator createArea();

    public abstract IIValidator createCity();

    public abstract IIValidator createPostalCode();

    public abstract IIValidator createContactNo();

    public abstract IPasswordValidator createPasswordValidator();

    public abstract IPValidator createValidateDigit();

    public abstract IPValidator createValidateLength();

    public abstract IPValidator createValidateLowerCase();

    public abstract IPValidator createValidateSpecialCharacter();

    public abstract IPValidator createValidateUpperCase();

    public abstract IInfoController createInfoController();

    public abstract ICredentialHelper createCredentialHelper();

    public abstract ICredentialController createCredentialController(ICredentialHelper credentialHelper);
}
