package Stocks.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "PortfolioID", query = "select  u  from Portfolio u where u.ID = :ID"),
        @NamedQuery(name = "PortfolioName", query = "select  u  from Portfolio u where u.Name = :name")
})

public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String Name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "portfolio", orphanRemoval = true)
    private List<BuyStock> stocks = new ArrayList<>();
    @ManyToOne
    private User user;
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
