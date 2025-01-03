package com.bookMid.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookMid.database.DBConnectionManager;

@WebServlet("/searchList")
public class SearchBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private DBConnectionManager manager;
    private static final String query = "SELECT ID,title,author,price FROM Books";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }

        String title = req.getParameter("searchTerm");

        try (Connection con = manager.openConnection(); PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            pw.println("<table border='1' align='center'>");
            pw.println("<tr>");
            pw.println("<th>Book Id</th>");
            pw.println("<th>Book Discription</th>");
            pw.println("<th>Book author</th>");
            pw.println("<th>Book price</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");
            while (rs.next()) {
                if (rs.getString(2).contains(title)) {
                    pw.println("<tr>");
                    pw.println("<td>" + rs.getInt(1) + "</td>");
                    pw.println("<td>" + rs.getString(2) + "</td>");
                    pw.println("<td>" + rs.getString(3) + "</td>");
                    pw.println("<td>" + rs.getFloat(4) + "</td>");
                    pw.println("<td><a href='deleteurl?id=" + rs.getInt(1) + "'>Delete</a></td>");
                    pw.println("</tr>");
                }
            }
            pw.println("</table>");
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
        pw.println("<a href='index.html'>Home</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}