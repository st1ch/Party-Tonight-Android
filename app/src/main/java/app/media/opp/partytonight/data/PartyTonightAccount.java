package app.media.opp.partytonight.data;

import android.content.Context;
import android.content.SharedPreferences;

import app.media.opp.partytonight.domain.Account;
import app.media.opp.partytonight.domain.Billing;
import app.media.opp.partytonight.domain.User;

/**
 * Created by arkadii on 10/30/16.
 */
public class PartyTonightAccount implements Account {
    private static final String PREFS_NAME = "account";
    private static final String USERNAME = "username";
    private static final String TOKEN = "token";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String BILLING_INFO = "billing_info";
    private static final String EMERGENCY_CONTACT = "emergency_contact";
    private Context context;
    private User user;

    public PartyTonightAccount(Context context) {
        this.context = context;
    }

    @Override
    public User user() {
        if (user == null) {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String userName = prefs.getString(USERNAME, null);
            String token = prefs.getString(TOKEN, null);
            String email = prefs.getString(EMAIL, null);
            String phone = prefs.getString(PHONE, null);
            String billingInfo = prefs.getString(BILLING_INFO, null);
            String emergencyContact = prefs.getString(EMERGENCY_CONTACT, null);

            user = new User(userName, email, phone, new Billing(billingInfo), emergencyContact, null);
            user.setToken(token);
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        this.user = user;
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
                .putString(USERNAME, user.getUserName())
                .putString(TOKEN, user.getToken())
                .putString(EMAIL, user.getEmail())
                .putString(PHONE, user.getPhoneNumber())
                .putString(BILLING_INFO, user.getBillingInfo() == null ? null : user.getBillingInfo().getCardNumber())
                .putString(EMERGENCY_CONTACT, user.getEmergencyContact())
                .apply();
    }

    @Override
    public boolean isAuthorized() {
        User user = user();
        return user.getToken() != null;
    }

    @Override
    public void logout() {
        user = null;
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
                .clear()
                .apply();
    }
}
