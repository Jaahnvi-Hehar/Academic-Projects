package view.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class IO implements IIO
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IO.class);

    Scanner sc = new Scanner(System.in);
    @Override
    public String readInput(){
        return sc.nextLine();
    }

    @Override
    public Integer readIntInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    @Override
    public Float readFloatInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextFloat();
    }

    @Override
    public void writeOutput(String output){
        LOGGER.info(output);
    }

}
