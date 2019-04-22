package Stocks.Models;

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

    public void setId(int id) {
        this.id = id;
    }

    public Stocks getStock() {
        return stock;
    }

    public void setStock(Stocks stock) {
        this.stock = stock;
    }
}
