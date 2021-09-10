package service.customerData.password;

import databaseLayer.admin.IAdminPasswordConfig;

public interface IPValidator {

    boolean isValid(String password, IAdminPasswordConfig admin);

}
