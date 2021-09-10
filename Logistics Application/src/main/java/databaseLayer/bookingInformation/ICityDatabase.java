package databaseLayer.bookingInformation;

import java.util.ArrayList;

public interface ICityDatabase {

    public ArrayList<String> getCities();
    public boolean insertCity(String cityId);
    public boolean deleteCity(int cityId);
}
