package databaseLayer.customer;

public interface IInfoSelector {
    String getPassword(String emailAddress);
    int getCustomerID(String emailAddress);
    String getName(int customerId);
    String getDOB(int customerId);
}
