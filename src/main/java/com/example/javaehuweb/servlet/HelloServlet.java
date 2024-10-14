package com.example.javaehuweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String numStr = request.getParameter("number");
        int num = Integer.parseInt(numStr);
        num *= 2;
        request.setAttribute("first", num);
        request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
