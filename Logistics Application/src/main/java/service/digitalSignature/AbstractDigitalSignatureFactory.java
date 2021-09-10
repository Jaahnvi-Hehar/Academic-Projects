package service.digitalSignature;

public abstract class AbstractDigitalSignatureFactory {

    private static AbstractDigitalSignatureFactory uniqueInstance = null;

    public static AbstractDigitalSignatureFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new DigitalSignatureFactory();
        }
        return uniqueInstance;
    }

    public static void setUniqueInstance(AbstractDigitalSignatureFactory instance) {
        uniqueInstance = instance;
    }

    public abstract IDigitalSignature createDigitalSignature(String email);
}
