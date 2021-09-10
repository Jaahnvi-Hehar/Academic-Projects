package view.customer;

import view.operation.IIO;

public class CustomerViewFactory extends AbstractCustomerViewFactory {
    private final IIO io;

    public CustomerViewFactory(IIO io) {
        this.io = io;
    }

    @Override
    public IInfo createInfo() {
        return new Info(io);
    }

    @Override
    public IInvalidValue createInvalidValue() {
        return new InvalidValue(io);
    }
}
