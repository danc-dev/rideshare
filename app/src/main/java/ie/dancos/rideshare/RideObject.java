package ie.dancos.rideshare;

public class RideObject {

    public RideObject() {
    }

    public RideObject(String pickupLocation, String dropoffLocation, String pickupTime, String pickupDate, String cost, String driver) {
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.pickupTime = pickupTime;
        this.pickupDate = pickupDate;
        this.cost = cost;
        this.driver = driver;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getRide() {
        return ride;
    }

    public void setRide(String ride) {
        this.ride = ride;
    }


    private String pickupLocation;
    private String dropoffLocation;
    private String pickupTime;
    private String pickupDate;
    private String cost;
    private String driver;


    private String ride;

}
