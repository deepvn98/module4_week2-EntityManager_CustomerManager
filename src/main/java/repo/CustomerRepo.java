//package repo;
//
//import model.Customer;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;
//import java.util.List;
//
//
//@Transactional
//public class CustomerRepo implements IGeneralRepo<Customer>{
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Customer> findAll() {
//        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c", Customer.class);
//        return query.getResultList();
//    }
//
//    @Override
//    public Customer findById(Long id) {
//        TypedQuery<Customer> query = em.createQuery("select c from Customer c where  c.id=:id", Customer.class);
//        query.setParameter("id", id);
//        try {
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }
//
//    @Override
//    public void save(Customer customer) {
//
//    }
//
//    @Override
//    public void remove(Long id) {
//
//    }
//}
