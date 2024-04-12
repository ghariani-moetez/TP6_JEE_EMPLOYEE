package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.IDepartementDao;
import dao.DepartementDaoImpl;
import metier.entities.Departement;

@WebServlet(name = "depServ", urlPatterns = { "/departements", "/saisieDepartement", "/saveDepartement",
		"/supprimerDep", "/editerDep", "/updateDep" })
public class DepartementServlet extends HttpServlet {
	IDepartementDao metier;

	@Override
	public void init() throws ServletException {
		metier = new DepartementDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println("PATH " + path);
		if (path.equals("/departements")) {
			DepartementModele model = new DepartementModele();
			List<Departement> deps = metier.getAllDepartements();
			model.setDepartements(deps);
			request.setAttribute("model", model);
			request.getRequestDispatcher("departements.jsp").forward(request, response);
		} else if (path.equals("/saisieDepartement")) {
			request.getRequestDispatcher("saisieDepartement.jsp").forward(request, response);
		}

		else if (path.equals("/saveDepartement") &&

				request.getMethod().equals("POST"))

		{
			Date dateDep = new Date();
			String nom = request.getParameter("nom");
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new

			SimpleDateFormat(pattern);
			try {

				dateDep =

						simpleDateFormat.parse(request.getParameter("dateDep"));

			} catch (ParseException e) {
				e.printStackTrace();
			}
			Departement dep = metier.save(new Departement(nom, dateDep));
			request.setAttribute("departement", dep);
			response.sendRedirect("departements");
		} else if (path.equals("/supprimerDep")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteDepartement(id);
			response.sendRedirect("departements");
		} else if (path.equals("/editerDep")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Departement dep = metier.getDepartement(id);
			request.setAttribute("departement", dep);
			request.getRequestDispatcher("editerDepartement.jsp").forward(request, response);
		} else if (path.equals("/updateDep")) {
			Date dateDep = new Date();
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			Departement dep = new Departement();
			dep.setIdDep(id);
			dep.setNomDep(nom);
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new

			SimpleDateFormat(pattern);
			try {

				dateDep =

						simpleDateFormat.parse(request.getParameter("dateCreation"));

			} catch (ParseException e) {
				e.printStackTrace();
			}
			dep.setDateCreation(dateDep);
			metier.updateDepartement(dep);
			response.sendRedirect("departements");

		} else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,

			HttpServletResponse response) throws

	ServletException, IOException {
		doGet(request, response);
	}
}