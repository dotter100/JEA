package Stocks.Models;

public class Stocks {

    private int price;
    private String name;
    private Company company;
    private Valuta valuta;

    public Stocks(int price, String name, Company company) {
        this.price = price;
        this.name = name;
        this.company = company;
        this.valuta = Valuta.EURO;
    }



    public Stocks(Valuta valuta) {
        this.valuta = valuta;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Company getCompany() {
        return company;
    }

    public Valuta getValute() {
        return valuta;
    }
}
