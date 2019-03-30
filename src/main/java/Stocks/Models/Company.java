package Stocks.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
    @Id
    private String name;
    public String getName() {
        return name;
    }

    public Company(String name) {
        this.name = name;
    }

    public Company() {
    }
}
