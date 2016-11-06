package app.media.opp.partytonight;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.media.opp.partytonight.domain.Bottle;
import app.media.opp.partytonight.domain.Table;

/**
 * Created by arkadii on 11/6/16.
 */

public class EventEntity {
    @SerializedName("club_name")
    private String clubName;
    @SerializedName("party_name")
    private String partyName;
    private long time;
    private String location;
    @SerializedName("club_capacity")
    private int clubCapacity;

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getClubCapacity() {
        return clubCapacity;
    }

    public void setClubCapacity(int clubCapacity) {
        this.clubCapacity = clubCapacity;
    }
}
