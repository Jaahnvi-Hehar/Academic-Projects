package databaseLayer.cards;

import java.util.Map;

public interface ICardDB {

    public Map<String,String> getCards(long cardnumber);
    public int getCardId(long cardNumber);

}
