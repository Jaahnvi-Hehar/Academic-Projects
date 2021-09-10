package view.customer;

import view.operation.IIO;

public class Info implements IInfo {
    private final IIO io;

    public Info(IIO io) {
        this.io = io;
    }

    @Override
    public String getEmailAddress() {
        io.writeOutput("--- Enter Email Address ---");
        return io.readInput();
    }


    @Override
    public String getPassword() {
        io.writeOutput("--- Enter Password ---");
        return io.readInput();
    }


    @Override
    public String getConfirmPassword() {
        io.writeOutput("--- Confirm Password ---");
        return io.readInput();
    }


    @Override
    public String getVerificationCode() {
        io.writeOutput("NOTE: An email has been sent to your email address with the verification code!");
        io.writeOutput("--- Verification Code ---");
        return io.readInput();
    }


    @Override
    public String getName() {
        io.writeOutput("--- Name ---");
        return io.readInput();
    }


    @Override
    public String getDateOfBirth() {
        io.writeOutput("--- Date of Birth (DD-MM-YYYY) ---");
        return io.readInput();
    }


    @Override
    public String getHouseNo() {
        io.writeOutput("--- House No. ---");
        return io.readInput();
    }


    @Override
    public String getBuildingName() {
        io.writeOutput("--- Building Name ---");
        return io.readInput();
    }


    @Override
    public String getStreetName() {
        io.writeOutput("--- Street Name ---");
        return io.readInput();
    }


    @Override
    public String getArea() {
        io.writeOutput("--- Area ---");
        return io.readInput();
    }


    @Override
    public String getCity() {
        io.writeOutput("--- City ---");
        return io.readInput();
    }


    @Override
    public String getPostalCode() {
        io.writeOutput("--- Postal Code ---");
        return io.readInput();
    }


    @Override
    public String getContactNo() {
        io.writeOutput("--- Contact No. ---");
        return io.readInput();
    }
}
