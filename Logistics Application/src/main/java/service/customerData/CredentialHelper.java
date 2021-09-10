package service.customerData;

import databaseLayer.admin.IAdminPasswordConfig;
import service.encrypt.AbstractConvertor;
import service.mail.IEmailSender;
import view.customer.IInfo;
import view.customer.IInvalidValue;


public class CredentialHelper implements ICredentialHelper {
    private IAdminPasswordConfig passwordConfig;
    private IEmailSender emailSender;
    private AbstractConvertor encryptor;
    private IInfo info;
    private IInvalidValue invalidValue;

    @Override
    public IAdminPasswordConfig getPasswordConfig() {
        return passwordConfig;
    }


    @Override
    public void setPasswordConfig(IAdminPasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }


    @Override
    public IEmailSender getEmailSender() {
        return emailSender;
    }


    @Override
    public void setEmailSender(IEmailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    public AbstractConvertor getEncryptor() {
        return encryptor;
    }


    @Override
    public void setEncryptor(AbstractConvertor encryptor) {
        this.encryptor = encryptor;
    }


    @Override
    public IInfo getInfo() {
        return info;
    }


    @Override
    public void setInfo(IInfo info) {
        this.info = info;
    }


    @Override
    public IInvalidValue getInvalidValue() {
        return invalidValue;
    }


    @Override
    public void setInvalidValue(IInvalidValue invalidValue) {
        this.invalidValue = invalidValue;
    }
}