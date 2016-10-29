package app.media.opp.partytonight.data;

import android.content.Context;

import app.media.opp.partytonight.domain.Account;
import app.media.opp.partytonight.domain.User;

/**
 * Created by arkadii on 10/30/16.
 */
public class PartyTonightAccount implements Account {
    private Context context;

    public PartyTonightAccount(Context context) {
        this.context = context;

    }

    //TODO этот класс основан на SharedPreferences. Как будет сущность юзера, реализуем его

    @Override
    public User user() {
        return null;
    }

    @Override
    public void saveUser(User user) {
        //TODO записать в SharedPreferences
    }

    @Override
    public boolean isAuthorized() {
        //TODO проверить наличие полей в SharedPreferences
        return false;
    }

    @Override
    public void logout() {
        //TODO почистить SharedPreferences
    }
}
