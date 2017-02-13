package app.media.opp.partytonight.domain;

/**
 * Created by arkadii on 10/29/16.
 */
public class User {
    String userName;
    String phoneNumber;
    Billing billingInfo;
    String emergencyContact;
    String password;
    String email;
    String token;
    String birthday;
    String address;

    public User(String email, String password) {
        this(null, email, null, null, null, password);
    }

    public User(String userName, String email, String password) {
        this(userName, email, null, null, null, password);
    }

    public User(String userName, String email, String password, String birthday, String address) {
        this(userName, email, null, null, null, password, birthday, address);
    }

    public User(String userName, String email, String phoneNumber, Billing billingInfo, String emergencyContact, String password) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingInfo = billingInfo;
        this.emergencyContact = emergencyContact;
        this.password = password;
    }

    public User(String userName, String email, String phoneNumber, Billing billingInfo, String emergencyContact, String password, String birthday) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingInfo = billingInfo;
        this.emergencyContact = emergencyContact;
        this.password = password;
        this.birthday = birthday;
    }

    public User(String userName, String email, String phoneNumber, Billing billingInfo, String emergencyContact, String password,
                String birthday, String address) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingInfo = billingInfo;
        this.emergencyContact = emergencyContact;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
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

    public Billing getBillingInfo() {
        return billingInfo;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }
}
