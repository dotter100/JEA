package Stocks.Services;

import Stocks.Models.Portfolio;
import Stocks.Models.Stocks;

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
}
