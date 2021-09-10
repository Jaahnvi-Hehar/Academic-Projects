package service.encrypt;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class EncryptorTest {

    @Test
    public void alterText1() {
        String text = "TestString@1";
        String expected = "Sg%tn%Tt@si%er1";
        AbstractConvertor encryptor = new Encryptor();
        String actual = encryptor.alterText(text);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void alterTest2() {
        String text = "TestString@1234";
        String expected = "Sg4%tn3%Tt@%si2%er1%";
        AbstractConvertor encryptor = new Encryptor();
        String actual = encryptor.alterText(text);
        Assertions.assertEquals(expected, actual);
    }
}
