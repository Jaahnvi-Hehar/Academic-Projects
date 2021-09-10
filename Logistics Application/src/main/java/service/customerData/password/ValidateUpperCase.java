package service.customerData.password;

import databaseLayer.admin.IAdminPasswordConfig;

public class ValidateUpperCase extends CountUpperCase implements IPValidator {

    @Override
    public boolean isValid(String password, IAdminPasswordConfig admin)
    {
        if( countUpperCase(password) >= admin.getNoOfUpperCase())
        {
            return true;
        }
        return false;
    }

}

