package Stocks.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="accounts")
@Cacheable(false)
@NamedQueries({
        @NamedQuery(name = "ID", query = "select  u  from User u where u.id = :ID"),
        @NamedQuery(name = "login", query = "select  u  from User u where u.Name = :name  AND u.Password = :password")
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @NotNull(message = "Name cannot be null")
    @Column(unique = true)
    private String Name;
    @NotEmpty(message = "password cannot be Empty")
    private String Password;




    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USERS_GROUPS",
            joinColumns       = @JoinColumn(name = "ID", nullable=false),
            uniqueConstraints = { @UniqueConstraint(columnNames={"ID","groupname"}) } )
    @Enumerated(EnumType.STRING)
    @Column(name="groupname", length=64, nullable=false)
    private List<Roles> role;

    public List<Roles> getRole() {
        return role;
    }
    public void setRole(List<Roles> role) {
        this.role = role;
    }
    @OneToMany
    private List<Portfolio> portfolios = new ArrayList<>();

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

    public String getPassword() {
        return Password;
    }



    public int getID() {
        return ID;
    }


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

