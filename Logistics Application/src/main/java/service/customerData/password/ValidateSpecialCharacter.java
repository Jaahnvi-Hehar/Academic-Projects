package service.customerData.password;

import databaseLayer.admin.IAdminPasswordConfig;

public class ValidateSpecialCharacter extends CountSpecialCharacter implements IPValidator {

    @Override
    public boolean isValid(String password, IAdminPasswordConfig admin)
    {
        if(countSpecialCharacter(password) >= admin.getNoOfSpecialCharacters())
        {
            return true;
        }
        return false;
    }
}

