package com.kemp.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/TestServlet")
public class TestServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String msg = request.getParameter("msg");

        System.out.println(name+","+msg);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
