package dao;
import java.util.List;

import metier.entities.Employee;
public interface IEmployeeDao {
public Employee save(Employee p);
public List<Employee> employeesParMC(String mc);
public Employee getEmployee(Long id);
public Employee updateEmployee(Employee e);
public void deleteEmployee(Long id);
}
