<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@page import="model.Employee" %>
<%
boolean i=false;
if (request.getParameter("name") != null)
{
	 Employee Obj = new Employee();
	 String stsMsg="";
	if (request.getParameter("hidItemIDSave") == "")
	 {
	 stsMsg = Obj.insertEmployee(request.getParameter("name"),
	 request.getParameter("address"),
	 request.getParameter("nic"),
	 request.getParameter("position"),
	 request.getParameter("password"));
	 i=true;
	 } else{
		 stsMsg =Obj.updateEmployee(request.getParameter("hidItemIDSave"),
				 request.getParameter("name"),
				 request.getParameter("address"),
				 request.getParameter("nic"),
				 request.getParameter("position"),
				 request.getParameter("password"));
		 i=true;
		
	 }
	 session.setAttribute("statusMsg", stsMsg);
}
//Delete item----------------------------------
if (request.getParameter("hidItemIDDelete") != null)
{
Employee Obj = new Employee();
String stsMsg = Obj.deleteEmployee(request.getParameter("hidItemIDDelete"));
session.setAttribute("statusMsg", stsMsg);
i=true;
}
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Brand</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
</head>

<body>
    <nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-white portfolio-navbar gradient">
        <div class="container"><a class="navbar-brand logo" href="#">Electro Grid</a>
<button class="navbar-toggler" data-toggle="collapse" data-target="#navbarNav">
<span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>

            <div class="collapse navbar-collapse"
                id="navbarNav">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="NEw.html">Home</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="customer.jsp">Customer Request</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="request.jsp">View Request</a></li>
                    
                </ul>
            </div>
        </div>
    </nav>
    <main class="page lanidng-page">
        <section class="portfolio-block photography">
            <div class="container">
                <div class="row no-gutters">
                    <div class="col-md-6 col-lg-4 item zoom-on-hover"><a href="#"></a></div>
                </div>
            </div>
        </section>
       
    </main>

<h1 class="text-center"> Employee Management</h1>
<form method="post" action="employee.jsp">
<div class="container">
 <div class="row">
 <div class="col">


  Employee Name: <input name="EmpName" type="text"  class="form-control"><br>
 Employee Address: <input name="EmpType" type="text"  class="form-control"><br>
 Employee NIC: <input name="EmpPhone" type="text"  class="form-control"><br>
 Employee Position: <input name="EmpEmail" type="text"  class="form-control"><br>
 Employee Password: <input name="EmpPassword" type="password"  class="form-control"><br>
  <div class="col text-center">
 <input name="btnSubmit" type="submit" value="Save"class="btn btn-primary"><br>
</div>
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
 </div>
 </div>
</div>
</form>



<div class="alert alert-success">
 <% 
 
 
 if( i){
 out.print(session.getAttribute("statusMsg"));
 }
 
 %>
</div>
<br>

    <footer class="page-footer">
        <div class="container">
            <div class="links">
<a href="#">About me</a><a href="#">Contact me</a><a href="#">Privacy</a></div>
            <div class="social-icons"><a href="#">
<i class="icon ion-social-facebook"></i></a>
<a href="#"><i class="icon ion-social-instagram-outline"></i></a>
<a href="#"><i class="icon ion-social-twitter"></i></a></div>
        </div>
    </footer>

    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>