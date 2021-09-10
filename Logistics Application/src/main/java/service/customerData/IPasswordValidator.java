package service.customerData;

import databaseLayer.admin.IAdminPasswordConfig;

import java.util.List;

public interface IPasswordValidator {
    List<Boolean> isPasswordValid(String password, IAdminPasswordConfig admin);
}
