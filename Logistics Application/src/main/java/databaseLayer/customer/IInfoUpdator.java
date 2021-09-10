package databaseLayer.customer;

import java.util.List;

public interface IInfoUpdator {
    boolean updateEmailAddress(int customerID, String emailAddress);
    boolean updatePassword(int customerID, String password);
    boolean updateCustomerInfo(int customerID, List<String> customerInfo);
}