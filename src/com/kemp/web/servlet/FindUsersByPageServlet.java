package com.kemp.web.servlet;

import com.kemp.domain.PageBean;
import com.kemp.domain.User;
import com.kemp.service.UserService;
import com.kemp.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/FindUsersByPageServlet")
public class FindUsersByPageServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");

        int currentPage = request.getParameter("currentPage")!=null
                ?Integer.parseInt(request.getParameter("currentPage"))
                :1;
        int rowCount = request.getParameter("rowCount")!=null
                ?Integer.parseInt(request.getParameter("rowCount"))
                :5;
        Map<String, String[]> condition = request.getParameterMap();
        UserService service = new UserServiceImpl();
        PageBean<User> usersByPage = service.findUsersByPage(currentPage, rowCount,condition);
        System.out.println("PageBean:"+usersByPage);
        request.setAttribute("usersByPage", usersByPage);
        request.setAttribute("condition", condition);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
