package service.encrypt;

public class ConvertorFactory extends AbstractConvertorFactory {

    @Override
    public AbstractConvertor createEncryptor() {
        return new Encryptor();
    }

    @Override
    public AbstractConvertor createDecryptor() {
        return new Decryptor();
    }
}
