package metier.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable{
@Id
@Column (name="code")
@GeneratedValue (strategy=GenerationType.IDENTITY)
private Long idEmployee;

@Column (name="nom")
private String nomEmployee;

@Column (name="prenom")
private String prenomEmployee;
private double salaire;
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}

public Long getIdEmployee() {
	return idEmployee;
}
public void setIdEmployee(Long idEmployee) {
	this.idEmployee = idEmployee;
}
public String getNomEmployee() {
	return nomEmployee;
}
public void setNomEmployee(String nomEmployee) {
	this.nomEmployee = nomEmployee;
}
public String getPrenomEmployee() {
	return prenomEmployee;
}
public void setPrenomEmployee(String prenomEmployee) {
	this.prenomEmployee = prenomEmployee;
}
public double getSalaire() {
	return salaire;
}
public void setSalaire(double salaire) {
	this.salaire = salaire;
}


private Departement departement;
public Employee(String nomEmployee, String prenomEmployee, double salaire, Departement departement) {
	super();
	this.nomEmployee = nomEmployee;
	this.prenomEmployee = prenomEmployee;
	this.salaire = salaire;
	this.departement = departement;
}
public Departement getDepartement() {
	return departement;
}
public void setDepartement(Departement departement) {
	this.departement = departement;
}




}