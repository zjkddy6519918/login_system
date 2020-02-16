package com.kemp.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kemp.service.UserService;
import com.kemp.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("application/json;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");

        String r_username = request.getParameter("r_username");
        Map<String,Object> map = new HashMap<>();
        UserService service = new UserServiceImpl();
        if (service.userExist(r_username)){
            map.put("userExist", true);
            map.put("msg", "此用户名已存在");
        } else {
            map.put("userExist", false);
            map.put("msg", "此用户名可用");
        }
        //转换为json格式并传入响应消息
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), map);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
