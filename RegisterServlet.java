package Mypackage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String DB_USER = "root"; // Change if your MySQL user is different
    private static final String DB_PASS = "Learn@123"; // Change to your MySQL password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Get data from form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Basic Validation
        if(name == null || email == null || password == null) {
            response.sendRedirect("register.jsp?error=emptyfields");
            return;
        }

        // 2. Database Logic
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load Driver (Optional for newer JDBC versions, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // SQL Query (Using PreparedStatement for security)
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // In real apps, hash this password!

            // Execute
            int i = pstmt.executeUpdate();
            if (i > 0) {
                // Success - Redirect to success page
                response.sendRedirect("success.jsp");
            } else {
                // Failed
                response.sendRedirect("register.jsp?error=sqlerror");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=exception");
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}