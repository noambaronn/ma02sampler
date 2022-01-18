package workspace.sampler.Transform;

public class Address {
    private String city;
    private  String street;
    private  String buildingNumber;

    public Address(String city, String street, String buildingNumber) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                '}';
    }
}
