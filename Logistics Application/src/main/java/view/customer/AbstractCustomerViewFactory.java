package view.customer;

import view.operation.IO;

public abstract class AbstractCustomerViewFactory {
    private static AbstractCustomerViewFactory uniqueInstance = null;

    public static AbstractCustomerViewFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new CustomerViewFactory(new IO());
        }
        return uniqueInstance;
    }

    public static void setUniqueInstance(AbstractCustomerViewFactory instance) {
        uniqueInstance = instance;
    }

    public abstract IInfo createInfo();

    public abstract IInvalidValue createInvalidValue();
}
