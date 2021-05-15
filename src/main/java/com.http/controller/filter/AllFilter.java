package com.http.controller.filter;

import com.http.config.AppConfig;
import com.http.model.MyConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AllFilter", urlPatterns = {"/*"})
public class AllFilter implements Filter {
    private final MyConnection myConnection = new MyConnection();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        try {
            if (MyConnection.connection == null)
                myConnection.connectDB();
            if (AppConfig.roles.isEmpty())
                AppConfig.setRole();
        } catch (Exception e) {
            e.printStackTrace();
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

}
