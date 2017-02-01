package app.media.opp.partytonight.data;

public class Statement extends StatementEntity {
    public Statement() {
    }

    public String total() {
        double total = 0;
        total += Double.parseDouble(getBottleSales());
        total += Double.parseDouble(getRefunds());
        total += Double.parseDouble(getTableSales());
        total += Double.parseDouble(getTicketsSales());

        setTotal(String.valueOf(total));

        return getTotal();
    }
}
