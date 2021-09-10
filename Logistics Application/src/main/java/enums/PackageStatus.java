package enums;

public enum PackageStatus
{
    COLLECTED(0),DISPATCHED(1),SHIPPED(2),DELIVERED(3);
    private final int number;

    private PackageStatus(int number) {
        this.number=number;
    }

    public int getNumber(){
        return this.number;
    }
}
