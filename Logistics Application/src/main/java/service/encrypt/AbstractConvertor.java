package service.encrypt;

public abstract class AbstractConvertor {
    protected static final int [] SECRET_KEY = { 5, 4, 1, 3, 2 };
    protected static final char ENCRYPTION_SYMBOL = '%';

    public abstract String alterText(String text);
}
