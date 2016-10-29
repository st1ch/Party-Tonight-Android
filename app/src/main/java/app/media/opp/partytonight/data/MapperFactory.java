package app.media.opp.partytonight.data;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import app.media.opp.partytonight.domain.User;

/**
 * Created by sebastian on 10.06.16.
 */
public class MapperFactory implements AbstractMapperFactory {
    private Gson gson = new Gson();

    @Override
    public Mapper<UserEntity, User> getUserMapper() {
        return null;
    }

}
