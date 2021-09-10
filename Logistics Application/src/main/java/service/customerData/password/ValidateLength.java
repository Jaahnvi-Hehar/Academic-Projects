package service.customerData.password;

import databaseLayer.admin.IAdminPasswordConfig;

public class ValidateLength implements IPValidator {

    @Override
    public boolean isValid(String password, IAdminPasswordConfig admin)
    {
        if (password.length() >= admin.getLengthOfPassword())
        {
            return true;
        }
        return false;
    }

}


