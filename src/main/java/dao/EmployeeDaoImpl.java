package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Employee;
import util.JPAutil;

public class EmployeeDaoImpl implements IEmployeeDao {

    private EntityManager entityManager = JPAutil.getEntityManager("TP5_JEE_EMPLOYEE");

    @Override
    public Employee save(Employee e) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(e);
        tx.commit();
        return e;
    }

    @Override
    public Employee updateEmployee(Employee e) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        e = entityManager.merge(e);
        tx.commit();
        return e;
    }

    @Override
    public void deleteEmployee(Long id) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Employee e = entityManager.find(Employee.class, id);
        if (e != null) {
            entityManager.remove(e);
        }
        tx.commit();
    }

    @Override
    public Employee getEmployee(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> employeesParMC(String nom) {
        List<Employee> employees = entityManager.createQuery(
                "select e from Employee e where e.nomEmployee like :pnom", Employee.class)
                .setParameter("pnom", "%" + nom + "%")
                .getResultList();
        return employees;
    }
}
