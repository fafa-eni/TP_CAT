package web;

import java.io.IOException;
import java.util.List;

import dao.IProduitDao;
import dao.ProduitDaoImpl;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.entities.Produit;
import metier.entities.User;


public class ControleurServlet extends HttpServlet {
    private IProduitDao metier;

    @Override
    public void init() throws ServletException {
        metier = new ProduitDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        // Routing logic based on the path
        if (path.equals("/login.do")) {
            // Forward to the login page
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }  else if (path.equals("/register.do")) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested resource is not available.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/login")) {
            // Handle login logic
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.verifyUser(username, password);

            if (user != null) {
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else if (path.equals("/register")) {
            // Handle registration logic
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (username == null || password == null || confirmPassword == null || email == null) {
                request.setAttribute("errorMessage", "All fields are required.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (!password.equals(confirmPassword)) {
                request.setAttribute("errorMessage", "Passwords do not match.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password); // Remember to hash passwords for security
                user.setEmail(email);

                UserDaoImpl userDao = new UserDaoImpl();
                boolean isUserAdded = userDao.addUser(user);

                if (isUserAdded) {
                    request.getRequestDispatcher("registerSuccess.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Username or email already exists or an error occurred.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
        }
    }

    

}
