<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="multikart">
    <meta name="keywords" content="multikart">
    <meta name="author" content="multikart">
   <link rel="icon" href="../resources/images/favicon/favicon.png" type="image/x-icon">
    <link rel="shortcut icon" href="../resources/images/favicon/favicon.png" type="image/x-icon">
    <title>GemboMart | Online Market Place| Akola | Amravati| Yawatmal| Buldhana| Washim </title>

    <!--Google font-->
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Yellowtail&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">

    <!-- Icons -->
    <link rel="stylesheet" type="text/css" href="../resources/css/vendors/fontawesome.css">

    <!--Slick slider css-->
    <link rel="stylesheet" type="text/css" href="../resources/css/vendors/slick.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/vendors/slick-theme.css">

    <!-- Animate icon -->
    <link rel="stylesheet" type="text/css" href="../resources/css/vendors/animate.css">

    <!-- Themify icon -->
    <link rel="stylesheet" type="text/css" href="../resources/css/vendors/themify-icons.css">

    <!-- Bootstrap css -->
    <link rel="stylesheet" type="text/css" href="../resources/css/vendors/bootstrap.css">

    <!-- Theme css -->
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">

</head>

<body class="theme-color-11">

   <!-- header start -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- header end -->

    <!-- tab section start -->
    <section class="ratio2_3 bg_cls">
        <div class="title1">     
            <h4>${sellerDetails.sellerAddress}</h4>       
            <h2 class="title-inner1">${sellerDetails.shopName}</h2>
        </div>
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="theme-tab">
                        <ul class="tabs tab-title">
                            <li class="current"><a href="tab-4">VEG</a></li>
                            <li class=""><a href="tab-5">NON-VEG</a></li>
                        </ul>
                        <div class="tab-content-cls">
                            <div id="tab-4" class="tab-content active default">
                                <div class="row cycle-box-row">
                                
                                   <c:forEach items="${productList}" var="product">
                                     <c:if test="${product.categoryName == 'veg menu'}">
                                       <div class="col-3">
                                        <div class="cycle-box">
                                            <div class="product-detail">
                                                <a href="/virtual-ecommerce/product/${product.productSellerId}">
                                                    <h4>${product.productName}</h4>
                                                </a>
                                                <div class="rating"><i class="fa fa-star"></i> <i
                                                        class="fa fa-star"></i> <i class="fa fa-star"></i> <i
                                                        class="fa fa-star"></i> <i class="fa fa-star"></i>
                                                </div>
                                               <!--  <ul class="details">
                                                    <li><i class="fa fa-user-o" aria-hidden="true"></i> 10-50 YR</li>
                                                    <li><i class="fa fa-arrows-v" aria-hidden="true"></i> 5 ft – 6.3 ft
                                                    </li>
                                                </ul> -->
                                                <div class="add-wish">
                                                    <a href="javascript:void(0)" title="Add to Wishlist" tabindex="0"><i
                                                            data-feather="heart"></i></a>
                                                </div>
                                            </div>
                                            <div class="img-wrapper">
                                                <a href="/virtual-ecommerce/product/${product.productSellerId}"><img
                                                        src="${imgLocation}/${product.productImage}"
                                                        class="img-fluid blur-up lazyload bg-img" alt=""></a>
                                                <div class="quick-view-part">
                                                    <a href="#" data-bs-toggle="modal" data-bs-target="#quick-view"
                                                        title="Quick View" tabindex="0"><i
                                                            data-feather="search"></i></a>
                                                </div>
                                            </div>
                                            <div class="bottom-detail">
                                                <div>
                                                    <!-- <ul class="color-variant">
                                                        <li class="bg-light0"></li>
                                                        <li class="bg-light1"></li>
                                                        <li class="bg-light2"></li>
                                                    </ul> -->
                                                    <h4><i class="fa fa-rupee"></i> ${product.productPrice}</h4>
                                                </div>
                                            </div>
                                            <ul class="cart-detail">
                                                <li>
                                                    <button data-bs-toggle="modal" data-bs-target="#addtocart"
                                                        title="Add to cart" tabindex="0"><i
                                                            data-feather="shopping-cart"></i>add to cart</button>
                                                </li>
                                                <li>
                                                    <div class="qty-box">
					                                    <div class="input-group"><span class="input-group-prepend"><button type="button"
					                                                class="btn quantity-left-minus" data-type="minus" data-field=""><i
					                                                    class="ti-angle-left"></i></button> </span>
					                                        <input type="text" name="quantity" class="form-control input-number" value="1">
					                                        <span class="input-group-prepend"><button type="button"
					                                                class="btn quantity-right-plus" data-type="plus" data-field=""><i
					                                                    class="ti-angle-right"></i></button></span>
					                                    </div>
					                                </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    </c:if>
                                    </c:forEach>
                                   </div>
                            </div>
                            <div id="tab-5" class="tab-content">
                                <div class="row cycle-box-row">
                              		 <c:forEach items="${productList}" var="product">
                                     <c:if test="${product.categoryName == 'non-veg menu'}">
                                       <div class="col-3">
                                        <div class="cycle-box">
                                            <div class="product-detail">
                                                <a href="/virtual-ecommerce/product/${product.productSellerId}">
                                                    <h4>${product.productName}</h4>
                                                </a>
                                                        <div class="rating"><i class="fa fa-star"></i> <i
                                                        class="fa fa-star"></i> <i class="fa fa-star"></i> <i
                                                        class="fa fa-star"></i> <i class="fa fa-star"></i>
                                                </div>
                                                <!-- <ul class="details">
                                                    <li><i class="fa fa-user-o" aria-hidden="true"></i> 10-50 YR</li>
                                                    <li><i class="fa fa-arrows-v" aria-hidden="true"></i> 5 ft – 6.3 ft
                                                    </li>
                                                </ul> -->
                                                <div class="add-wish">
                                                    <a href="javascript:void(0)" title="Add to Wishlist" tabindex="0"><i
                                                            data-feather="heart"></i></a>
                                                </div>
                                            </div>
                                            <div class="img-wrapper">
                                                <a href="/virtual-ecommerce/product/${product.productSellerId}"><img
                                                        src="${imgLocation}/${product.productImage}"
                                                        class="img-fluid blur-up lazyload bg-img" alt=""></a>
                                                <div class="quick-view-part">
                                                    <a href="#" data-bs-toggle="modal" data-bs-target="#quick-view"
                                                        title="Quick View" tabindex="0"><i
                                                            data-feather="search"></i></a>
                                                </div>
                                            </div>
                                            <div class="bottom-detail">
                                                <div>
                                                    <!-- <ul class="color-variant">
                                                        <li class="bg-light0"></li>
                                                        <li class="bg-light1"></li>
                                                        <li class="bg-light2"></li>
                                                    </ul> -->
                                                    <h4><i class="fa fa-rupee"></i> ${product.productPrice}</h4>
                                                </div>
                                            </div>
                                            <ul class="cart-detail">
                                                <li>
                                                    <button data-bs-toggle="modal" data-bs-target="#addtocart"
                                                        title="Add to cart" tabindex="0"><i
                                                            data-feather="shopping-cart"></i>add to cart</button>
                                                </li>
                                                <li>
                                                    <div class="qty-box">
					                                    <div class="input-group"><span class="input-group-prepend"><button type="button"
					                                                class="btn quantity-left-minus" data-type="minus" data-field=""><i
					                                                    class="ti-angle-left"></i></button> </span>
					                                        <input type="text" name="quantity" class="form-control input-number" value="1">
					                                        <span class="input-group-prepend"><button type="button"
					                                                class="btn quantity-right-plus" data-type="plus" data-field=""><i
					                                                    class="ti-angle-right"></i></button></span>
					                                    </div>
					                                </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    </c:if>
                                    </c:forEach>
                              
                              
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- tab section start -->


    <!-- footer start -->
 	<jsp:include page="footer.jsp"></jsp:include>
    <!-- footer end -->

    <!-- tap to top -->
    <div class="tap-top">
        <div>
            <i class="fa fa-angle-double-up"></i>
        </div>
    </div>
    <!-- tap to top end -->


    <!-- latest jquery-->
    <script src="../resources/js/jquery-3.3.1.min.js"></script>

    <!-- fly cart ui jquery-->
    <script src="../resources/js/jquery-ui.min.js"></script>

    <!-- slick js-->
    <script src="../resources/js/slick.js"></script>
    <script src="../resources/js/slick-animation.min.js"></script>

    <!-- menu js-->
    <script src="../resources/js/menu.js"></script>

    <!-- wow js-->
    <script src="../resources/js/wow.min.js"></script>

    <!-- feather icon js-->
    <script src="../resources/js/feather.min.js "></script>

    <!-- lazyload js-->
    <script src="../resources/js/lazysizes.min.js"></script>

    <!-- footer reveal js-->
    <script src="../resources/js/footer-reveal.min.js"></script>

    <!-- Bootstrap js-->
    <script src="../resources/js/bootstrap.bundle.min.js"></script>

    <!-- Bootstrap Notification js-->
    <script src="../resources/js/bootstrap-notify.min.js"></script>

    <!-- Theme js-->
    <script src="../resources/js/theme-setting.js"></script>
    <script src="../resources/js/script.js"></script>
    <script src="../resources/js/custom-slick-animated.js"></script>

	<script src="../resources/js/set-location.js"></script>
    <script>
        new WOW().init();
        feather.replace();
        $(window).on('load', function () {
            setTimeout(function () {
                $('#exampleModal').modal('show');
            }, 2500);
        });
        function openSearch() {
            document.getElementById("search-overlay").style.display = "block";
        }

        function closeSearch() {
            document.getElementById("search-overlay").style.display = "none";
        }
        if ($(window).width() > '576') {
            $('footer').footerReveal();
        }

    </script>

</body>

</html>