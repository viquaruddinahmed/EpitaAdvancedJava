package fr.epita.IamViquarWeb.web.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.DataException;

import fr.epita.IamViquarCore.datamodel.Identity;
import fr.epita.IamViquarCore.services.HibernateIdentityDAO;
import fr.epita.IamViquarCore.test.TestHibernateDAO;

/**
 * Servlet implementation class IdentityModification
 */
public class IdentityModification extends AbstractSpringServlet {
	private static final long serialVersionUID = 1L;
	 @Inject
     fr.epita.IamViquarCore.services.HibernateIdentityDAO dao;
     
     private static final Logger LOGGER = LogManager.getLogger(IdentityCreation.class); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdentityModification() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *  getting user selection parameter
	 *  passing to Update form
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("selection");
         LOGGER.info("value of ID"+id); 
					
					
					if ( id != null )
					{
						
						if (request.getParameter("modify") != null) {  
							 
							Identity identity=dao.getById(Long.parseLong(id));
							request.getSession().setAttribute("identity", identity);
						
						
						
						
						response.sendRedirect("modification.jsp");
					
					
					}
					else if (request.getParameter("delete") != null)
					{
						Identity identity=dao.getById(Integer.parseInt(id));
						try {
							dao.delete(identity);
							response.sendRedirect("Delete.jsp");
						} catch (DataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
					
					else
					{
						response.sendRedirect("search.jsp");
					}
				
				
				
				
				
			}

}
