package Stocks.Services;

import Stocks.Models.BuyStock;
import Stocks.Models.Portfolio;
import Stocks.Models.Stocks;
import Stocks.Models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PortfolioService {

    @PersistenceContext
    private EntityManager em;

    public List<Portfolio> GetPortfolio(int ID){
        TypedQuery<Portfolio> query = em.createNamedQuery("PortfolioID", Portfolio.class).setParameter("ID",ID);
        return query.getResultList();
    }


    public List<Portfolio> GetPortfolio(String name){
        TypedQuery<Portfolio> query = em.createNamedQuery("PortfolioName", Portfolio.class).setParameter("name",name);
        return query.getResultList();
    }



    public Boolean UpdatePortfolio(Portfolio portfolio){
        em.getTransaction().begin();
        em.merge(portfolio);
        em.getTransaction().commit();
        return true;
    }

    public Boolean AddPortfolio(Portfolio portfolio,int id) {

        em.getTransaction().begin();
        portfolio.setStocks(null);
        User u = em.find(User.class, id);
//        for (BuyStock b : portfolio.getStocks()){
//            b.setPortfolio(portfolio);
//           em.merge(b);
//        }
        portfolio.setUser(u);
        u.AddPortfolios(portfolio);
        //em.merge(portfolio);
        em.persist(u);
        em.getTransaction().commit();
        return true;
    }
}
