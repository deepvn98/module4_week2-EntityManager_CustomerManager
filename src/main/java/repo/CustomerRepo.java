package repo;

import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public class CustomerRepo implements ICustomerRepo{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        String sql = "select c from Customer c";
        TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
        List<Customer> customerList = query.getResultList();
        return customerList;
    }

    @Override
    public Customer findById(Long id) {
        String sql = "select c from Customer c where  c.id=:id";
        TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
        query.setParameter("id", id);
        try {
            Customer customer = query.getSingleResult();
            return customer;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId()!=null){
            entityManager.merge(customer);
        }else {
            entityManager.persist(customer);
        }

    }

    @Override
    public void remove(Long id) {
        Customer customer = findById(id);
        if (customer!=null){
            entityManager.remove(customer);
        }

    }
}
