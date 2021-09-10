package enums;

public enum PackageType {

    RED_DART(1),BLUE_DART(2),JUMBO_PACK(3);
    private final int number;

    private PackageType(int number) {
        this.number=number;
    }

    public int getNumber(){
        return this.number;
    }

}
