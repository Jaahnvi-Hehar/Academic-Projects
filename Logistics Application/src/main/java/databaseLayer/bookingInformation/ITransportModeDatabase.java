package databaseLayer.bookingInformation;

import java.util.ArrayList;

public interface ITransportModeDatabase {
    public ArrayList<String> getModes();
    public boolean insertMode(String modeName);
}
