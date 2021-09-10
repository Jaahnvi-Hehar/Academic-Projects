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

import java.util.HashMap;

import static org.mockito.Mockito.doReturn;

public class CouponVerificationTest {

    @InjectMocks
    ICouponVerification iCouponVerificationtest;

    @Mock
    ICouponDatabase mockiCouponDatabase;

    @Mock
    ILogisticsDatabaseConnection mockiDatabaseConnection;

    @BeforeEach
    void settingup(){
        mockiCouponDatabase = Mockito.spy(new CouponDatabase(mockiDatabaseConnection));
        iCouponVerificationtest = new CouponVerification(mockiCouponDatabase);
    }

    @Test
    void testTrueVerifyCoupons(){
        doReturn(new HashMap<String,String>()
        {{
            put("CouponCode","FLAT50");
            put("discount","50");
            put("isactive","1");
            put("minorderamt","500");
            put("maxdiscountamt","100");
        }} ).when(mockiCouponDatabase).getCouponDetails("FLAT50");

        Assertions.assertEquals(true,iCouponVerificationtest.verifyCouponCode("FLAT50",1000));
    }

    @Test
    void testFalseVerifyCoupons(){
        doReturn(new HashMap<String,String>()
        {{
            put("CouponCode","FLAT");
            put("discount","50");
            put("isactive","0");
            put("minorderamt","500");
            put("maxdiscountamt","100");
        }} ).when(mockiCouponDatabase).getCouponDetails("FLAT");

        Assertions.assertEquals(false,iCouponVerificationtest.verifyCouponCode("FLAT",1000));
    }

    @Test
    void testFalseMinOrderAmtVerifyCoupons(){
        doReturn(new HashMap<String,String>()
        {{
            put("CouponCode","FLAT");
            put("discount","50");
            put("isactive","0");
            put("minorderamt","500");
            put("maxdiscountamt","100");
        }} ).when(mockiCouponDatabase).getCouponDetails("FLAT");

        Assertions.assertEquals(false,iCouponVerificationtest.verifyCouponCode("FLAT",100));
    }

    @Test
    void testFalseCodeVerifyCoupons(){
        doReturn(null).when(mockiCouponDatabase).getCouponDetails("FLAT");

        Assertions.assertEquals(false,iCouponVerificationtest.verifyCouponCode("FLAT",100));
    }


}
