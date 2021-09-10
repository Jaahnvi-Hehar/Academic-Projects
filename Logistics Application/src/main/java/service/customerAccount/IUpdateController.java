package service.customerAccount;

public interface IUpdateController  {
    boolean updateEmailAddress(int customerID);
    boolean updatePassword(int customerID);
    boolean updateInfo(int customerID);
}