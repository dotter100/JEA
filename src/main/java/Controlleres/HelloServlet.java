package Controlleres;

import Models.User;
import services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "hello", urlPatterns = "/")
public class HelloServlet extends HttpServlet {
    @Inject
    private UserService userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello, Bart" + getUsers());
        //req.getRequestDispatcher("/WEB-INF/CreatedUser.jsp").forward(req,resp);


    }


    public String getUsers(){
        //userService.create();

        String t = "";
        for(User u : userService.GetUsers()){
            t = u.getName() + " ";
        }
        return t + "done";
    }
}
