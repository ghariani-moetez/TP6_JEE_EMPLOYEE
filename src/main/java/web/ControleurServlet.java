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
import dao.DepartementDaoImpl;
import dao.EmployeeDaoImpl;
import dao.IDepartementDao;
import metier.entities.Departement;
import metier.entities.Employee;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
IEmployeeDao metier;
IDepartementDao metierDep;
@Override
public void init() throws ServletException {
metier = new EmployeeDaoImpl();
metierDep = new DepartementDaoImpl();
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
	List<Departement> deps = metierDep.getAllDepartements();
	DepartementModele model= new DepartementModele();
	model.setDepartements(deps);
	request.setAttribute("depModel", model);
request.getRequestDispatcher("saisieEmployee.jsp").forward(request,response);
}
else if (path.equals("/save.do") && request.getMethod().equals("POST"))
{
 String nom=request.getParameter("nom");
 String prenom=request.getParameter("prenom");
 Long departementId=Long.parseLong(request.getParameter("departement"));
 Departement dep = metierDep.getDepartement(departementId);
double prix = Double.parseDouble(request.getParameter("salaire"));
 Employee p = metier.save(new Employee(nom,prenom,prix,dep));
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
	
	    Long id = Long.parseLong(request.getParameter("id"));
	    Employee p = metier.getEmployee(id);
	    request.setAttribute("employee", p);
	    
	    List<Departement> deps = metierDep.getAllDepartements();
	    DepartementModele depModel = new DepartementModele();
	    depModel.setDepartements(deps);
	    request.setAttribute("depModel", depModel);
	    
	    request.getRequestDispatcher("editerEmployee.jsp").forward(request, response);
	
}
else if (path.equals("/update.do") )
{
Long id = Long.parseLong(request.getParameter("id"));
String nom=request.getParameter("nom");
String prenom=request.getParameter("prenom");
Long departementId=Long.parseLong(request.getParameter("departement"));
double salaire =
Double.parseDouble(request.getParameter("salaire"));
Employee p = new Employee();
p.setIdEmployee(id);
p.setNomEmployee(nom);
p.setPrenomEmployee(prenom);
p.setSalaire(salaire);
Departement dep = metierDep.getDepartement(departementId);
p.setDepartement(dep);
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
