package app.media.opp.partytonight.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arkadii on 10/29/16.
 */
public class UserEntity {
    @SerializedName("user_name")
    String userName;
    @SerializedName("phone_number")
    String phoneNumber;
    @SerializedName("billing_info")
    String billingInfo;
    @SerializedName("emergency_contact")
    String emergencyContact;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;


    public UserEntity(String userName, String email, String phoneNumber, String billingInfo, String emergencyContact, String password) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingInfo = billingInfo;
        this.emergencyContact = emergencyContact;
        this.password = password;
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
