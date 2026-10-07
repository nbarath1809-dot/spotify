package com.spotify.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // Get registration data from AJAX request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String ageString = request.getParameter("age");

        // Response for AJAX
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {

            // Server-side validation
            if (name == null || name.trim().length() < 3) {
                out.print("Name must contain at least 3 characters.");
                return;
            }

            if (email == null || email.trim().isEmpty()) {
                out.print("Email is required.");
                return;
            }

            if (password == null || password.length() < 8) {
                out.print("Password should contain at least 8 characters.");
                return;
            }

            if (confirmPassword == null || !password.equals(confirmPassword)) {
                out.print("Passwords do not match.");
                return;
            }

            int age = Integer.parseInt(ageString);

            if (age < 18) {
                out.print("Age should be 18 or above.");
                return;
            }


            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/spotifydb",
                    "root",
                    "root"
            );


            // Check whether email already exists
            String checkSql = "SELECT * FROM users WHERE email=?";

            PreparedStatement check = con.prepareStatement(checkSql);
            check.setString(1, email);

            ResultSet rs = check.executeQuery();


            if (rs.next()) {

                // Send response to AJAX
                out.print("Email already exists.");

            } else {

                // Insert new user
                String sql =
                        "INSERT INTO users(name,email,password,age) VALUES(?,?,?,?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setInt(4, age);

                int result = ps.executeUpdate();


                if (result > 0) {

                    // Store user information in session
                    HttpSession session = request.getSession();

                    session.setAttribute("name", name);
                    session.setAttribute("email", email);

                    // AJAX receives this message
                    out.print("Registration Successful");

                } else {

                    out.print("Registration Failed");
                }

                ps.close();
            }


            rs.close();
            check.close();
            con.close();

        } catch (NumberFormatException e) {

            out.print("Invalid age.");

        } catch (Exception e) {

            e.printStackTrace();

            out.print("Database Error");
        }

        out.close();
    }
}