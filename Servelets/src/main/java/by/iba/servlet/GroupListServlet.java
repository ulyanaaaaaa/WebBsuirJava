package by.iba.servlet;

import by.iba.dao.PersonDao;
import by.iba.model.ListService;
import by.iba.model.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GroupListServlet", value = "/GroupListServlet")
public class GroupListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDao daoPerson = new PersonDao();

        // Получение параметров из запроса
        String nname = request.getParameter("nname");
        String nphone = request.getParameter("nphone");
        String nemail = request.getParameter("nemail");

        // Проверка на пустые поля
        if (nname == null || nname.isEmpty() || nphone == null || nphone.isEmpty() || nemail == null || nemail.isEmpty()) {
            request.setAttribute("errorMessage", "Заполните все поля");
        } else {
            // Создание нового объекта Person и вставка его в базу данных
            Person newPerson = new Person(nname, nphone, nemail);
            daoPerson.insertPerson(newPerson);
        }

        // Получение списка персон и передача на JSP
        request.setAttribute("group", daoPerson.getPersons());
        request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDao daoPerson = new PersonDao();
        request.setAttribute("group", daoPerson.getPersons());
        request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
    }
}

