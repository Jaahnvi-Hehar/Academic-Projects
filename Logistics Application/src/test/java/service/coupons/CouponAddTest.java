package service.coupons;


import databaseLayer.connection.ILogisticsDatabaseConnection;
import databaseLayer.coupons.CouponDatabase;
import databaseLayer.coupons.ICouponDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import view.coupons.CouponIO;
import view.coupons.ICouponIO;
import view.operation.IIO;
import view.operation.IO;

import java.util.HashMap;

import static org.mockito.Mockito.doReturn;


public class CouponAddTest {

    @InjectMocks
    ICouponAdd iCouponAddTest;

    @Mock
    ICouponDatabase mockiCouponDatabase;

    @Mock
    ICouponIO mockiCouponIO;

    @Mock
    IIO mockio;

    @Mock
    ICouponIOValidation mockiCouponIOValidation ;

    @Mock
    ILogisticsDatabaseConnection mockDatabaseConnection;

    @BeforeEach
    void settingup(){
        mockiCouponDatabase = Mockito.spy(new CouponDatabase(mockDatabaseConnection));
        mockiCouponIO = Mockito.spy(new CouponIO(mockio,mockiCouponIOValidation));
        mockio = new IO();
        iCouponAddTest = new CouponAdd(mockiCouponDatabase,mockiCouponIO,mockio);
    }

    @Test
    void TestTrueAdd(){
        doReturn("FLAT50").when(mockiCouponIO).inputCouponCode();
        doReturn(50).when(mockiCouponIO).inputDiscount();
        doReturn((byte)1).when(mockiCouponIO).inputIsActive();
        doReturn(500).when(mockiCouponIO).inputMinOrderAmount();
        doReturn(200).when(mockiCouponIO).inputMaxDiscountAmount();

        doReturn(1).when(mockiCouponDatabase).insertCoupon(new HashMap<String, String>(){{
            put("couponcode","FLAT50");
            put("discount","50");
            put("isactive","1");
            put("minorderamt","500");
            put("maxdiscountamt","200");
        }});

        Assertions.assertEquals(1,iCouponAddTest.addCoupon());
    }

    @Test
    void TestFalseAdd(){

        doReturn("FLAT50").when(mockiCouponIO).inputCouponCode();
        doReturn(50).when(mockiCouponIO).inputDiscount();
        doReturn((byte)1).when(mockiCouponIO).inputIsActive();
        doReturn(500).when(mockiCouponIO).inputMinOrderAmount();
        doReturn(200).when(mockiCouponIO).inputMaxDiscountAmount();

        doReturn(-1).when(mockiCouponDatabase).insertCoupon(new HashMap<String, String>(){{
            put("couponcode","FLAT50");
            put("discount","50");
            put("isactive","1");
            put("minorderamt","500");
            put("maxdiscountamt","200");
        }});

        Assertions.assertEquals(-1,iCouponAddTest.addCoupon());
    }
}
