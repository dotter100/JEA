package Stocks.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
public class BuyStock  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int BuyPrice;
    private int Amount;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    private Stocks stock;
    @ManyToOne
    private Portfolio portfolio;
    @JsonIgnore
    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public int getBuyPrice() {
        return BuyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.BuyPrice = buyPrice;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public Stocks getStock() {
        return stock;
    }

    public void setStock(Stocks stock) {
        this.stock = stock;
    }
}
