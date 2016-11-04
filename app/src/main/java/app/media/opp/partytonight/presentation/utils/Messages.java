package app.media.opp.partytonight.presentation.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.media.opp.partytonight.R;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;


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
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case 401:
                    return c.getString(R.string.wrongLoginOrPassword);
                case 403:
                    Response<?> response = httpException.response();
                    try {
                        switch (response.errorBody().string()) {
                            case "401":
                                return c.getString(R.string.suchEmailIsAlreadyUsed);
                            case "402":
                                return c.getString(R.string.thisBillingIsAlreadyUsed);
                            case "403":
                                return c.getString(R.string.suchNameIsAlreadyUsed);
                        }
                    } catch (IOException e1) {
                        Log.e("Messages", String.valueOf(e1));
                    }
            }
        }
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
