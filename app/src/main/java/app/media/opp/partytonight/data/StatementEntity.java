package app.media.opp.partytonight.data;

public class StatementEntity {
    private String total;

    private String withdrawn;

    private String ticketsSales;

    private String bottleSales;

    private String tableSales;

    private String refunds;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(String withdrawn) {
        this.withdrawn = withdrawn;
    }

    public String getTicketsSales() {
        return ticketsSales;
    }

    public void setTicketsSales(String ticketsSales) {
        this.ticketsSales = ticketsSales;
    }

    public String getBottleSales() {
        return bottleSales;
    }

    public void setBottleSales(String bottleSales) {
        this.bottleSales = bottleSales;
    }

    public String getTableSales() {
        return tableSales;
    }

    public void setTableSales(String tableSales) {
        this.tableSales = tableSales;
    }

    public String getRefunds() {
        return refunds;
    }

    public void setRefunds(String refunds) {
        this.refunds = refunds;
    }
}
