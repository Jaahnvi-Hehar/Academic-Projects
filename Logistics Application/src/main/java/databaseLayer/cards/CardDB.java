package databaseLayer.cards;


import databaseLayer.connection.ILogisticsDatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;



public class CardDB implements ICardDB{

    private final ILogisticsDatabaseConnection iLogisticsDatabaseConnection;
    public CardDB(ILogisticsDatabaseConnection connection)
    {
        this.iLogisticsDatabaseConnection = connection;
    }

    @Override
    public Map<String,String> getCards(long cardnumber){

        Map<String,String> cardDetails = null;
        try{
            Connection connectionDb = iLogisticsDatabaseConnection.getConnection();
            ResultSet rs;
            Statement st = connectionDb.createStatement();
            String query = "SELECT * FROM Cards WHERE CardNumber="+cardnumber+"";

            rs = st.executeQuery(query);

            if(rs.next()){

                cardDetails = new HashMap<>();
                cardDetails.put("cardnumber", rs.getString("CardNumber"));
                cardDetails.put("cardbalance", rs.getString("CardBalance"));
                cardDetails.put("cvv", rs.getString("Cvv"));
                cardDetails.put("holdername", rs.getString("HolderName"));
                cardDetails.put("dateofexpiry", rs.getString("DateofExpiry"));
                cardDetails.put("cardtype", rs.getString("CardType"));
            }else{
                return null;
            }
        }catch(Exception e){
            return cardDetails;
        }
        iLogisticsDatabaseConnection.closeConnection();
        return cardDetails;
    }

    @Override
    public int getCardId(long cardNumber) {
        int CardId = -1;
        try{
            Connection connectionDb = iLogisticsDatabaseConnection.getConnection();
            ResultSet rs;
            Statement st = connectionDb.createStatement();
            String query = "SELECT CardId FROM Cards WHERE CardNumber='"+cardNumber+"'";

            rs = st.executeQuery(query);
            if(rs.next()){
                CardId = rs.getInt("CardId");
            }else{
                return CardId;
            }
        }catch(Exception e){
            return CardId;
        }
        iLogisticsDatabaseConnection.closeConnection();
        return CardId;
    }
}
