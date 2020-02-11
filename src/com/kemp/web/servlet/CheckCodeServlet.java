package com.kemp.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(value = "/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 处理响应乱码问题:字节流需getBytes("UTF-8") */
        //response.setContentType("text/html;charset=UTF-8");
        //response.setCharacterEncoding("UTF-8");
        /* 处理post请求乱码问题 */
        request.setCharacterEncoding("UTF-8");
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        int width=80;
        int height = 30;
        //1.创建一个图像，存储图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2.美化图片
        //2.1填充背景色
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,width,height);
        String checkCode = getCheckCode();
        request.getSession().setAttribute("CHECKCODE_SERVER", checkCode);
        //2.2画边框
        g.setColor(Color.YELLOW);
        g.setFont(new Font("黑体",Font.BOLD,24));
        g.drawString(checkCode,15,25);
        Random random = new Random();
        //2.4画干扰线
        for (int i=0; i<10; i++){
            //随机生成坐标点
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.setColor(Color.GREEN);
            g.drawLine(x1,y1,x2,y2);
        }

        //3.将图片输出到页面展示
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    private String getCheckCode(){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int size = str.length();
        //2.3生成随即角标
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=4; i++){
            int index = random.nextInt(size);
            char ch = str.charAt(index);
            sb.append(ch);
        }
        return sb.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
