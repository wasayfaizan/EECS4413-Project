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
import dao.UserDao;
import model.User;

@WebServlet("/user-register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Fetching user details from the request
            String name = request.getParameter("register-name");
            String email = request.getParameter("register-email");
            String password = request.getParameter("register-password");

            // Creating a user object
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);  // Ensure the User class has a setPassword method

            // DAO initialization
            UserDao udao = new UserDao(DbConnection.getConnection(this.getServletContext()));
            // Checking if user already exists
            User tempUser = udao.userLogin(email, password);
            if (tempUser != null) {
                out.println("<h3>User already registered with this email!</h3>");
                request.getRequestDispatcher("register.jsp").include(request, response);
            } else {
                // Registering new user
                boolean result = udao.registerUser(user);
                if (result) {
                    out.println("<h3>User registered successfully</h3>");
                    response.sendRedirect("login.jsp");
                } else {
                    out.println("<h3>Error in registration</h3>");
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database connection error: " + e.getMessage());
        }
    }
}
