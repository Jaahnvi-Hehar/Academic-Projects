package service.customerData;

import databaseLayer.admin.IAdminPasswordConfig;
import service.encrypt.AbstractConvertor;
import service.mail.IEmailSender;
import view.customer.IInfo;
import view.customer.IInvalidValue;

public interface ICredentialHelper {
    IAdminPasswordConfig getPasswordConfig();
    void setPasswordConfig(IAdminPasswordConfig passwordConfig);
    IEmailSender getEmailSender();
    void setEmailSender(IEmailSender emailSender);
    AbstractConvertor getEncryptor();
    void setEncryptor(AbstractConvertor encryptor);
    IInfo getInfo();
    void setInfo(IInfo info);
    IInvalidValue getInvalidValue();
    void setInvalidValue(IInvalidValue invalidValue);
}
