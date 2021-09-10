package service.customerData.password;

public abstract class CountLowerCase {
    public int countLowerCase(String password) {
        int lenlower = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                lenlower++;
            }
        }
        return lenlower;
    }
}
