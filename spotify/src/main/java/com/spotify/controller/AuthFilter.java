package com.spotify.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({
    "/home.jsp",
    "/store.jsp",
    "/purchaseSuccess.jsp",
    "/purchaseHistory.jsp",
    "/PurchaseHistoryServlet",
    "/PurchaseServlet"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {

            boolean manualLogout = false;

            Cookie[] cookies = req.getCookies();

            if (cookies != null) {

                for (Cookie c : cookies) {

                    if ("manualLogout".equals(c.getName())) {

                        manualLogout = true;
                        break;

                    }

                }

            }

            if (manualLogout) {

                res.sendRedirect("login.jsp");

            } else {

                res.sendRedirect("sessionExpired.jsp");

            }

            return;

        }

        chain.doFilter(request, response);

    }

}