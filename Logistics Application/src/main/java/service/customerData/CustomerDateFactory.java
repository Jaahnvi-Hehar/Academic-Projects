package service.customerData;

import service.customerData.info.*;
import service.customerData.password.*;
import view.customer.AbstractCustomerViewFactory;
import view.customer.IInfo;
import view.customer.IInvalidValue;

public class CustomerDateFactory extends AbstractCustomerDataFactory {

    @Override
    public IInfoValidator createInfoValidator() {
        return new InfoValidator();
    }

    @Override
    public IIValidator createName() {
        return new Name();
    }

    @Override
    public IIValidator createDateOfBirth() {
        return new DateOfBirth();
    }

    @Override
    public IIValidator createHouseNo() {
        return new HouseNo();
    }

    @Override
    public IIValidator createBuildingName() {
        return new BuildingName();
    }

    @Override
    public IIValidator createStreetName() {
        return new StreetName();
    }

    @Override
    public IIValidator createArea() {
        return new Area();
    }

    @Override
    public IIValidator createCity() {
        return new City();
    }

    @Override
    public IIValidator createPostalCode() {
        return new PostalCode();
    }

    @Override
    public IIValidator createContactNo() {
        return new ContactNo();
    }

    @Override
    public IPasswordValidator createPasswordValidator() {
        return new PasswordValidator();
    }

    @Override
    public IPValidator createValidateDigit() {
        return new ValidateDigit();
    }

    @Override
    public IPValidator createValidateLength() {
        return new ValidateLength();
    }

    @Override
    public IPValidator createValidateLowerCase() {
        return new ValidateLowerCase();
    }

    @Override
    public IPValidator createValidateSpecialCharacter() {
        return new ValidateSpecialCharacter();
    }

    @Override
    public IPValidator createValidateUpperCase() {
        return new ValidateUpperCase();
    }

    @Override
    public IInfoController createInfoController() {
        IInfo info = AbstractCustomerViewFactory.instance().createInfo();
        IInvalidValue invalidValue = AbstractCustomerViewFactory.instance().createInvalidValue();
        return new InfoController(info, invalidValue);
    }

    @Override
    public ICredentialHelper createCredentialHelper() {
        return new CredentialHelper();
    }

    @Override
    public ICredentialController createCredentialController(ICredentialHelper credentialHelper) {
        return new CredentialController(credentialHelper);
    }
}

