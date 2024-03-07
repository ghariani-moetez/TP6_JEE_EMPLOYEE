 package web;
import java.util.ArrayList;
import java.util.List;
import metier.entities.Employee;
public class EmployeeModele {
private String motCle;
List<Employee> employees = new ArrayList<>();
public String getMotCle() {
return motCle;
}
public void setMotCle(String motCle) {
this.motCle = motCle;
}
public List<Employee> getEmployees() {
return employees;
}
public void setEmployees(List<Employee> employees) {
this.employees = employees;
}
}