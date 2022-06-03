package com.jialin.testDb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(name = "TestDbServlet", value = "/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = "springstudent";
        String pwd = "springstudent";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";
        //Get Connection from database

        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to DB: " + jdbcUrl);

            Class.forName(driver);
            Connection myCon = DriverManager.getConnection(jdbcUrl,user,pwd);

            out.println("Successful!");
            myCon.close();

        }catch (Exception e ){
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


}
