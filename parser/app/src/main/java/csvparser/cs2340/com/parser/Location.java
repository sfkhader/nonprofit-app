package csvparser.cs2340.com.parser;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Location {
    private String name;
    private String latitude;
    private String longitude;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String website;

    //Default constructor w/ no args is needed for firebase
    Location() {
    }

    Location(String name, String latitude, String longitude,
             String streetAddress, String city, String state, String zip,
             String type, String website) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.website = website;
    }

    //Public getters are needed for firebase to accept our object
    public String getName() {
        return name;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getZip() {
        return zip;
    }
    public String getType() {
        return type;
    }
    public String getWebsite() {
        return website;
    }
}