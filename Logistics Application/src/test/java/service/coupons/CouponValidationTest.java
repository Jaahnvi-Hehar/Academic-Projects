package service.coupons;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import databaseLayer.coupons.CouponDatabase;
import databaseLayer.coupons.ICouponDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doReturn;

import org.mockito.Mockito;

import java.util.HashMap;


public class CouponValidationTest {

    @InjectMocks
    ICouponIOValidation iCouponIOValidation;

    @Mock
    ICouponDatabase mockICouponDatabase;

    @Mock
    ILogisticsDatabaseConnection iDatabaseConnection;

    @BeforeEach
    void settingup(){
        mockICouponDatabase = Mockito.spy(new CouponDatabase(iDatabaseConnection));
        iCouponIOValidation = new CouponIOValidation(mockICouponDatabase);
    }

    @Test
    void TestTrueValidateCouponCode(){
        doReturn(null).when(mockICouponDatabase).getCouponDetails("FLAT50");
        Assertions.assertTrue(iCouponIOValidation.validateCouponCode("FLAT50"));
    }

    @Test
    void TestFalseValidateCouponCode(){
        String returnkey = "CouponCode";
        String returnKeyValue = "Flat50";
        doReturn(new HashMap<String,String>(){{ put(returnkey,returnKeyValue); }} ).when(mockICouponDatabase).getCouponDetails("FLAT50");
        Assertions.assertFalse(iCouponIOValidation.validateCouponCode("FLAT50"));
        Assertions.assertFalse(iCouponIOValidation.validateCouponCode("FL"));
    }

    @Test
    void TestTrueValidateDiscount(){
        Assertions.assertTrue(iCouponIOValidation.validateDiscount(50));
    }

    @Test
    void TestFalseValidateDiscount(){
        Assertions.assertFalse(iCouponIOValidation.validateDiscount(150));
    }

    @Test
    void TestTrueValidateIsActive(){
        byte num = 0;
        Assertions.assertTrue(iCouponIOValidation.validateIsActive(num));
    }

    @Test
    void TestFalseValidateIsActive(){
        byte num = 100;
        Assertions.assertFalse(iCouponIOValidation.validateIsActive(num));
    }

    @Test
    void TestTrueValidateMinOrderAmt(){
        Assertions.assertTrue(iCouponIOValidation.validateMinOrderAmount(100));
    }

    @Test
    void TestFalseValidateMinOrderAmt(){
        Assertions.assertFalse(iCouponIOValidation.validateMinOrderAmount(-100));
    }

    @Test
    void TestTrueValidateMaxDiscountAmt(){
        Assertions.assertTrue(iCouponIOValidation.validateMaxDiscountAmount(200));
    }

    @Test
    void TestFalseValidateMaxDiscountAmt(){
        Assertions.assertFalse(iCouponIOValidation.validateMaxDiscountAmount(5000));
    }
}
