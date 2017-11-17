package fr.epita.IamViquarWeb.web.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.IamViquarCore.datamodel.Identity;
import fr.epita.IamViquarCore.services.HibernateIdentityDAO;


/**
 * Servlet implementation class IdentityCreation
 */
public class IdentityCreation extends AbstractSpringServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	fr.epita.IamViquarCore.services.HibernateIdentityDAO dao;

	private static final Logger LOGGER = LogManager.getLogger(IdentityCreation.class);  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdentityCreation() {
        super();

    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Creating identity in DB
	 * Parsing format of date
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String displayName = request.getParameter("displayName");
		final String email = request.getParameter("email");
		final String rawDate = request.getParameter("date");
		if (!displayName.isEmpty() && !email.isEmpty() && !rawDate.isEmpty() ) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	try {
		final Date date = sdf.parse(rawDate);
        
		final Identity identity = new Identity(displayName, email, date);

		dao.save(identity);
         LOGGER.info("Identity Saved");
		

		response.sendRedirect("success.jsp");

	} catch (final Exception pe) {
		request.getSession().setAttribute("message",
				"A problem occured with the identity creation, contact the admistrator at ...@admin.com");
		response.sendRedirect("creation.jsp");
	}
	

}else {
	
	response.sendRedirect("identityempty.jsp");
}

}

	}


