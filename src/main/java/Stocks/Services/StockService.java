package Stocks.Services;

import Stocks.Models.Stocks;
import Stocks.Models.User;
import interceptor.UserInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class StockService {

    @PersistenceContext
    private EntityManager em;

    public List<Stocks> GetStocks(){
        TypedQuery<Stocks> query = em.createQuery("select  u  from Stocks u", Stocks.class);
        return query.getResultList();
    }


    public List<Stocks> GetStock(String name){
        TypedQuery<Stocks> query = em.createNamedQuery("stockname", Stocks.class).setParameter("name",name);
        return query.getResultList();
    }

    public void AddStock(Stocks stock){
        em.getTransaction().begin();
        em.persist(stock.getCompany());
        em.persist(stock);
        em.getTransaction().commit();
        em.flush();
    }
}
