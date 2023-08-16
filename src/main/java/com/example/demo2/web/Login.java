package com.example.demo2.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");
        String pass = request.getParameter("userpass");

        try (AccountActions accountActions = new AccountActions(name, pass)) {
            if (accountActions.loginIntoAccount()) {
                out.println("WELCOME");
            } else {
                out.println("Username or Password incorrect");
                System.out.println(name);
                System.out.println(pass);
                RequestDispatcher rs = request.getRequestDispatcher("index.html");
                rs.include(request, response);
            }
        }

    }
}
