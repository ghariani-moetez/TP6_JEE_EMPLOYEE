package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Departement;
import util.JPAutil;

public class DepartementDaoImpl implements IDepartementDao {

    private EntityManager entityManager = JPAutil.getEntityManager("TP5_JEE_EMPLOYEE");

    @Override
    public Departement save(Departement e) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(e);
        tx.commit();
        return e;
    }

    @Override
    public Departement updateDepartement(Departement e) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        e = entityManager.merge(e);
        tx.commit();
        return e;
    }

    @Override
    public void deleteDepartement(Long id) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Departement e = entityManager.find(Departement.class, id);
        if (e != null) {
            entityManager.remove(e);
        }
        tx.commit();
    }

    @Override
    public Departement getDepartement(Long id) {
        return entityManager.find(Departement.class, id);
    }

	@Override
	public List<Departement> getAllDepartements() {
	    List<Departement> deps = entityManager.createQuery("SELECT d FROM Departement d", Departement.class).getResultList();
	    return deps;
	}
    
}
