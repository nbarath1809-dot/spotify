package com.spotify.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {

            response.sendRedirect("login.jsp");
            return;

        }

        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");

        String product = request.getParameter("product");
        double price = Double.parseDouble(request.getParameter("price"));

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/spotifydb",
                    "root",
                    "root");

            String sql = "INSERT INTO purchases(username,email,product_name,price) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, product);
            ps.setDouble(4, price);

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            if (result > 0) {

                session.setAttribute("product", product);
                session.setAttribute("price", price);

                response.sendRedirect("purchaseSuccess.jsp");

            } else {

                response.getWriter().println(
                        "<script>"
                        + "alert('Purchase Failed');"
                        + "location='store.jsp';"
                        + "</script>");

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<script>"
                    + "alert('Database Error');"
                    + "location='store.jsp';"
                    + "</script>");

        }

    }

}