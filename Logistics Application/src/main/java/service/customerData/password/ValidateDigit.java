package service.customerData.password;

import databaseLayer.admin.IAdminPasswordConfig;

public class ValidateDigit extends CountDigit implements IPValidator {

    @Override
    public boolean isValid(String password, IAdminPasswordConfig admin)
    {
        if(countDigit(password) >= admin.getNoOfDigits())
        {
            return true;
        }
        return false;
    }
}
