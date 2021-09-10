package service.distanceCalculator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SummarizedDistanceTest {

    ISummarizedDistance iSummarizedDistance;

    @Test
    public void distanceTrueTest()
    {
        iSummarizedDistance = new SummarizedDistance();
        Assertions.assertEquals(1, iSummarizedDistance.distance(2, 3));
    }

    @Test
    public void distanceFalseTest()
    {
        iSummarizedDistance = new SummarizedDistance();
        Assertions.assertNotEquals(0, iSummarizedDistance.distance(2, 0));
    }

}
