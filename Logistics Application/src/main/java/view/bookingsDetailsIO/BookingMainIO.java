package view.bookingsDetailsIO;

import databaseLayer.connection.LogisticsDatabaseConnection;
import enums.PackageStatus;
import enums.PackageType;
import service.bookingInformation.AbstractBookingInformationFactory;
import service.bookingInformation.IBookingService;
import service.bookingInformation.ICityService;
import service.bookingInformation.ITransportModeService;
import service.costEstimation.AbstractCostEstimationInformationFactory;
import service.costEstimation.ICostService;
import service.payment.IPaymentService;
import service.payment.PaymentService;
import view.operation.IIO;
import service.distanceCalculator.AbstractDistanceInformationFactory;
import service.distanceCalculator.DistanceInformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BookingMainIO implements IBookingMainIO
{
    private static final String YES_INPUT = "yes";

    private final IIO inputOutput;
    private final IBookingService bookingService;
    private final ICityService cityService;
    private final ITransportModeService transportModeService;
    private final ICostService costService;
    private final IPaymentService iPaymentService;

    private int sourceCityId;
    private int destinationCityId;
    private int userPackageType;
    private int modeID;
    private final HashMap<String, Float> packageDetails;

    public BookingMainIO(IIO inputOutput)
    {
        AbstractBookingInformationFactory abstractBookingInformationFactory = AbstractBookingInformationFactory.instance();
        AbstractCostEstimationInformationFactory abstractCostEstimationInformationFactory = AbstractCostEstimationInformationFactory.instance();
        this.packageDetails = new HashMap<>();
        this.inputOutput = inputOutput;
        this.iPaymentService = new PaymentService(this.inputOutput, new LogisticsDatabaseConnection());
        this.bookingService = abstractBookingInformationFactory.createBooking();
        this.cityService = abstractBookingInformationFactory.createCity();
        this.transportModeService = abstractBookingInformationFactory.createTransport();
        this.costService = abstractCostEstimationInformationFactory.createCostService();
    }

    @Override
    public void execute(int customerId) {
        inputOutput.writeOutput("Do you want to make a booking ? Yes/No");
        String bookingCheck = inputOutput.readInput();
        if (bookingCheck.equalsIgnoreCase(YES_INPUT)) {

            int cityCount = 0;
            ArrayList<String> cityArrayList = getModeAndCityDetails(cityCount, "_____________|Available cities are:|_____________", "CityId -> CityName", cityService.getCities());
            int modeCount = 0;
            ArrayList<String> modeArrayList = getModeAndCityDetails(modeCount, "_____________|Available Modes of Transport are:|_____________", "ModeId -> ModeName", transportModeService.getModes());
            inputOutput.writeOutput("_____________|Make Bookings_____________|");
            try {
                enterSourceDestinationModeDetails();
                if (sourceCityId == destinationCityId) {
                    inputOutput.writeOutput("Please enter different cities!");
                    return;
                }
                if (sourceCityId > cityArrayList.size() || destinationCityId > cityArrayList.size()) {
                    inputOutput.writeOutput("Please enter a valid city ID!");
                    return;
                }
                if (modeID > modeArrayList.size()) {
                    inputOutput.writeOutput("Please enter a valid mode ID!");
                } else {
                    enterPackageDetails();
                    displayTotalCost(customerId);
                }
            } catch (Exception exception) {
                inputOutput.writeOutput(exception.getMessage());
            }
        } else if (bookingService.getBookingsBasedOnCustomerId(customerId).size() > 0) {
            checkPreviousBookingStatus(customerId);
        } else {
            inputOutput.writeOutput("Sorry, create a booking to check the status!");
        }
    }

    private void checkPreviousBookingStatus(int customerId) {
        inputOutput.writeOutput("Do you want to check your previous booking status ? Yes/No");
        String statusCheck = inputOutput.readInput();
        if (statusCheck.equalsIgnoreCase(YES_INPUT)) {
            HashMap<String, Integer> bookings = bookingService.getBookingsBasedOnCustomerId(customerId);
            inputOutput.writeOutput("_____________|Bookings|_____________");
            inputOutput.writeOutput("Booking_Ids____|____Status");
            Map<String, Integer> sortedBookings = new TreeMap<>(bookings);
            for (Map.Entry<String, Integer> booking : sortedBookings.entrySet()) {
                inputOutput.writeOutput(booking.getKey() + "_______" + PackageStatus.values()[booking.getValue()] + "__________");
            }
        } else {
            inputOutput.writeOutput("Thank you, visit again!");
        }
    }

    private void displayTotalCost(int customerId) {
        AbstractDistanceInformationFactory abstractDistanceInformationFactory = new DistanceInformation();
        float distanceValue;
        distanceValue = abstractDistanceInformationFactory.createSummarizedDistance().distance(sourceCityId, destinationCityId);
        String bookingId = bookingService.insertBooking(sourceCityId, destinationCityId, packageDetails, userPackageType, customerId);
        costService.setPackageDetails(packageDetails, modeID, distanceValue);
        inputOutput.writeOutput("Total Cost to be paid:- " + costService.estimatedTotalCost());
        if(bookingId.contains("B")) {
            displayPaymentOption(bookingId);
        }
    }

    private void displayPaymentOption(String bookingId)
    {
        iPaymentService.PaymentOperation(Integer.parseInt(bookingId.split("B")[1]),costService.estimatedTotalCost());
    }

    private void enterPackageDetails() {
        float packageHeight;
        float packageWidth;
        float packageLength;
        float packageWeight;

        inputOutput.writeOutput("Enter the package height!");
        packageHeight = inputOutput.readFloatInput();

        inputOutput.writeOutput("Enter the package width!");
        packageWidth = inputOutput.readFloatInput();

        inputOutput.writeOutput("Enter the package length!");
        packageLength = inputOutput.readFloatInput();

        inputOutput.writeOutput("Enter the package weight!");
        packageWeight = inputOutput.readFloatInput();

        int packageSize = getPackageTypes();
        do {
            inputOutput.writeOutput("Enter the package type!");
            userPackageType = inputOutput.readIntInput();
            if (userPackageType > packageSize) {
                inputOutput.writeOutput(" Please enter valid package type!");
            } else {
                break;
            }
        } while (true);

        packageDetails.put("packageHeight", packageHeight);
        packageDetails.put("packageWidth", packageWidth);
        packageDetails.put("packageLength", packageLength);
        packageDetails.put("packageWeight", packageWeight);
    }

    private void enterSourceDestinationModeDetails() {
        inputOutput.writeOutput("Enter the source city ID!");
        sourceCityId = inputOutput.readIntInput();

        inputOutput.writeOutput("Enter the destination city ID!");
        destinationCityId = inputOutput.readIntInput();

        inputOutput.writeOutput("Enter the mode of transport!");
        modeID = inputOutput.readIntInput();
    }

    private ArrayList<String> getModeAndCityDetails(int count, String outputSentence, String outputHeading, ArrayList<String> cityModeList) {
        inputOutput.writeOutput(outputSentence);
        inputOutput.writeOutput(outputHeading);
        for (String cityMode : cityModeList) {
            count++;
            inputOutput.writeOutput(count + " -> " + cityMode);
        }
        return cityModeList;
    }

    private Integer getPackageTypes() {
        inputOutput.writeOutput("_____________|Available Package Types are:|_____________");
        inputOutput.writeOutput("PackageTypeId -> PackageType");
        for (PackageType packageType : PackageType.values()) {
            inputOutput.writeOutput(packageType.getNumber() + " -> " + packageType);
        }
        return PackageType.values().length;
    }
}
