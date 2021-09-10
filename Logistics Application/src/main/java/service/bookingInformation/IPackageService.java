package service.bookingInformation;

import java.util.HashMap;

public interface IPackageService {
    public void getPackages();
    public String insertPackage(HashMap<String, Float> packageDetails,int userPackageType);
    public void deletePackage(int packageId);
    public void getPackageByPackageID(int packageId);
}
