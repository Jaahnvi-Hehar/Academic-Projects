package service.encrypt;

public abstract class AbstractConvertorFactory {
    private static AbstractConvertorFactory uniqueInstance = null;

    public static AbstractConvertorFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new ConvertorFactory();
        }
        return uniqueInstance;
    }

    public static void setUniqueInstance(AbstractConvertorFactory abstractBookingInformationFactory) {
        uniqueInstance = abstractBookingInformationFactory;
    }

    public abstract AbstractConvertor createEncryptor();

    public abstract AbstractConvertor createDecryptor();
}