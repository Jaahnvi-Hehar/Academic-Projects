package service.digitalSignature;

import databaseLayer.connection.LogisticsDatabaseConnection;
import databaseLayer.customer.InfoSelector;

public class DigitalSignatureFactory extends AbstractDigitalSignatureFactory{
    @Override
    public IDigitalSignature createDigitalSignature(String email) {
        return new DigitalSignature(new InfoSelector( new LogisticsDatabaseConnection()), email);
    }
}
