package service.customerData.password;

public abstract class CountDigit {

    public int countDigit(String password)
    {
        int lendigit = 0;
        for(int i = 0; i < password.length(); i++)
        {
            if(Character.isDigit(password.charAt(i)))
            {
                lendigit++;
            }
        }
        return lendigit;
    }

}
