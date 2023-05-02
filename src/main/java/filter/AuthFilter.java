package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class AuthFilter implements Filter {

    public static final String SESSION_LOGIN = "loginsession" ;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        var valSession = (String) session.getAttribute(SESSION_LOGIN);
        String uri = request.getRequestURI();


        boolean loginRequest = uri.endsWith("login.jsp") || uri.endsWith("login.do");


        boolean loggedIn = session != null && session.getAttribute("username") != null;
        if (loggedIn || loginRequest) {
            response.sendRedirect(request.getContextPath() + "/welcome");
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
