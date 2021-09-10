package service.bookingInformation;

import java.util.ArrayList;

public interface ITransportModeService {
    public ArrayList<String> getModes();
    public boolean insertMode(String modeName);
}
