package app.media.opp.partytonight.presentation.utils;

import android.content.Context;

import java.net.SocketTimeoutException;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.media.opp.partytonight.R;


/**
 * Created by sebastian on 21.06.16.
 */
@Singleton
public class Messages {

    private Context c;

    @Inject
    public Messages(Context c) {
        this.c = c;
    }

    public String getError(Throwable e) {
        String message = e.getMessage();
        if (e instanceof SocketTimeoutException) {
            return c.getString(R.string.networkError);
        }
        if (message != null) {
            if (message.startsWith("Unable to resolve host") || message.startsWith("Failed to connect")) {
                return c.getString(R.string.networkError);
            }
        }
        return e.getMessage();
    }


    public String convertError(int messageId) {
        return c.getString(messageId);
    }
}
