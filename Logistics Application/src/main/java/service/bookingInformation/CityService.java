package service.bookingInformation;

import databaseLayer.bookingInformation.ICityDatabase;
import java.util.ArrayList;

public class CityService implements ICityService {

    private final ICityDatabase cityDatabase;

    public CityService(ICityDatabase cityDatabase) {
        this.cityDatabase = cityDatabase;
    }

    @Override
    public ArrayList<String> getCities() {
        return cityDatabase.getCities();
    }

    @Override
    public void insertCity(String cityName) {
        cityDatabase.insertCity(cityName);
    }

    @Override
    public void deleteCity(int cityId) {
        cityDatabase.deleteCity(cityId);
    }
}
