package app.media.opp.partytonight.domain;

public class CartItemExtended extends CartItem {

    String partyName;
    Type typeOfItem;
    String title;
    int amount;
    int number;

    private double fullPrice;

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

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
