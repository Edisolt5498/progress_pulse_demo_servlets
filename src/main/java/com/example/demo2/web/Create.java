package com.example.demo2.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;


public class Create extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("newUserName");
        String pass = request.getParameter("newUserPassword");

        try (AccountActions accountActions = new AccountActions(name, pass)) {
            if (accountActions.createNewAccount()) {
                out.println("New account created");
                RequestDispatcher rs = request.getRequestDispatcher("index.html");
                rs.include(request, response);
            } else {
                out.println("ACCOUNT DID NOt CREATED");
            }
        }
    }
}
