package databaseLayer.payment;


import databaseLayer.connection.ILogisticsDatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class PaymentDB implements IPaymentDB{

    private final ILogisticsDatabaseConnection iLogisticsDatabaseConnection;

    public PaymentDB(ILogisticsDatabaseConnection connection)
    {
        this.iLogisticsDatabaseConnection = connection;
    }

    @Override
    public int createPayment(Map<String,String> paymentdetails) {
        int paymentid=-1;
        try {
            Connection connectionDb = iLogisticsDatabaseConnection.getConnection();
            Statement st = connectionDb.createStatement();
            ResultSet rs ;
            connectionDb.setAutoCommit(false);

            String insertPaymentRecord = "INSERT INTO Payment ( BookingId, Cost, PaymentMode, CardId) Values" +
                    "('"+paymentdetails.get("bookingid")+"','"+paymentdetails.get("amount")+"','"+paymentdetails.get("paymentmode")+"','"+paymentdetails.get("Cardid")+"')";
            String reduceBalance = "UPDATE Cards SET CardBalance = CardBalance - "+paymentdetails.get("amount")+" where CardId = "+paymentdetails.get("Cardid")+"";
            String returnvaluequery = "SELECT PaymentId from Payment where BookingId = '"+paymentdetails.get("bookingid")+"'";
            st.executeUpdate(insertPaymentRecord);
            st.executeUpdate(reduceBalance);
            rs = st.executeQuery(returnvaluequery);
            rs.next();
            paymentid = rs.getInt("PaymentId");
            connectionDb.commit();
            iLogisticsDatabaseConnection.closeConnection();
        } catch (Exception e) {
            paymentid = -1;
        }

        return paymentid;
    }
}
