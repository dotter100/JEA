package Stocks.Models;

import javax.persistence.Entity;

@Entity
public class BuyStock extends Stocks {
    public BuyStock() {

    }

    public BuyStock(int price, String name, Company company) {
        super(price, name, company);
    }

    public BuyStock(Valuta valuta) {
        super(valuta);
    }
    private int BuyPrice;
    private int Amount;

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
}
