package databaseLayer.admin;

public interface IAdminPasswordConfig {
    int getLengthOfPassword();
    void setLengthOfPassword(int lengthOfPassword);
    int getNoOfUpperCase();
    void setNoOfUpperCase(int noOfUpperCase);
    int getNoOfLowerCase();
    void setNoOfLowerCase(int noOfLowerCase);
    int getNoOfSpecialCharacters();
    void setNoOfSpecialCharacters(int noOfSpecialCharacters);
    int getNoOfDigits();
    void setNoOfDigits(int noOfDigits);

}
