package app.media.opp.partytonight.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arkadii on 10/29/16.
 */
public class User {
    String userName;
    String phoneNumber;
    String billingInfo;
    String emergencyContact;
    String password;
    String email;
    String token;

    public User(String email, String password) {
        this(null, email, null, null, null, password);
    }

    public User(String userName, String email, String phoneNumber, String billingInfo, String emergencyContact, String password) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingInfo = billingInfo;
        this.emergencyContact = emergencyContact;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBillingInfo() {
        return billingInfo;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getPassword() {
        return password;
    }
}
