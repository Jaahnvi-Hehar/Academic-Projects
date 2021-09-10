package service.digitalSignature;

import databaseLayer.customer.IInfoSelector;
import service.encrypt.AbstractConvertor;
import service.encrypt.Decryptor;

public class DigitalSignature implements IDigitalSignature{

    private final IInfoSelector infoSelector;
    private final int customerId;

    public DigitalSignature(IInfoSelector infoSelector, String emailAddress)
    {
        this.infoSelector = infoSelector;
        AbstractConvertor abstractConvertor = new Decryptor();
        emailAddress = abstractConvertor.alterText(emailAddress);
        this.customerId = this.infoSelector.getCustomerID(emailAddress);
    }

    private String getName()
    {
        return infoSelector.getName(customerId);
    }

    private String getDOB()
    {
        return infoSelector.getDOB(customerId);
    }

    @Override
    public String originalSignature()
    {
        String delimiter = "-";
        String signature = nameForSignature(getName());
        String[] dateOfBirth = getDOB().split(delimiter);
        for(String date: dateOfBirth)
        {
            signature += date;
        }
        return signature;
    }

    @Override
    public boolean verifySignature(String signatureForVerification) {

        return signatureForVerification.equals(originalSignature());
    }

    public boolean moreThanOneWord(String name)
    {
        char character = ' ';
        name = name.trim();
        for(int i = 0; i < name.length(); i++)
        {
            if(name.charAt(i) == character)
            {
                return true;
            }
        }
        return false;
    }

    public String nameForSignature(String name)
    {
        String delimiter = " ";
        String signature;
        if(name.contains(delimiter))
        {
            String[] subString = name.split(delimiter);
            signature = subString[0];
        }
        else
        {
            signature = name;
        }
        return signature;
    }

}
