package Controlleres;

import services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreatedUser", urlPatterns = "/CreatedUser")
public class UserCreated extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String Username = req.getParameter("UserName");
            userService.create(Username);
            List<String> Userlist = userService.GetUsernames();
            req.setAttribute("User", Username);
            req.setAttribute("Userlist", Userlist);
            req.getRequestDispatcher("/WEB-INF/CreatedUser.jsp").forward(req, resp);
        }catch (Exception ex){
            resp.getWriter().write("<H1> 404 </H1>");
            resp.getWriter().append("error: " + ex.getMessage());

        }

    }
}
