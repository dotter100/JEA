package Stocks.Services;



import Stocks.Models.User;
import interceptor.UserInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Interceptors(UserInterceptor.class)
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public List<User> GetUsers(){
        TypedQuery<User> query = em.createQuery("select  u  from User u", User.class);

        return query.getResultList();
    }

    public List<String> GetUsernames(){
        TypedQuery<User> query = em.createQuery("select  u  from User u", User.class);
        List<String> strings = new ArrayList();
        for(User u : query.getResultList()){
            strings.add(u.toString());
        }
        return strings;
    }

    public void createUser(User user){
        em.getTransaction().begin();

        em.persist(user);
        em.getTransaction().commit();
        em.flush();
    }

    public User GetUser(String Username, String password){
        return em.createNamedQuery("login", User.class).setParameter("name", Username).setParameter("password", password).getSingleResult();
    }

    public User GetUserID(int id) {
        return em.createNamedQuery("ID", User.class).setParameter("ID", id).getSingleResult();
    }
}