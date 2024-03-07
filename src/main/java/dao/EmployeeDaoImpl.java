package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {

    @Override
    public Employee save(Employee employee) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO employee(NOM_EMPLOYEE, PRENOM_EMPLOYEE, SALAIRE) VALUES(?,?,?)");
            ps.setString(1, employee.getNomEmployee());
            ps.setString(2, employee.getPrenomEmployee());
            ps.setDouble(3, employee.getSalaire());
            ps.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_EMPLOYEE) as MAX_ID FROM employee");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                employee.setIdEmployee(rs.getLong("MAX_ID"));
            }
            ps.close();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> employeesParMC(String mc) {
        List<Employee> employees = new ArrayList<>();
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee WHERE NOM_EMPLOYEE LIKE ?");
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setIdEmployee(rs.getLong("ID_EMPLOYEE"));
                employee.setNomEmployee(rs.getString("NOM_EMPLOYEE"));
                employee.setPrenomEmployee(rs.getString("PRENOM_EMPLOYEE"));
                employee.setSalaire(rs.getDouble("SALAIRE"));
                employees.add(employee);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee getEmployee(Long id) {
        Connection conn = SingletonConnection.getConnection();
        Employee employee = new Employee();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee WHERE ID_EMPLOYEE = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setIdEmployee(rs.getLong("ID_EMPLOYEE"));
                employee.setNomEmployee(rs.getString("NOM_EMPLOYEE"));
                employee.setPrenomEmployee(rs.getString("PRENOM_EMPLOYEE"));
                employee.setSalaire(rs.getDouble("SALAIRE"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE employee SET NOM_EMPLOYEE=?, PRENOM_EMPLOYEE=?, SALAIRE=? WHERE ID_EMPLOYEE=?");
            ps.setString(1, employee.getNomEmployee());
            ps.setString(2, employee.getPrenomEmployee());
            ps.setDouble(3, employee.getSalaire());
            ps.setLong(4, employee.getIdEmployee());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE ID_EMPLOYEE = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
