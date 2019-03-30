package Stocks.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String Name;
    @OneToMany
    private List<BuyStock> stocks;

    public Portfolio() {
    }

    public Portfolio(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<BuyStock> getStocks() {
        return stocks;
    }

    public void setStocks(List<BuyStock> stocks) {
        this.stocks = stocks;
    }

    public void ADDStocks(BuyStock stock) {
        this.stocks.add(stock);
    }

}
