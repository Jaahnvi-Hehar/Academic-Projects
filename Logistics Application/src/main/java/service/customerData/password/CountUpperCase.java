package service.customerData.password;

public abstract class CountUpperCase {
    public int countUpperCase(String password) {
        int lenupper = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                lenupper++;
            }
        }
        return lenupper;
    }
}
