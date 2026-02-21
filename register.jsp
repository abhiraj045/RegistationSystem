<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register - Smooth App</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            width: 400px;
        }
    </style>
</head>
<body>

    <div class="card p-4">
        <h3 class="text-center mb-4">Create Account</h3>
        
        <!-- Error Message Logic -->
        <% 
            String error = request.getParameter("error");
            if("exception".equals(error)) {
        %>
            <div class="alert alert-danger">Database Connection Failed!</div>
        <% } else if("emptyfields".equals(error)) { %>
            <div class="alert alert-warning">Please fill all fields.</div>
        <% } %>

        <form action="register" method="post">
            <div class="mb-3">
                <label class="form-label">Full Name</label>
                <input type="text" name="name" class="form-control" required placeholder="John Doe">
            </div>
            <div class="mb-3">
                <label class="form-label">Email Address</label>
                <input type="email" name="email" class="form-control" required placeholder="john@example.com">
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" required placeholder="******">
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary btn-lg">Register Now</button>
            </div>
        </form>
        <div class="text-center mt-3">
            <a href="#" class="text-decoration-none">Already have an account? Login</a>
        </div>
    </div>

</body>
</html>