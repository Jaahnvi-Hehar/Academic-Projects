package service.customerData.password;

import databaseLayer.admin.IAdminPasswordConfig;

public class ValidateLowerCase extends CountLowerCase implements IPValidator {

    @Override
    public boolean isValid(String password, IAdminPasswordConfig admin)
    {
        if(countLowerCase(password) >= admin.getNoOfLowerCase())
        {
            return true;
        }
        return false;
    }

}

