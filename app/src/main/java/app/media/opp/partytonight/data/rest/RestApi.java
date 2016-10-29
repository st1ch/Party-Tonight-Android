package app.media.opp.partytonight.data.rest;

import android.content.Context;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public class RestApi {

    private final PartyTonightApi api;
    private Context c;

    public RestApi(Context c, PartyTonightApi api) {
        this.c = c;
        this.api = api;
    }
}
