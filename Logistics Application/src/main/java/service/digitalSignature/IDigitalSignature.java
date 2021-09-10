package service.digitalSignature;

public interface IDigitalSignature {

    boolean verifySignature(String signatureForVerification);
    String originalSignature();
    String nameForSignature(String name);
    boolean moreThanOneWord(String name);
}
