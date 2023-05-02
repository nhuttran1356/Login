package controller;

import service.LoginService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    LoginService loginService = new LoginService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isSuccess = loginService.checkLogin(email, password);
        if(isSuccess){
            session.setAttribute(email, password);
            session.setMaxInactiveInterval(3600);
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/welcome.jsp");
        }else {
            PrintWriter writer = response.getWriter();
            writer.println("Login Fail !");
            writer.close();
        }
    }

}
