package com.liang.servlet;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/testServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");//设置编码格式，以防前端页面出现中文乱码
        PrintWriter printWriter = resp.getWriter();//创建输出流
        printWriter.println("hello<br>");
        printWriter.println("<h1>这就是一个大表头，你们觉得大不大</h1>");
        printWriter.close();
        printWriter.flush();
        ServletContext servletContext = getServletContext();
        DefaultServletHandlerConfigurerTests defaultServletHandlerConfigurer = new DefaultServletHandlerConfigurerTests(servletContext);
        defaultServletHandlerConfigurer.enable(servletContext.getServletContextName());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public ServletContext getServletContext(){
        return super.getServletContext();
    }

    class DefaultServletHandlerConfigurerTests extends DefaultServletHandlerConfigurer {

        /**
         * Create a {@link DefaultServletHandlerConfigurer} instance.
         *
         * @param servletContext the ServletContext to use to configure the underlying DefaultServletHttpRequestHandler.
         */
        public DefaultServletHandlerConfigurerTests(ServletContext servletContext) {
            super(servletContext);
        }

        @Override
        public void enable() {
            super.enable();
        }
    }


}
