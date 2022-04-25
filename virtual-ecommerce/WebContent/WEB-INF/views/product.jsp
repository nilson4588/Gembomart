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
    <link rel="icon" href="../resources/images/favicon/favicon.png" type="image/x-icon">
    <link rel="shortcut icon" href="../resources/images/favicon/favicon.png" type="image/x-icon">
    <title>GemboMart | Online Market Place| Akola | Amravati| Yawatmal| Buldhana| Washim </title>

    <!--Google font-->
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Yellowtail&display=swap" rel="stylesheet">

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
 

    <!-- breadcrumb start -->
    <div class="breadcrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="page-title">
                        <h2>product</h2>
                    </div>
                </div>
                <div class="col-sm-6">
                    <nav aria-label="breadcrumb" class="theme-breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">product</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- breadcrumb End -->


    <!-- section start -->
    <section>
        <div class="collection-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="product-slick">
                            <div><img src="${imgLocation}/${product.productImage}" alt=""
                                    class="img-fluid blur-up lazyload image_zoom_cls-0"></div>
                            <!-- <div><img src="../resources/images/pro3/2.jpg" alt=""
                                    class="img-fluid blur-up lazyload image_zoom_cls-1"></div>
                            <div><img src="../resources/images/pro3/27.jpg" alt=""
                                    class="img-fluid blur-up lazyload image_zoom_cls-2"></div>
                            <div><img src="../resources/images/pro3/27.jpg" alt=""
                                    class="img-fluid blur-up lazyload image_zoom_cls-3"></div> -->
                        </div>
                        <%-- <div class="row">
                            <div class="col-12 p-0">
                                <div class="slider-nav">
                                    <div><img src="${imgLocation}/${product.productImage}" alt=""
                                            class="img-fluid blur-up lazyload"></div>
                                    <div><img src="../resources/images/pro3/2.jpg" alt=""
                                            class="img-fluid blur-up lazyload"></div>
                                    <div><img src="../resources/images/pro3/27.jpg" alt=""
                                            class="img-fluid blur-up lazyload"></div>
                                    <div><img src="../resources/images/pro3/27.jpg" alt=""
                                            class="img-fluid blur-up lazyload"></div>
                                </div>
                            </div>
                        </div> --%>
                    </div>
                    <div class="col-lg-6 rtl-text">
                        <div class="product-right">
                            <div class="product-count">
                                <ul>
                                    <li>
                                        <img src="../resources/images/fire.gif" class="img-fluid" alt="image">
                                        <span class="p-counter">37</span>
                                        <span class="lang">orders in last 24 hours</span>
                                    </li>
                                    <li>
                                        <img src="../resources/images/person.gif" class="img-fluid user_img" alt="image">
                                        <span class="p-counter">44</span>
                                        <span class="lang">active view this</span>
                                    </li>
                                </ul>
                            </div>
                            <h2>${product.productName}</h2>
                            <div class="rating-section">
                                <div class="rating"><i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
                                        class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i>
                                </div>
                                <h6>120 ratings</h6>
                            </div>
                            <div class="label-section">
                                <span class="badge badge-grey-color">#1 Best seller</span>
                                <span class="label-text">in food</span>
                            </div>
                            <h3 class="price-detail"><i class="fa fa-rupee"></i> ${product.productPrice} <!-- <del>$459.00</del><span>55% off</span> --></h3>
                           <!--  <ul class="color-variant">
                                <li class="bg-light0 active"></li>
                                <li class="bg-light1"></li>
                                <li class="bg-light2"></li>
                            </ul> -->
                            <div id="selectSize" class="addeffect-section product-description border-product">
                                <!-- <h6 class="product-title size-text">select size <span><a href="" data-bs-toggle="modal"
                                            data-bs-target="#sizemodal">size
                                            chart</a></span></h6>
                                <div class="modal fade" id="sizemodal" tabindex="-1" role="dialog"
                                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Sheer
                                                    Straight Kurta</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            </div>
                                            <div class="modal-body"><img src="../resources/images/size-chart.jpg" alt=""
                                                    class="img-fluid blur-up lazyload"></div>
                                        </div>
                                    </div>
                                </div>
                                <h6 class="error-message">please select size</h6>
                                <div class="size-box">
                                    <ul>
                                        <li><a href="javascript:void(0)">s</a></li>
                                        <li><a href="javascript:void(0)">m</a></li>
                                        <li><a href="javascript:void(0)">l</a></li>
                                        <li><a href="javascript:void(0)">xl</a></li>
                                    </ul>
                                </div> -->
                                <h6 class="product-title">quantity</h6>
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
                            </div>
                            <div class="product-buttons"><a href="javascript:void(0)" id="cartEffect"
                                    class="btn btn-solid hover-solid btn-animation"><i class="fa fa-shopping-cart me-1"
                                        aria-hidden="true"></i> add to cart</a> <a href="#" class="btn btn-solid"><i
                                        class="fa fa-bookmark fz-16 me-2" aria-hidden="true"></i>wishlist</a></div>
                            <div class="product-count">
                                <ul>
                                    <li>
                                        <img src="../resources/images/icon/truck.png" class="img-fluid" alt="image">
                                        <span class="lang">Free shipping for orders</span>
                                    </li>
                                </ul>
                            </div>
                           
                            <div class="border-product">
                                <h6 class="product-title">shipping info</h6>
                                <ul class="shipping-info">
                                    <li>100% Original Products</li>
                                    <li>Free Delivery on order</li>
                                    <li>Pay on delivery is available</li>                                   
                                </ul>
                            </div>
                            
                            <div class="border-product">
                                <h6 class="product-title">100% secure payment</h6>
                                <img src="../resources/images/payment.png" class="img-fluid mt-1" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Section ends -->


 

    <!-- footer start -->
   <jsp:include page="footer.jsp"></jsp:include>
    <!-- footer end -->

  
    <!-- recently purchase product 
    <div class="media recently-purchase">
        <img src="../resources/images/pro3/sm.jpg" alt="Floral Dress ">
        <div class="media-body">
            <div>
                <div class="title">
                    Some recently purchase this item
                </div>
                <a href="#">
                    <span class="product-name">
                        Floral Dress
                    </span>
                </a>
                <small class="timeAgo">50 minutes ago</small>
            </div>
        </div>
        <a href="javascript:void(0)" class="close-popup fa fa-times"></a>
    </div>
    <!-- recently purchase product -->

    <!-- Add to cart modal popup start
    <div class="modal fade bd-example-modal-lg theme-modal cart-modal" id="addtocart" tabindex="-1" role="dialog"
        aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body modal1">
                    <div class="container-fluid p-0">
                        <div class="row">
                            <div class="col-12">
                                <div class="modal-bg addtocart">
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <div class="media">
                                        <a href="#">
                                            <img class="img-fluid blur-up lazyload pro-img"
                                                src="../resources/images/fashion/product/43.jpg" alt="">
                                        </a>
                                        <div class="media-body align-self-center text-center">
                                            <a href="#">
                                                <h6>
                                                    <i class="fa fa-check"></i>Item
                                                    <span>men full sleeves</span>
                                                    <span> successfully added to your Cart</span>
                                                </h6>
                                            </a>
                                            <div class="buttons">
                                                <a href="#" class="view-cart btn btn-solid">Your cart</a>
                                                <a href="#" class="checkout btn btn-solid">Check out</a>
                                                <a href="#" class="continue btn btn-solid">Continue shopping</a>
                                            </div>

                                            <div class="upsell_payment">
                                                <img src="../resources/images/payment_cart.png"
                                                    class="img-fluid blur-up lazyload" alt="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="product-section">
                                        <div class="col-12 product-upsell text-center">
                                            <h4>Customers who bought this item also.</h4>
                                        </div>
                                        <div class="row" id="upsell_product">
                                            <div class="product-box col-sm-3 col-6">
                                                <div class="img-wrapper">
                                                    <div class="front">
                                                        <a href="#">
                                                            <img src="../resources/images/fashion/product/1.jpg"
                                                                class="img-fluid blur-up lazyload mb-1"
                                                                alt="cotton top">
                                                        </a>
                                                    </div>
                                                    <div class="product-detail">
                                                        <h6><a href="#"><span>cotton top</span></a></h6>
                                                        <h4><span>$25</span></h4>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="product-box col-sm-3 col-6">
                                                <div class="img-wrapper">
                                                    <div class="front">
                                                        <a href="#">
                                                            <img src="../resources/images/fashion/product/34.jpg"
                                                                class="img-fluid blur-up lazyload mb-1"
                                                                alt="cotton top">
                                                        </a>
                                                    </div>
                                                    <div class="product-detail">
                                                        <h6><a href="#"><span>cotton top</span></a></h6>
                                                        <h4><span>$25</span></h4>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="product-box col-sm-3 col-6">
                                                <div class="img-wrapper">
                                                    <div class="front">
                                                        <a href="#">
                                                            <img src="../resources/images/fashion/product/13.jpg"
                                                                class="img-fluid blur-up lazyload mb-1"
                                                                alt="cotton top">
                                                        </a>
                                                    </div>
                                                    <div class="product-detail">
                                                        <h6><a href="#"><span>cotton top</span></a></h6>
                                                        <h4><span>$25</span></h4>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="product-box col-sm-3 col-6">
                                                <div class="img-wrapper">
                                                    <div class="front">
                                                        <a href="#">
                                                            <img src="../resources/images/fashion/product/19.jpg"
                                                                class="img-fluid blur-up lazyload mb-1"
                                                                alt="cotton top">
                                                        </a>
                                                    </div>
                                                    <div class="product-detail">
                                                        <h6><a href="#"><span>cotton top</span></a></h6>
                                                        <h4><span>$25</span></h4>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Add to cart modal popup end-->


    <!-- sticky cart bottom start 
    <div class="sticky-bottom-cart d-sm-block d-none">
        <div class="container">
            <div class="cart-content">
                <div class="product-image">
                    <img src="../resources/images/pro3/1.jpg" class="img-fluid" alt="">
                    <div class="content d-lg-block d-none">
                        <h5>WOMEN PINK SHIRT</h5>
                        <h6>$32.96<del>$459.00</del><span>55% off</span></h6>
                    </div>
                </div>
                <div class="selection-section">
                    <div class="form-group mb-0">
                        <select id="inputState" class="form-control">
                            <option selected>Choose color...</option>
                            <option>pink</option>
                            <option>blue</option>
                            <option>grey</option>
                        </select>
                    </div>
                    <div class="form-group mb-0">
                        <select id="inputState" class="form-control">
                            <option selected>Choose size...</option>
                            <option>small</option>
                            <option>medium</option>
                            <option>large</option>
                            <option>extra large</option>
                        </select>
                    </div>
                </div>
                <div class="add-btn">
                    <a data-bs-toggle="modal" data-bs-target="#addtocart" href="" class="btn btn-solid btn-sm">add to
                        cart</a>
                </div>
            </div>
        </div>
    </div>
    <!-- sticky cart bottom end -->

    <!-- tap to top start -->
    <div class="tap-top">
        <div><i class="fa fa-angle-double-up"></i></div>
    </div>
    <!-- tap to top end -->


    <!-- added to cart notification 
    <div class="added-notification">
        <img src="../resources/images/fashion/pro/1.jpg" class="img-fluid" alt="">
        <h3>added to cart</h3>
    </div>
     added to cart notification -->


    <!-- latest jquery-->
    <script src="../resources/js/jquery-3.3.1.min.js"></script>

    <!-- menu js-->
    <script src="../resources/js/menu.js"></script>

    <!-- lazyload js-->
    <script src="../resources/js/lazysizes.min.js"></script>

    <!-- sticky cart bottom js-->
    <script src="../resources/js/sticky-cart-bottom.js"></script>

    <!-- slick js-->
    <script src="../resources/js/slick.js"></script>

    <!-- timer js-->
    <script src="../resources/js/timer.js"></script>

    <!-- Bootstrap js-->
    <script src="../resources/js/bootstrap.bundle.min.js"></script>

    <!-- Bootstrap Notification js-->
    <script src="../resources/js/bootstrap-notify.min.js"></script>

    <!-- Zoom js-->
    <script src="../resources/js/jquery.elevatezoom.js"></script>

    <!-- Theme js-->
    <script src="../resources/js/theme-setting.js"></script>
    <script src="../resources/js/script.js"></script>
	<script src="../resources/js/set-location.js"></script>
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