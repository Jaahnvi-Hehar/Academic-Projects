package service.distanceCalculator;

public class DistanceInformation extends AbstractDistanceInformationFactory {

    @Override
    public ISummarizedDistance createSummarizedDistance()
    {
        return new SummarizedDistance();
    }

}
