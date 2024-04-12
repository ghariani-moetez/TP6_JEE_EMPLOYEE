package metier.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Departement implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.IDENTITY)
private Long idDep;
private String nomDep;
@Temporal( TemporalType.DATE )
private Date dateCreation;
@OneToMany (mappedBy="departement")
private List<Employee> employees;
public Departement() {
super();
}
public Departement(String nomDep, Date dateCreation) {
super();
this.nomDep = nomDep;
this.dateCreation = dateCreation;
}
public Long getIdDep() {
return idDep;
}
public void setIdDep(Long idDep) {
this.idDep = idDep;
}
public String getNomDep() {
return nomDep;
}
public void setNomDep(String nomDep) {
this.nomDep = nomDep;
}
public Date getDateCreation() {
return dateCreation;
}
public void setDateCreation(Date dateCreation) {
this.dateCreation = dateCreation;
}
public List<Employee> getEmployees() {
return employees;
}
public void setEmployees(List<Employee> employees) {
this.employees = employees;
}
}