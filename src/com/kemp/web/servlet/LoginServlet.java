package com.kemp.web.servlet;

import com.kemp.domain.User;
import com.kemp.service.UserService;
import com.kemp.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(value = "/LoginServlet")
public class LoginServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        response.setContentType("text/html;charset=UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");

        String verifycode = request.getParameter("verifycode");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            request.setAttribute("login_msg", "验证码错误！");
            String msg = (String)request.getAttribute("login_msg");
            System.out.println(new String(msg.getBytes("GBK"),"UTF-8"));
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceImpl();
        System.out.println("loginUser:"+loginUser);
        User user = service.login(loginUser);
        System.out.println("user:"+user);
        if (user != null){
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } else {
            request.setAttribute("login_msg", "用户名或密码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
