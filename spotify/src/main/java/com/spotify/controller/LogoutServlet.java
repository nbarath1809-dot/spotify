package com.spotify.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {

            ActiveUsers.logout();

            session.invalidate();

        }

        Cookie logoutCookie = new Cookie("manualLogout", "true");
        logoutCookie.setMaxAge(5);
        logoutCookie.setPath(request.getContextPath());

        response.addCookie(logoutCookie);

        response.sendRedirect("login.jsp");

    }
}