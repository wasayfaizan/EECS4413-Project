package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.DbConnection;
import dao.*;
import model.*;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");

	    try (PrintWriter out = response.getWriter()) {
	        String email = request.getParameter("login-email");
	        String password = request.getParameter("login-password");

	        UserDao udao = new UserDao(DbConnection.getConnection(this.getServletContext()));
	        User user = udao.userLogin(email, password);

	        if (user != null) {
	            request.getSession().setAttribute("auth", user);

	            // Redirect before getting the response writer or outputting anything
	            if ("admin@login.com".equalsIgnoreCase(email)) {
	                response.sendRedirect("admin.jsp");
	            } else {
	                response.sendRedirect("index.jsp");
	            }
	            return; // Important to return immediately after sendRedirect
	        } else {
	            // Handle invalid login without committing the response
	            request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	            return;
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        // Properly handle exception without writing to out
	        request.setAttribute("errorMessage", "Error while connecting to the database. Please try again later.");
	        request.getRequestDispatcher("login.jsp").forward(request, response);
	    }
	}

}