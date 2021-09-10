package service.customerData.password;

public abstract class CountSpecialCharacter {
    public int countSpecialCharacter(String password)
    {
        int lenspecial = 0;
        for(int i = 0; i < password.length(); i++)
        {
            if(specialCharacter(password.charAt(i)))
            {
                lenspecial++;
            }
        }
        return lenspecial;
    }

    public boolean specialCharacter(Character ch)
    {
        return (ch >= 33 && ch <= 47) || (ch >= 58 && ch <= 64) ||
                (ch >= 91 && ch <= 96) ||
                (ch >= 123 && ch <= 126);
    }

}
