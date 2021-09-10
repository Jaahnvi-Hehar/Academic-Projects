package service.bookingInformation;

import databaseLayer.bookingInformation.ITransportModeDatabase;
import java.util.ArrayList;

public class TransportModeService implements ITransportModeService{

    private final ITransportModeDatabase transportModeDatabase;

    public TransportModeService (ITransportModeDatabase transportModeDatabase){
        this.transportModeDatabase = transportModeDatabase;
    }

    @Override
    public ArrayList<String> getModes() {
        return this.transportModeDatabase.getModes();
    }

    @Override
    public boolean insertMode(String modeName) {
        return this.transportModeDatabase.insertMode(modeName);
    }
}
