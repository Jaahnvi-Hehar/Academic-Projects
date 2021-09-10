package service.customerData;

import java.util.List;

public interface IInfoValidator {
    List<Boolean> validateCustomerInfo(List<String> values);
}
