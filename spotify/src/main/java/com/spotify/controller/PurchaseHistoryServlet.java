package com.spotify.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PurchaseHistoryServlet")
public class PurchaseHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session==null || session.getAttribute("email")==null){

            response.sendRedirect("login.jsp");
            return;

        }

        String email=(String)session.getAttribute("email");

        ArrayList<String[]> history=new ArrayList<String[]>();

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/spotifydb",
                    "root",
                    "root");

            String sql="SELECT product_name,price,purchase_date FROM purchases WHERE email=? ORDER BY purchase_date DESC";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,email);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                String row[]=new String[3];

                row[0]=rs.getString("product_name");
                row[1]=String.valueOf(rs.getDouble("price"));
                row[2]=rs.getString("purchase_date");

                history.add(row);

            }

            rs.close();
            ps.close();
            con.close();

        }

        catch(Exception e){

            e.printStackTrace();

        }

        request.setAttribute("history",history);

        request.getRequestDispatcher("purchaseHistory.jsp")
               .forward(request,response);

    }

}