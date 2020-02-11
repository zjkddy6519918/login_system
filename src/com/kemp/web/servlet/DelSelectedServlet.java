package com.kemp.web.servlet;

import com.kemp.service.UserService;
import com.kemp.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(value = "/DelSelectedServlet")
public class DelSelectedServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");

        String[] uids = request.getParameterValues("uid");
        UserService service = new UserServiceImpl();
        service.delSelectedUsers(uids);
        response.sendRedirect(request.getContextPath()+"/FindUsersByPageServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
