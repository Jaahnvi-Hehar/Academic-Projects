package service.encrypt;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DecryptorTest {
    @Test
    public void alterText1() {
        String text = "Sg%tn%Tt@si%er1";
        String expected = "TestString@1";
        AbstractConvertor encryptor = new Decryptor();
        String actual = encryptor.alterText(text);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void alterTest2() {
        String text = "Sg4%tn3%Tt@%si2%er1%";
        String expected = "TestString@1234";
        AbstractConvertor encryptor = new Decryptor();
        String actual = encryptor.alterText(text);
        Assertions.assertEquals(expected, actual);
    }
}
