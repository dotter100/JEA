package Stocks.Models;

import javax.persistence.*;

@Entity
@NamedQuery(name = "stockname", query = "select  u  from Stocks u where u.name = :name")
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private int price;
    @Column(unique = true)
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
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
