package com.kemp.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 过滤敏感词汇
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    private List<String> list = new ArrayList<>();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //使用代理对象，增强getParameter方法
        ServletRequest request = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("getParameter")){
                    String value = (String) method.invoke(req, args);
                    System.out.println("args:"+args[0]+",value:"+value);
                    if (value != null){
                        for (String str : list){
                            if (value.contains(str)){
                                value = value.replaceAll(str, "***");

                                return value;
                            }
                        }
                    }
                }
                if (method.getName().equals("getParameterMap")){
                    Map<String, String[]> map = (Map<String, String[]>)method.invoke(req, args);
                    if (map != null){
                        Set<String> keys = map.keySet();
                        for (String key : keys) {
                            String[] values = map.get(key);
                            if (values != null){
                                for (int i = 0; i<values.length; i++) {
                                    if (values[i] != null){
                                        for (String str : list){
                                            if (values[i].contains(str)){
                                                values[i] = values[i].replaceAll(str, "***");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("map:");
                    for (Map.Entry<String, String[]> entry : map.entrySet()) {
                        System.out.println("key:"+entry.getKey());
                        Stream.of(entry.getValue()).forEach(System.out::println);
                    }
                    return map;
                }

                if (method.getName().equals("getParameterValues")){
                    String[] values = (String[])method.invoke(req, args);
                    if (values != null){
                        for (int i=0; i<values.length; i++){
                            if (values[i] != null){
                                for (String str : list){
                                    if (values[i].contains(str)){
                                        values[i] = values[i].replaceAll(str, "***");
                                    }
                                }
                            }
                        }
                    }
                    return values;
                }
                return method.invoke(req, args);
            }
        });

        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(config.getServletContext().getRealPath(
                        "/WEB-INF/classes/sensitive.txt")),"UTF-8"))){
            String line = null;
            while ((line = br.readLine())!= null){
                list.add(line);
            }
            System.out.println("list="+list);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
