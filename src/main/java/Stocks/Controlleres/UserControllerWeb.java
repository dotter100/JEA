package Stocks.Controlleres;

import Stocks.Services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Webuser", urlPatterns = "/Webuser")
public class UserControllerWeb  extends HttpServlet {
    @Inject
    private UserService userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<String> Userlist = userService.GetUsernames();
            req.setAttribute("Userlist", Userlist);

            req.getRequestDispatcher("/WEB-INF/UserTest.jsp").forward(req, resp);

    }
}
