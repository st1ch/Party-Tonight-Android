package app.media.opp.partytonight.domain;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/2/17
 */

public class CartItemExtended extends CartItem {
    String partyName;
    Type typeOfItem;
    String title;
    int amount;
    private int fullPrice;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getTypeOfItem() {
        return typeOfItem;
    }

    public void setTypeOfItem(Type typeOfItem) {
        this.typeOfItem = typeOfItem;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(int fullPrice) {
        this.fullPrice = fullPrice;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public enum Type {
        Bottle("Bottle"), Table("Table");

        private String title;

        Type(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
