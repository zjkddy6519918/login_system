package com.kemp.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.获取资源的请求路径
        HttpServletRequest request = (HttpServletRequest)req;
        String uri = request.getRequestURI();
        if (uri.contains("/login.jsp") || uri.contains("/LoginServlet")
                || uri.contains("/css/")|| uri.contains("/js/")
                || uri.contains("/fonts/")|| uri.contains("/CheckCodeServlet")
        ) {
            chain.doFilter(req, resp);
        } else {
            Object user = request.getSession().getAttribute("user");
            if (user != null){
                chain.doFilter(req, resp);
            } else {
                request.setAttribute("login_msg", "您尚未登录，请先登录");
                request.getRequestDispatcher("/login.jsp").forward(request, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
