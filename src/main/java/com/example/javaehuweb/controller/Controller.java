package com.example.javaehuweb.controller;

import com.example.javaehuweb.controller.command.Command;
import com.example.javaehuweb.controller.command.CommandType;
import com.example.javaehuweb.dao.connection.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {
    private static final Logger log = LogManager.getLogger();
    private static final String COMMAND_PARAMETER = "command";

    @Override
    public void init() {
        log.debug("Servlet {} has started.", this.getServletName());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("GET request received");
        processRequest(request, response);
        log.debug("GET request processed");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("POST request received");
        processRequest(request, response);
        log.debug("POST request processed");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String commandStr = request.getParameter(COMMAND_PARAMETER);
        Command command = CommandType.defineCommand(commandStr);
        String page = command.execute(request);

        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    public void destroy() {
        log.debug("Servlet {} is being destroyed.", this.getServletName());
        ConnectionPool.getInstance().closeAllConnections();
        ConnectionPool.getInstance().deregisterDrivers();
        log.debug("Servlet {} has been destroyed.", this.getServletName());
    }
}
