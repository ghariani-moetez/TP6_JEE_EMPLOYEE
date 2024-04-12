package web;


import java.util.ArrayList;
import java.util.List;
import metier.entities.Departement;
public class DepartementModele {
List<Departement> departements = new ArrayList<>();
public List<Departement> getDepartements() {
return departements;
}
public void setDepartements(List<Departement> departements) {
this.departements = departements;
}
}