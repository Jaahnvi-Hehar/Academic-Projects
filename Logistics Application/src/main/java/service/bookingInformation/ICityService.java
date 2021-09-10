package service.bookingInformation;

import java.util.ArrayList;

public interface ICityService
{
    public ArrayList<String> getCities();
    public void insertCity(String cityName);
    public void deleteCity(int cityId);

}
