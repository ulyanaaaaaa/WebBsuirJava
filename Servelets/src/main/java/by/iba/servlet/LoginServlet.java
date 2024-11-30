package by.iba.servlet;

import by.iba.util.HashPassword;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import by.iba.dao.UserDao;



@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        UserDao daoUser = new UserDao();

        if (daoUser.isValidUser(name,  HashPassword.getHash(password))) {

            request.getSession().setAttribute("name", name);
            response.sendRedirect(request.getContextPath() + "/GroupListServlet");
// НЕТ ПАРАМЕТРОВ - всегда использует метод get request.getRequestDispatcher("/GroupServlet")
//.forward(request, response);

        } else {
            request.setAttribute("errorMessage", "Invalid Login and password!!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                    .forward(request, response);
        }

    }

   /* public boolean validateUser(String user, String password) {
        return user.equalsIgnoreCase("admin") && password.equals("admin");
    }*/

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        System.out.println("service");
    }
}

