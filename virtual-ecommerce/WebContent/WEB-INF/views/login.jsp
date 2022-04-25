<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="multikart">
    <meta name="keywords" content="multikart">
    <meta name="author" content="multikart">
     <link rel="icon" href="resources/images/favicon/favicon.png" type="image/x-icon">
    <link rel="shortcut icon" href="resources/images/favicon/favicon.png" type="image/x-icon">
    <title>GemboMart | Online Market Place| Akola | Amravati| Yawatmal| Buldhana| Washim </title>
    

    <!--Google font-->
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Yellowtail&display=swap" rel="stylesheet">

    <!-- Icons -->
    <link rel="stylesheet" type="text/css" href="resources/css/vendors/fontawesome.css">

    <!--Slick slider css-->
    <link rel="stylesheet" type="text/css" href="resources/css/vendors/slick.css">
    <link rel="stylesheet" type="text/css" href="resources/css/vendors/slick-theme.css">

    <!-- Animate icon -->
    <link rel="stylesheet" type="text/css" href="resources/css/vendors/animate.css">

    <!-- Themify icon -->
    <link rel="stylesheet" type="text/css" href="resources/css/vendors/themify-icons.css">

    <!-- Bootstrap css -->
    <link rel="stylesheet" type="text/css" href="resources/css/vendors/bootstrap.css">

    <!-- Theme css -->
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">

</head>

<body class="theme-color-11">


    <!-- header start -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- header end -->

     
     
    <!-- breadcrumb start -->
    <div class="breadcrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="page-title">
                        <h2>customer's login</h2>
                    </div>
                </div>
                <div class="col-sm-6">
                    <nav aria-label="breadcrumb" class="theme-breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item active">login</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- breadcrumb End -->


    <!--section start-->
    <section class="login-page section-b-space">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <h3>Login</h3>
                    <div class="theme-card">
                        <form class="theme-form">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="text" class="form-control" id="email" placeholder="Email" required="">
                            </div>
                            <div class="form-group">
                                <label for="review">Password</label>
                                <input type="password" class="form-control" id="review"
                                    placeholder="Enter your password" required="">
                            </div><a href="#" class="btn btn-solid">Login</a>
                        </form>
                    </div>
                </div>
                <div class="col-lg-6 right-login">
                    <h3>New Customer</h3>
                    <div class="theme-card authentication-right">
                        <h6 class="title-font">Create A Account</h6>
                        <p>Sign up for a free account at our store. Registration is quick and easy. It allows you to be
                            able to order from our shop. To start shopping click register.</p><a href="register"
                            class="btn btn-solid">Create an Account</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--Section ends-->


    <!-- footer start -->
   <jsp:include page="footer.jsp"></jsp:include>
    <!-- footer end -->


    <!-- tap to top start -->
    <div class="tap-top">
        <div><i class="fa fa-angle-double-up"></i></div>
    </div>
    <!-- tap to top end -->


    <!-- latest jquery-->
    <script src="resources/js/jquery-3.3.1.min.js"></script>

    <!-- menu js-->
    <script src="resources/js/menu.js"></script>

    <!-- lazyload js-->
    <script src="resources/js/lazysizes.min.js"></script>

    <!-- slick js-->
    <script src="resources/js/slick.js"></script>

    <!-- Bootstrap js-->
    <script src="resources/js/bootstrap.bundle.min.js"></script>

    <!-- Bootstrap Notification js-->
    <script src="resources/js/bootstrap-notify.min.js"></script>

    <!-- Theme js-->
    <script src="resources/js/theme-setting.js"></script>
    <script src="resources/js/script.js"></script>

	<script src="resources/js/set-location.js"></script>

    <script>
        function openSearch() {
            document.getElementById("search-overlay").style.display = "block";
        }

        function closeSearch() {
            document.getElementById("search-overlay").style.display = "none";
        }
    </script>
</body>

</html>