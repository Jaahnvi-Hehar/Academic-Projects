package service.bookingInformation;

import databaseLayer.bookingInformation.IPackageDatabase;
import java.util.HashMap;

public class PackageService implements IPackageService {

    private final IPackageDatabase packageDatabase;

    public PackageService(IPackageDatabase packageDatabase){
        this.packageDatabase = packageDatabase;
    }

    @Override
    public void getPackages() {
        this.packageDatabase.getPackages();
    }

    @Override
    public String insertPackage(HashMap<String, Float> packageDetails, int userPackageType)
    {
       return this.packageDatabase.insertPackage(packageDetails,userPackageType);
    }
    @Override
    public void deletePackage(int packageId) {
        this.packageDatabase.deletePackage(packageId);
    }

    @Override
    public void getPackageByPackageID(int packageId) { this.packageDatabase.getPackageByPackageID(packageId); }
}
