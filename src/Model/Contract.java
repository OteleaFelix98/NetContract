package Model;

public class Contract {

    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String speed;
    private String traffic;
    private String period;

    public Contract() {

    }

    ;

    public Contract(String firstName, String lastName, String address, String speed, String traffic, String period) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.speed = speed;
        this.traffic = traffic;
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean isValidContract() {
        if (!this.getFirstName().equals("") && !this.getLastName().equals("") && !this.getAddress().equals("")
                && !this.getSpeed().equals("Select:") && !this.getTraffic().equals("Select:") && !this.getPeriod().equals("Select:")) {
            return true;
        }
        return false;

    }
}
