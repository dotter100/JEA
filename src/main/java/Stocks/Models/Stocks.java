package Stocks.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Stocks {

    private int price;
    @Id
    private String name;
    @ManyToOne
    private Company company;
    private Valuta valuta;

    public Stocks() {
    }

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
