package com.example.javaehuweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

    @Override
    public void init() {
        log.debug("Servlet {} has started.", this.getServletName());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("GET request received");
        response.setContentType("text/html");
        String numStr = request.getParameter("number");
        int num = Integer.parseInt(numStr);
        num *= 2;
        request.setAttribute("first", num);
        request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
        log.debug("GET request processed");
    }

    public void destroy() {
    }
}
