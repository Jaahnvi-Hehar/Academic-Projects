package databaseLayer.bookingInformation;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PackageDatabase implements IPackageDatabase
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PackageDatabase.class);

    private final ILogisticsDatabaseConnection databaseConnection;

    public PackageDatabase(ILogisticsDatabaseConnection databaseConnection)
    {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<Integer> getPackages()
    {
        ArrayList<Integer> packageArrayList = new ArrayList<>();
        try {
            String sql = "select * from package";
            ResultSet resultSet = this.databaseConnection.executeQuery(sql);
            while (resultSet.next()) {
                    packageArrayList.add(resultSet.getInt(1));
                }
            resultSet.close();
            this.databaseConnection.closeConnection();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return packageArrayList;
    }

    @Override
    public String insertPackage(HashMap<String, Float> packageDetails, int userPackageType)
    {
        int package_Id;
        String formatPackageId = "";
        try {
            String sql = "insert into package (package_weight,package_width,package_height,package_length,packageType_Id) values(?,?,?,?,?)";
            PreparedStatement statement = this.databaseConnection.createPreparedStatement(sql);
            statement.setFloat(1, packageDetails.get("packageWeight"));
            statement.setFloat(2, packageDetails.get("packageWidth"));
            statement.setFloat(3, packageDetails.get("packageHeight"));
            statement.setFloat(4, packageDetails.get("packageLength"));
            statement.setInt(5, userPackageType);
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                package_Id = resultSet.getInt(1);
                formatPackageId = String.format("%03d", package_Id);
                formatPackageId = "P" + formatPackageId;
            }
            statement.close();
            this.databaseConnection.closeConnection();

        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return formatPackageId;
    }

    @Override
    public boolean deletePackage(int packageId)
    {
        String sql = "delete from booking where package_Id="+packageId;
        int numberOfRecords = this.databaseConnection.executeUpdate(sql);
        if(numberOfRecords > 0) {
            LOGGER.debug("{} records deleted",numberOfRecords);
            return true;
        }
        else {
            LOGGER.error("Deleted operation failed! Please try again");
        }
        this.databaseConnection.closeConnection();
        return false;
    }

    @Override
    public HashMap<String,Float> getPackageByPackageID(int packageId)
    {
        HashMap<String,Float> packageDetails = new HashMap<>();
        try {
            String sql = "select * from booking where package_Id =" + packageId;
            ResultSet resultSet = this.databaseConnection.executeQuery(sql);
            while (resultSet.next()) {
                packageDetails.put("Package ID" , (float) resultSet.getInt(1));
                packageDetails.put("Package Weight" , resultSet.getFloat(2));
                packageDetails.put("Package Width" , resultSet.getFloat(3));
                packageDetails.put("Package Height" , resultSet.getFloat(4));
                packageDetails.put("Package Length" , resultSet.getFloat(5));
                packageDetails.put("Package Type Id" , (float) resultSet.getInt(6));
            }
            resultSet.close();
            this.databaseConnection.closeConnection();

        }  catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return packageDetails;
    }

}


