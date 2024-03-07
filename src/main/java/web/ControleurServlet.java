package web;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IEmployeeDao;
import dao.EmployeeDaoImpl;
import metier.entities.Employee;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
IEmployeeDao metier;
@Override
public void init() throws ServletException {
metier = new EmployeeDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,
 HttpServletResponse response)
 throws ServletException, IOException {
String path=request.getServletPath();
if (path.equals("/index.do"))
{
request.getRequestDispatcher("employees.jsp").forward(request,response);
}
else if (path.equals("/chercher.do"))
{
String motCle=request.getParameter("motCle");
EmployeeModele model= new EmployeeModele();
model.setMotCle(motCle);
List<Employee> employs = metier.employeesParMC(motCle);
model.setEmployees(employs);
request.setAttribute("model", model);
request.getRequestDispatcher("employees.jsp").forward(request,response);
}
else if (path.equals("/saisie.do") )
{
request.getRequestDispatcher("saisieEmployee.jsp").forward(request,response);
}
else if (path.equals("/save.do") && request.getMethod().equals("POST"))
{
 String nom=request.getParameter("nom");
 String prenom=request.getParameter("prenom");
double prix = Double.parseDouble(request.getParameter("salaire"));
 Employee p = metier.save(new Employee(nom,prenom,prix));
request.setAttribute("employee", p);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else if (path.equals("/supprimer.do"))
{
 Long id= Long.parseLong(request.getParameter("id"));
 metier.deleteEmployee(id);
 response.sendRedirect("chercher.do?motCle=");
}
else if (path.equals("/editer.do") )
{
Long id= Long.parseLong(request.getParameter("id"));
 Employee p = metier.getEmployee(id);
 request.setAttribute("employee", p);
request.getRequestDispatcher("editerEmployee.jsp").forward(request,response);
}
else if (path.equals("/update.do") )
{
Long id = Long.parseLong(request.getParameter("id"));
String nom=request.getParameter("nom");
String prenom=request.getParameter("prenom");

double salaire =
Double.parseDouble(request.getParameter("salaire"));
Employee p = new Employee();
p.setIdEmployee(id);
p.setNomEmployee(nom);
p.setPrenomEmployee(prenom);
p.setSalaire(salaire);
metier.updateEmployee(p);
request.setAttribute("employee", p);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}

else
{
response.sendError(Response.SC_NOT_FOUND);
}
}
@Override
protected void doPost(HttpServletRequest request,
 HttpServletResponse response) throws
ServletException, IOException {
doGet(request,response);
}
}
