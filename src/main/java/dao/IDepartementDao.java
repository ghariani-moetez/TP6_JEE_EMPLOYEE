package dao;
import java.util.List;
import metier.entities.Departement;
public interface IDepartementDao {
	public Departement save(Departement dep);
	public Departement getDepartement(Long id);
	public Departement updateDepartement(Departement dep);
	public void deleteDepartement(Long id);
	public List<Departement> getAllDepartements();

}
