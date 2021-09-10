package databaseLayer.bookingInformation;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPackageDatabase {
    public ArrayList<Integer> getPackages();
    public String insertPackage(HashMap<String, Float> packageDetails, int userPackageType);
    public boolean deletePackage(int packageId);
    public HashMap<String,Float> getPackageByPackageID(int packageId);
}
