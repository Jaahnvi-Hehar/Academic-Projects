package service.cardvalidation;

import databaseLayer.cards.ICardDB;

import java.util.Map;

public class CardDetails {
    private Long CardNumber;
    private int CardBalance;
    private int CardCvv;
    private String CardHolderName;
    private String DateofExpiry;
    private int CardType;

    private ICardDB iCardDB;
    private Map<String,String> actualCardDetails;

    public CardDetails(ICardDB icdb){
        iCardDB =icdb;
    }

    public boolean setValues(Long CardNumber){

        if((actualCardDetails = iCardDB.getCards(CardNumber)) != null){
            this.CardNumber = Long.parseLong(actualCardDetails.get("cardnumber"));
            this.CardBalance = Integer.parseInt(actualCardDetails.get("cardbalance"));
            this.CardCvv = Integer.parseInt(actualCardDetails.get("cvv"));
            this.CardHolderName = actualCardDetails.get("holdername");
            this.DateofExpiry = actualCardDetails.get("dateofexpiry");
            this.CardType = Integer.parseInt(actualCardDetails.get("cardtype"));
            return true;
        }else{
            return false;
        }
    }

    public Long getCardNumber() {
        return CardNumber;
    }

    public int getCardCvv() {
        return CardCvv;
    }

    public String getCardHolderName() {
        return CardHolderName;
    }

    public String getDateofExpiry() {
        return DateofExpiry;
    }

    public int getCardType() {
        return CardType;
    }

    public int getCardBalance() {
        return CardBalance;
    }
}
