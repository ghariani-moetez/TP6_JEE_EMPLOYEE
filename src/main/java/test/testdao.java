package test;
import java.util.List;

import dao.EmployeeDaoImpl;
import metier.entities.Employee;
public class testdao {
public static void main(String[] args) {
EmployeeDaoImpl pdao= new EmployeeDaoImpl();
Employee prod= pdao.save(new Employee("iyed","bechikh",2800));
System.out.println(prod);
List<Employee> employs =pdao.employeesParMC("Bouzid");
for (Employee e : employs)
System.out.println(e);
}
}