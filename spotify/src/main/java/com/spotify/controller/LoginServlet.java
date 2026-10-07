package com.spotify.controller;
import com.spotify.controller.ActiveUsers;
import java.io.IOException;
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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/spotifydb",
					"root",
					"root"); 

			String sql = "SELECT * FROM users WHERE email=? AND password=?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				HttpSession session = request.getSession();

				session.setAttribute("username", rs.getString("name"));
				session.setAttribute("email", rs.getString("email"));
				session.setMaxInactiveInterval(120);
				ActiveUsers.login();
				response.sendRedirect("home.jsp");

			} else {

				response.getWriter().println("<script>"+ "alert('Invalid Email or Password');"+ "location='login.jsp';"+ "</script>");

			}

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();

			response.getWriter().println("<script>"+ "alert('Database Error');"+ "location='login.jsp';"+ "</script>");

		}
	}
}