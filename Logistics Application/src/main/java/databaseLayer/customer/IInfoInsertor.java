package databaseLayer.customer;

import java.util.List;

public interface IInfoInsertor {
    boolean setCredentials(List<String> credentials);
    boolean setCustomerInfo(int customerID, List<String> customerInfo);
}