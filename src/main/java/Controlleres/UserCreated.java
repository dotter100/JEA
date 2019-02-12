package Controlleres;

import services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreatedUser", urlPatterns = "/CreatedUser")
public class UserCreated extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Username = req.getParameter("UserName");
        userService.create(Username);
        req.setAttribute("User", Username);
        req.getRequestDispatcher("/WEB-INF/CreatedUser.jsp").forward(req,resp);

    }
}
