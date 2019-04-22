package Stocks.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="account")
@Cacheable(false)
@NamedQueries({
        @NamedQuery(name = "ID", query = "select  u  from User u where u.id = :ID"),
        @NamedQuery(name = "login", query = "select  u  from User u where u.Name = :name  AND u.Password = :password")
})
@JsonIgnoreProperties(ignoreUnknown = false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @NotNull(message = "Name cannot be null")
    @Column(unique = true)
    private String Name;
    @JsonIgnore
    @NotEmpty(message = "password cannot be Empty")
    private String Password;


    private Roles role;
    public Roles getRole() {
        return role;
    }
    public void setRole(Roles role) {
        this.role = role;
    }
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "user")
    private List<Portfolio> portfolios = new ArrayList<>();
    @JsonIgnore
    private String twofactor;
    @JsonIgnore
    public String getTwofactor() {
        return twofactor;
    }

    public void setTwofactor(String twofactor) {
        this.twofactor = twofactor;
    }

    public User() {

    }
    public User(String name, String password) {
        Name = name;
        Password = password;
    }
    public User(String name, String password, Roles role) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    @JsonIgnore
    public String getPassword() {
        return Password;
    }

    public int getID() {
        return ID;
    }

    @JsonIgnore
    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void AddPortfolios(Portfolio portfolios) {
        this.portfolios.add(portfolios);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}

