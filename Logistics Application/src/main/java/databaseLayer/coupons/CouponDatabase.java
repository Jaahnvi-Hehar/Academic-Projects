package databaseLayer.coupons;


import databaseLayer.connection.ILogisticsDatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CouponDatabase implements ICouponDatabase {

    private final ILogisticsDatabaseConnection iLogisticsDatabaseConnection;

    public CouponDatabase(ILogisticsDatabaseConnection connection)
    {
        this.iLogisticsDatabaseConnection = connection;
    }


    @Override
    public int insertCoupon(Map<String,String> couponDetails) {

        int couponid=-1;
        try {
        Connection connectionDb = iLogisticsDatabaseConnection.getConnection();
        Statement st = connectionDb.createStatement();
        ResultSet rs ;


        String query = "INSERT INTO coupons ( CouponCode, Discount, IsActive, MinOrderAmt, MaxDiscountAmt) Values" +
                "('"+couponDetails.get("couponcode")+"','"+couponDetails.get("discount")+"','"+couponDetails.get("isactive")+"','"+couponDetails.get("minorderamt")+"','"+couponDetails.get("maxdiscountamt")+"')";
        String returnvaluequery = "SELECT CouponId from coupons where CouponCode = '"+couponDetails.get("couponcode")+"'";


            st.executeUpdate(query);
            rs = st.executeQuery(returnvaluequery);
            rs.next();
            couponid =rs.getInt("CouponId");
            iLogisticsDatabaseConnection.closeConnection();
        } catch (Exception e) {
            couponid = -1;
        }
        return couponid;
    }

    @Override
    public ResultSet viewCouponsAdmin() {
        ResultSet rs=null;
        try{
            Connection connectionDb = iLogisticsDatabaseConnection.getConnection();
            Statement st = connectionDb.createStatement();
            String query="SELECT * FROM coupons";
            rs =  st.executeQuery(query);
            iLogisticsDatabaseConnection.closeConnection();
        }catch(Exception e){
            return rs;
        }
        return rs;

    }

    @Override
    public ResultSet viewCouponsCustomers() {

        ResultSet rs=null;
        try{
            Connection connectionDb = iLogisticsDatabaseConnection.getConnection();
            Statement st = connectionDb.createStatement();
            String query="SELECT * FROM coupons WHERE IsActive=1";
            rs =  st.executeQuery(query);
            iLogisticsDatabaseConnection.closeConnection();
        }catch(Exception e){
            return rs;
        }
        return rs;
    }

    @Override
    public Map getCouponDetails(String code) {

        Map<String,String> details = null;
        try{
            Connection connectionDb = iLogisticsDatabaseConnection.getConnection();
        ResultSet rs;
        Statement st = connectionDb.createStatement();
        String query = "SELECT * FROM coupons WHERE CouponCode='"+code+"'";
            rs = st.executeQuery(query);
            if(rs.next()){
                details = new HashMap<>();
                details.put("couponcode", rs.getString("CouponCode"));
                details.put("discount", rs.getString("Discount"));
                details.put("isactive", rs.getString("IsActive"));
                details.put("minorderamt", rs.getString("MinOrderAmt"));
                details.put("maxdiscountamt", rs.getString("MaxDiscountAmt"));

            }else{
                return null;
            }
            iLogisticsDatabaseConnection.closeConnection();
        }catch(Exception e){
            return details;
        }
        return details;
    }

    @Override
    public int getDiscountPercentage(String Code){
        Code = Code.toUpperCase();
        int discountpercentage = 0;
        try {
            Connection connectionDb = iLogisticsDatabaseConnection.getConnection();
            ResultSet rs;
            Statement st = connectionDb.createStatement();
            String query = "SELECT Discount FROM coupons WHERE CouponCode='" + Code + "'";
            rs = st.executeQuery(query);
            rs.next();
            discountpercentage = rs.getInt("Discount");
            iLogisticsDatabaseConnection.closeConnection();
            return discountpercentage;
        }catch (Exception e){
            return discountpercentage;
        }
    }

    @Override
    public int getMaxAmount(String Code){
        Code = Code.toUpperCase();
        int maxAmt = 0;
        try {
            Connection connectionDb = iLogisticsDatabaseConnection.getConnection();
            ResultSet rs;
            Statement st = connectionDb.createStatement();
            String query = "SELECT MaxDiscountAmt FROM coupons WHERE CouponCode='" + Code + "'";
            rs = st.executeQuery(query);
            rs.next();
            maxAmt = rs.getInt("MaxDiscountAmt");
            iLogisticsDatabaseConnection.closeConnection();
            return maxAmt;
        }catch (Exception e){
            return maxAmt;
        }
    }
}
