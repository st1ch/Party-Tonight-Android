package app.media.opp.partytonight.domain;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("id_event")
    private int id_event;

    @SerializedName("content")
    private String content;

    @SerializedName("rating")
    private int rating;

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
