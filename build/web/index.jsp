<%-- 
    Document   : index
    Created on : 30/07/2017, 10:44:17 PM
    Author     : Elvis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Roperia Arce</title>
        <link rel="shortcut icon" href="libs/insititutional/img/favicon.ico" type="image/x-icon">
        <link rel="icon" href="libs/insititutional/img/favicon.ico" type="image/x-icon">
        <!-- Bootstrap Core CSS -->
        <link href="libs/insititutional/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom Fonts -->
        <link href='https://fonts.googleapis.com/css?family=Mrs+Sheppards%7CDosis:300,400,700%7COpen+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800;' rel='stylesheet' type='text/css'>
        <link href="libs/insititutional/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <!-- Plugin CSS -->
        <link href="libs/insititutional/css/animate.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom CSS -->
        <link href="libs/insititutional/css/style.css" rel="stylesheet" type="text/css"/>
        <!-- CSShake -->
        <link href="libs/CSShake/csshake.min.css" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
                <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
    </head>
    <body id="page-top">
        <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand page-scroll" href="#page-top"><img src="libs/insititutional/img/logo.png" alt="logolayana"></a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a class="page-scroll" href="#page-top">Inicio</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#about">Quienes Somos</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#services">Servicios</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#contact">Contactenos</a>
                        </li>
                        <li>
                            <a href="#" data-toggle="modal" data-target="#MyModal">Ingresar</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <!-- Section Intro Slider
        ================================================== -->
        <div id="carousel-example-generic" class="carousel intro slide">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <!-- First slide -->
                <div class="item active" style="background-image:url(https://s3-eu-west-1.amazonaws.com/layanademo/s1-min.jpg)">
                    <div class="carousel-caption">
                        <h2 data-animation="animated bounceInDown">Roperia Arce</h2>
                        <h1 data-animation="animated bounceInUp">
                            Tu estilo al mejor precio </h1>
                        <a href="#" class="btn btn-ghost btn-lg" data-animation="animated fadeInLeft">Start Tour</a><a href="#" class="btn btn-primary btn-lg" data-animation="animated fadeInRight">Learn More</a>
                    </div>
                </div>
                <!-- /.item -->
                <!-- Second slide -->
                <div class="item" style="background-image:url(https://s3-eu-west-1.amazonaws.com/layanademo/s2-min.jpg)">
                    <div class="carousel-caption">
                        <h2 data-animation="animated zoomInLeft">
                            Roperia Arce </h2>
                        <h1 data-animation="animated zoomInRight">
                            Contigo en todo momento</h1>
                        <a href="#" class="btn btn-ghost btn-lg" data-animation="animated bounceIn">Start Tour</a><a href="#" class="btn btn-primary btn-lg" data-animation="animated bounceIn">Learn More</a>
                    </div>
                </div>
                <!-- /.item -->
                <!-- Third slide -->
                <div class="item" style="background-image:url(https://s3-eu-west-1.amazonaws.com/layanademo/s3-min.jpg)">
                    <div class="carousel-caption">
                        <h2 data-animation="animated bounceInLeft">
                            Roperia Arce</h2>
                        <h1 data-animation="animated bounceInRight">
                            Lo que te gusta en donde te gusta</h1>
                        <a href="#" class="btn btn-ghost btn-lg" data-animation="animated zoomInRight">Start Tour</a><a href="#" class="btn btn-primary btn-lg" data-animation="animated zoomInRight">Learn More</a>
                    </div>
                </div>
                <!-- /.item -->
            </div>
            <!-- /.carousel-inner -->
            <!-- Controls (currently displayed none from style.css)-->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!-- /.carousel -->

        <!-- Section About
        ================================================== -->
        <section id="about">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 col-lg-offset-1 text-center">
                        <h2 class="section-heading">WHAT WE STAND FOR<br/>& OUR <b>PRINCIPLES</b></h2>
                        <hr>
                        <p>
                            Praesent vestibulum aenean nonummy hendrerit mauris. Cum sociis natoque penatibus et magnis dis parturient montes ascetur ridiculusmus. Nulla dui. Fusce feugiat. Praesent vestibulum aenean nonummy hendrerit mauris. Cum sociis natoque penatibus.
                        </p>
                        <p>
                            Fusce feugiat Praesent vestibulum aenean nonummy hendrerit mauris. Cum sociis natoque penatibus et magnis dis parturient montes ascetur ridiculusmus. Nulla dui. Fusce feugiat. Fusce feugiat Praesent vestibulum aenean nonummy hendrerit mauris. Cum sociis natoque penatibus et magnis dis parturient montes ascetur ridiculusmus. Nulla dui. Fusce feugiat.
                        </p>
                        <p>
                            Praesent vestibulum aenean nonummy hendrerit mauris. Cum sociis natoque penatibus et magnis dis parturient montes ascetur ridiculusmus. Nulla dui. Fusce feugiat. Fusce feugiat Praesent vestibulum aenean nonummy hendrerit mauris.
                        </p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Section after about
        ================================================== -->
        <section class="no-padding">
            <div class="equal-heights w-middle">
                <div class="col-md-4 bg-primary">
                    <div class="box">
                        <h2>Make a Difference</h2>
                        <p>
                            Praesent vestibulum aenean nonummy hendrerit mauris. Cum sociis natoque penatibus.
                        </p>
                        <a href="#" class="btn btn-round btn-white btn-xl">Find a Cause!</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <img src="https://s3-eu-west-1.amazonaws.com/layanademo/wom.jpg" alt="">
                </div>
                <div class="col-md-4 bg-dark">
                    <div class="box">
                        <h2>Real <b>Solutions</b></h2>
                        <p>
                            The simplest acts of kindness are by far more powerful then a thousand heads bowing in prayer. Let us not forget that the real solution lies in a world in which charity will be unnecessary.
                        </p>
                    </div>
                </div>
            </div>
            <div class="equal-heights w-middle">
                <div class="col-md-4 bg-gray">
                    <div class="box">
                        <h2>Give <b>Cheerfully</b></h2>
                        <p>
                            When we give cheerfully and accept gratefully, everyone is blessed. A bone to the dog is not charity. Charity is the bone shared with the dog, when you are just as hungry as the dog.
                        </p>
                    </div>
                </div>
                <div class="col-md-4 bg-darkgray">
                    <div class="box">
                        <h2>Real <b>Solutions</b></h2>
                        <p>
                            The simplest acts of kindness are by far more powerful then a thousand heads bowing in prayer. Let us not forget that the real solution lies in a world in which charity will be unnecessary.
                        </p>
                    </div>
                </div>
                <div class="col-md-4 bg-gray">
                    <div class="box">
                        <h2>Enjoy <b>Life</b></h2>
                        <p>
                            The simplest acts of kindness are by far more powerful then a thousand heads bowing in prayer. Let us not forget that the real solution lies in a world in which charity will be unnecessary.
                        </p>
                    </div>
                </div>
            </div>
        </section>
        <div class="clearfix">
        </div>

        <!-- Section Services
        ================================================== -->
        <section id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">OUR <b>SERVICES</b></h2>
                        <hr class="primary">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <!-- begin 1st row -->
                    <div class="col-lg-4 col-md-6 text-center">
                        <div class="service-box">
                            <div class="iconbox bg-primary">
                                <i class="fa fa-2x fa-trophy wow bounceIn text-fade"></i>
                            </div>
                            <h3>Bootstrap Template</h3>
                            <p class="text-muted">
                                Duis at risus dignissim, mollis augue quis, congue leo. Curabitur sed lacus ut lectus pharetra malesuada et sit amet ligula.
                            </p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 text-center">
                        <div class="service-box">
                            <div class="iconbox bg-dark">
                                <i class="fa fa-2x fa-cloud wow bounceIn text-fade" data-wow-delay=".1s"></i>
                            </div>
                            <h3>Responsive Layout</h3>
                            <p class="text-muted">
                                Duis at risus dignissim, mollis augue quis, congue leo. Curabitur sed lacus ut lectus pharetra malesuada et sit amet ligula.
                            </p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 text-center">
                        <div class="service-box">
                            <div class="iconbox bg-primary">
                                <i class="fa fa-2x fa-newspaper-o wow bounceIn text-fade" data-wow-delay=".2s"></i>
                            </div>
                            <h3>Theme Support</h3>
                            <p class="text-muted">
                                Duis at risus dignissim, mollis augue quis, congue leo. Curabitur sed lacus ut lectus pharetra malesuada et sit amet ligula.
                            </p>
                        </div>
                    </div>
                </div>
                <!-- end 1st row -->
                <br/>
                <div class="row">
                    <!-- begin 2nd row -->
                    <div class="col-lg-4 col-md-6 text-center">
                        <div class="service-box">
                            <div class="iconbox bg-dark">
                                <i class="fa fa-2x fa-yelp wow bounceIn text-fade"></i>
                            </div>
                            <h3>MultiPurpose Use</h3>
                            <p class="text-muted">
                                Duis at risus dignissim, mollis augue quis, congue leo. Curabitur sed lacus ut lectus pharetra malesuada et sit amet ligula.
                            </p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 text-center">
                        <div class="service-box">
                            <div class="iconbox bg-primary">
                                <i class="fa fa-2x fa-wordpress wow bounceIn text-fade" data-wow-delay=".1s"></i>
                            </div>
                            <h3>WordPress Available</h3>
                            <p class="text-muted">
                                Duis at risus dignissim, mollis augue quis, congue leo. Curabitur sed lacus ut lectus pharetra malesuada et sit amet ligula.
                            </p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 text-center">
                        <div class="service-box">
                            <div class="iconbox bg-dark">
                                <i class="fa fa-2x fa-desktop wow bounceIn text-fade" data-wow-delay=".2s"></i>
                            </div>
                            <h3>Infinite Colors</h3>
                            <p class="text-muted">
                                Duis at risus dignissim, mollis augue quis, congue leo. Curabitur sed lacus ut lectus pharetra malesuada et sit amet ligula.
                            </p>
                        </div>
                    </div>
                </div>
                <!-- end 2nd row -->
            </div>
        </section>
        <div class="clearfix">
        </div>

        <!-- Section Team
        ================================================== -->
        <section class="no-padding">
            <div class="col-md-4 bg-primary no-padding teambox">
                <div class="team-thumb overlay-image view-overlay">
                    <img src="https://s3-eu-west-1.amazonaws.com/layanademo/guess-attic-837156_640.jpg" alt="" class="img-responsive">
                    <div class="mask team_quote">
                        <div class="port-zoom-link">
                            <p>
                                Its is great to work in such company, some other projects will be started in the same format!
                            </p>
                        </div>
                    </div>
                </div>
                <h2>BEVERLY GARCIA</h2>
                <p>
                    VOLUNTEER WORK
                </p>
                <div class="team-social">
                    <a href="#"><i class="fa fa-twitter"></i></a>
                    <a href="#"><i class="fa fa-linkedin"></i></a>
                    <a href="#"><i class="fa fa-facebook"></i></a>
                    <a href="#"><i class="fa fa-google-plus"></i></a>
                    <a href="#"><i class="fa fa-skype"></i></a>
                </div>
            </div>
            <div class="col-md-4 bg-dark no-padding teambox">
                <div class="team-thumb overlay-image view-overlay">
                    <img src="https://s3-eu-west-1.amazonaws.com/layanademo/beauty-355157_640.jpg" alt="" class="img-responsive">
                    <div class="mask team_quote">
                        <div class="port-zoom-link">
                            <p>
                                Its is great to work in such company, some other projects will be started in the same format!
                            </p>
                        </div>
                    </div>
                </div>
                <h2>MARY LACOSTE</h2>
                <p>
                    LAYANA FOUNDER
                </p>
                <div class="team-social">
                    <a href="#"><i class="fa fa-twitter"></i></a>
                    <a href="#"><i class="fa fa-linkedin"></i></a>
                    <a href="#"><i class="fa fa-facebook"></i></a>
                    <a href="#"><i class="fa fa-google-plus"></i></a>
                    <a href="#"><i class="fa fa-skype"></i></a>
                </div>
            </div>
            <div class="col-md-4 bg-primary no-padding teambox">
                <div class="team-thumb overlay-image view-overlay">
                    <img src="https://s3-eu-west-1.amazonaws.com/layanademo/woman-983967_640.jpg" alt="" class="img-responsive">
                    <div class="mask team_quote">
                        <div class="port-zoom-link">
                            <p>
                                Its is great to work in such company, some other projects will be started in the same format!
                            </p>
                        </div>
                    </div>
                </div>
                <h2>CAROL STEPHENS</h2>
                <p>
                    PUBLIC RELATIONS
                </p>
                <div class="team-social">
                    <a href="#"><i class="fa fa-twitter"></i></a>
                    <a href="#"><i class="fa fa-linkedin"></i></a>
                    <a href="#"><i class="fa fa-facebook"></i></a>
                    <a href="#"><i class="fa fa-google-plus"></i></a>
                    <a href="#"><i class="fa fa-skype"></i></a>
                </div>
            </div>
        </section>
        <div class="clearfix">
        </div>

        <!-- Section Testimonials
        ================================================== -->
        <section id="testimonials">
            <div class="container">
                <div class="row">
                    <div class="col-md-12" data-wow-delay="0.2s">
                        <div class="carousel slide" data-ride="carousel" id="quote-carousel">
                            <!-- Bottom Carousel Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#quote-carousel" data-slide-to="0" class="active"><img class="img-responsive " src="libs/insititutional/img/demo/testimonial1.jpg" alt="">
                                </li>
                                <li data-target="#quote-carousel" data-slide-to="1"><img class="img-responsive" src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/11/2015/11/23042525/1-150x150.png" alt="">
                                </li>
                            </ol>
                            <!-- Carousel Slides / Quotes -->
                            <div class="carousel-inner text-center">
                                <!-- Quote 1 -->
                                <div class="item active">
                                    <blockquote>
                                        <div class="row">
                                            <div class="col-sm-8 col-sm-offset-2">
                                                <p>
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore!
                                                </p>
                                                <small>William W. Purkey</small>
                                            </div>
                                        </div>
                                    </blockquote>
                                </div>
                                <!-- Quote 2 -->
                                <div class="item">
                                    <blockquote>
                                        <div class="row">
                                            <div class="col-sm-8 col-sm-offset-2">
                                                <p>
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.
                                                </p>
                                                <small>Johnny Doe</small>
                                            </div>
                                        </div>
                                    </blockquote>
                                </div>
                                <!-- End Quote 2 -->
                            </div>
                            <!-- Carousel Buttons Next/Prev -->
                            <a data-slide="prev" href="#quote-carousel" class="left carousel-control"><i class="fa fa-chevron-left"></i></a>
                            <a data-slide="next" href="#quote-carousel" class="right carousel-control"><i class="fa fa-chevron-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Section Fun Facts
        ================================================== -->
        <section id="funfacts" class="parallax parallax-image" style="background-image:url(http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/11/2015/09/15223245/glamour-678834_1280.jpg);">
            <div class="wrapsection">
                <div class="container">
                    <div class="parallax-content">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="funfacts text-center">
                                    <div class="icon">
                                        <i class="fa fa-tint"></i>
                                    </div>
                                    <h2 class="counter" data-from="0" data-to="294" data-speed="2500"></h2>
                                    <h4>Happy Clients</h4>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="funfacts text-center">
                                    <div class="icon">
                                        <i class="fa fa-trophy"></i>
                                    </div>
                                    <h2 class="counter" data-from="0" data-to="163" data-speed="2500"></h2>
                                    <h4>Awards Received</h4>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="funfacts text-center">
                                    <div class="icon">
                                        <i class="fa fa-send-o"></i>
                                    </div>
                                    <h2 class="counter" data-from="0" data-to="317" data-speed="2500"></h2>
                                    <h4>Letters Sent</h4>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="funfacts text-center">
                                    <div class="icon">
                                        <i class="fa fa-user"></i>
                                    </div>
                                    <h2 class="counter" data-from="0" data-to="458" data-speed="2500"></h2>
                                    <h4>Hired People</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="clearfix">
        </div>

        <!-- Section Portfolio
        ================================================== -->
        <section id="portfolio">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading"><b>PORTFOLIO</b></h2>
                        <hr class="primary">
                        <br/>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row no-gutter">
                    <div class="col-lg-4 col-sm-6">
                        <a href="#" class="portfolio-box">
                            <img src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/11/2015/09/15222605/beauty-354565_1280-450x300.jpg" class="img-responsive" alt="">
                            <div class="portfolio-box-caption">
                                <div class="portfolio-box-caption-content">
                                    <div class="project-category text-faded">
                                        Category
                                    </div>
                                    <div class="project-name">
                                        Project Name
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a href="#" class="portfolio-box">
                            <img src="https://s3-eu-west-1.amazonaws.com/layanademo/woman-983967_640.jpg" class="img-responsive" alt="">
                            <div class="portfolio-box-caption">
                                <div class="portfolio-box-caption-content">
                                    <div class="project-category text-faded">
                                        Category
                                    </div>
                                    <div class="project-name">
                                        Project Name
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a href="#" class="portfolio-box">
                            <img src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/11/2015/09/30160348/woman-919047_1280-450x300.jpg" class="img-responsive" alt="">
                            <div class="portfolio-box-caption">
                                <div class="portfolio-box-caption-content">
                                    <div class="project-category text-faded">
                                        Category
                                    </div>
                                    <div class="project-name">
                                        Project Name
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a href="#" class="portfolio-box">
                            <img src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/11/2015/09/30160151/time-425818_1280-450x300.jpg" class="img-responsive" alt="">
                            <div class="portfolio-box-caption">
                                <div class="portfolio-box-caption-content">
                                    <div class="project-category text-faded">
                                        Category
                                    </div>
                                    <div class="project-name">
                                        Project Name
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a href="#" class="portfolio-box">
                            <img src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/11/2015/09/18160911/woman-659352_1280-450x300.jpg" class="img-responsive" alt="">
                            <div class="portfolio-box-caption">
                                <div class="portfolio-box-caption-content">
                                    <div class="project-category text-faded">
                                        Category
                                    </div>
                                    <div class="project-name">
                                        Project Name
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a href="#" class="portfolio-box">
                            <img src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/11/2015/09/15222605/woman-918614_1280-449x300.jpg" class="img-responsive" alt="">
                            <div class="portfolio-box-caption">
                                <div class="portfolio-box-caption-content">
                                    <div class="project-category text-faded">
                                        Category
                                    </div>
                                    <div class="project-name">
                                        Project Name
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <!-- Section Pricing
        ================================================== -->
        <section id="pricing" class="no-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading"><b>PRICING</b></h2>
                        <hr class="primary">
                        <br/>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="wow-pricing-table col-md-4">
                        <div class="wow-pricing">
                            <div class="wow-pricing-header">
                                <h5>Silver</h5>
                                <div class="wow-pricing-cost">
                                    $69.99
                                </div>
                                <div class="wow-pricing-per">
                                    per month
                                </div>
                            </div>
                            <div class="wow-pricing-content">
                                <ul>
                                    <li>5 products</li>
                                    <li>1 image per product</li>
                                    <li>basic stats</li>
                                    <li>non commercial</li>
                                </ul>
                            </div>
                            <div class="wow-pricing-button">
                                <a href="#" class="wow-button buttonprice" target="_self" rel="nofollow"><span class="wow-button-inner">Sign Up</span></a>
                            </div>
                        </div>
                    </div>
                    <!-- /.col-md-4 -->
                    <div class="wow-pricing-table col-md-4">
                        <div class="wow-pricing featured">
                            <div class="wow-pricing-header">
                                <h5>Premium</h5>
                                <div class="wow-pricing-cost">
                                    $339.99
                                </div>
                                <div class="wow-pricing-per">
                                    per month
                                </div>
                            </div>
                            <div class="wow-pricing-content">
                                <ul>
                                    <li>5 products</li>
                                    <li>1 image per product</li>
                                    <li>basic stats</li>
                                    <li>non commercial</li>
                                </ul>
                            </div>
                            <div class="wow-pricing-button">
                                <a href="#" class="wow-button buttonprice" target="_self" rel="nofollow"><span class="wow-button-inner">Sign Up</span></a>
                            </div>
                        </div>
                    </div>
                    <!-- /.col-md-4 -->
                    <div class="wow-pricing-table col-md-4">
                        <div class="wow-pricing">
                            <div class="wow-pricing-header">
                                <h5>Ultim</h5>
                                <div class="wow-pricing-cost">
                                    $429.99
                                </div>
                                <div class="wow-pricing-per">
                                    per month
                                </div>
                            </div>
                            <div class="wow-pricing-content">
                                <ul>
                                    <li>15 products</li>
                                    <li>4 image per product</li>
                                    <li>premium stats</li>
                                    <li>commercial</li>
                                </ul>
                            </div>
                            <div class="wow-pricing-button">
                                <a href="#" class="wow-button buttonprice" target="_self" rel="nofollow"><span class="wow-button-inner">Sign Up</span></a>
                            </div>
                        </div>
                    </div>
                    <!-- /.col-md-4 -->
                </div>
            </div>
        </section>
        <div class="clearfix">
        </div>

        <!-- Section after pricing
        ================================================== -->
        <section>
            <div class="equal-heights w-middle">
                <div class="col-md-4 bg-primary">
                    <div class="box">
                        <h2>Final <b>Action</b></h2>
                        <p>
                            Praesent vestibulum aenean nonummy hendrerit mauris. Cum sociis natoque penatibus.
                        </p>
                        <a href="#" class="btn btn-round btn-white btn-xl">Action Button</a>
                    </div>
                </div>
                <div class="col-md-4" style="height: 337px;">
                    <img src="https://s3-eu-west-1.amazonaws.com/layanademo/cat.jpg" alt="">
                </div>
                <div class="col-md-4 bg-dark">
                    <div class="box">
                        <h2><b>Convinced</b> Yet</h2>
                        <p>
                            The simplest acts of kindness are by far more powerful then a thousand heads bowing in prayer. Let us not forget that the real solution lies in a world in which charity will be unnecessary.
                        </p>
                    </div>
                </div>
            </div>
        </section>
        <div class="clearfix">
        </div>

        <!-- Section Timeline
        ================================================== -->
        <section id="tline">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading"><b>Timeline</b></h2>
                    </div>
                </div>
            </div>
            <br/>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <ul class="tline-holder">
                            <!-- tline ITEM-->
                            <li class="tline-item-left wow fadeInLeft">
                                <div class="tline-item-content">
                                    <div class="date-icon fa fa-rocket">
                                    </div>
                                    <div class="tline-item-txt text-right">
                                        <div class="meta">
                                            January 2017
                                        </div>
                                        <h3>Love Fashion</h3>
                                        <p>
                                            It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <!-- /tline-->
                            <!-- tline-->
                            <li class="tline-item-right wow fadeInRight">
                                <div class="tline-item-content">
                                    <div class="date-icon fa fa-camera">
                                    </div>
                                    <div class="tline-item-txt text-left">
                                        <div class="meta">
                                            December 2016
                                        </div>
                                        <h3>Green is Health</h3>
                                        <p>
                                            It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <!-- /tline-->
                            <!-- tline-->
                            <li class="tline-item-left wow fadeInLeft">
                                <div class="tline-item-content">
                                    <div class="date-icon fa fa-user">
                                    </div>
                                    <div class="tline-item-txt text-right">
                                        <div class="meta">
                                            November 2016
                                        </div>
                                        <h3>Why you love us</h3>
                                        <p>
                                            It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <!-- /tline-->
                            <!-- tline-->
                            <li class="tline-item-right wow fadeInRight">
                                <div class="tline-item-content">
                                    <div class="date-icon fa fa-bullhorn">
                                    </div>
                                    <div class="tline-item-txt text-left">
                                        <div class="meta">
                                            September 2016
                                        </div>
                                        <h3>Save our Planet</h3>
                                        <p>
                                            It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <!-- /tline-->
                            <li class="tline-start">
                                <div class="tline-start-content">
                                    <div class="tline-start-icon">
                                    </div>
                                    <a href="#" class="btn btn-primary bgn-xl wow zoomIn">START</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <!-- Section Social
        ================================================== -->
        <section id="social" class="parallax parallax-image" style="background-image:url(https://s3-eu-west-1.amazonaws.com/layanademo/s3-min.jpg);">
            <div class="overlay" style="background:#222;opacity:0.5;">
            </div>
            <div class="wrapsection">
                <div class="container">
                    <div class="parallax-content">
                        <div class="row wow fadeInLeft">
                            <div class="col-md-3">
                                <div class="funfacts text-center">
                                    <div class="icon">
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                    </div>
                                    <h4>Twitter</h4>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="funfacts text-center">
                                    <div class="icon">
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                    </div>
                                    <h4>Facebook</h4>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="funfacts text-center">
                                    <div class="icon">
                                        <a href="#"><i class="fa fa-google"></i></a>
                                    </div>
                                    <h4>Google</h4>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="funfacts text-center">
                                    <div class="icon">
                                        <a href="#"><i class="fa fa-wordpress"></i></a>
                                    </div>
                                    <h4>Blog</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="clearfix">
        </div>

        <!-- Section Contact
        ================================================== -->
        <section id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center">
                        <h2 class="section-heading">CONTACT <b>US</b></h2>
                        <hr class="primary">
                        <p>
                            Ready to start your next project with us? That's great! Give us a call or send us an email and we will get back to you as soon as possible!
                        </p>
                        <div class="regularform">
                            <div class="done">
                                <div class="alert alert-success">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    Your message has been sent. Thank you!
                                </div>
                            </div>
                            <form method="post" action="contact.php" id="contactform" class="text-left">
                                <input name="name" type="text" class="col-md-6 norightborder" placeholder="Your Name *">
                                <input name="email" type="email" class="col-md-6" placeholder="E-mail address *">
                                <textarea name="comment" class="col-md-12" placeholder="Message *"></textarea>
                                <input type="submit" id="submit" class="contact submit btn btn-primary btn-xl" value="Send message">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Section Footer
        ================================================== -->
        <section class="bg-dark">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <h1 class="bottombrand wow flipInX">Roperia Arce</h1>
                        <p>
                            &copy; 2017 Todos los derechos reservados
                        </p>
                    </div>
                </div>
            </div>
        </section>
        <div class="modal fade" id="MyModal" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <center>
                            <h2 class="modal-title"><strong>WARDROVE</strong></h2>
                        </center>
                    </div>
                    <form id="form">
                        <div class="modal-body">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="user">Usuario</label>
                                    <input type="text" class="form-control" id="user" placeholder="Usuario" required>
                                </div>
                                <div class="form-group">
                                    <label for="pass">Contraseña</label>
                                    <input type="password" class="form-control" id="pass" placeholder="Contraseña" required>
                                </div>
                                <center>
                                    <div id="error" class="hide">
                                        <p class="text text-danger">Usuario o Contraseña Invalidos!</p>
                                    </div>
                                </center>
                            </div>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                            <button type="submit" id="ingresar" class="btn btn-primary" >Ingresar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script src="libs/insititutional/js/jquery.js" type="text/javascript"></script>
        <script src="libs/insititutional/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="libs/insititutional/js/parallax.js" type="text/javascript"></script>
        <script src="libs/insititutional/js/contact.js" type="text/javascript"></script>
        <script src="libs/insititutional/js/countto.js" type="text/javascript"></script>
        <script src="libs/insititutional/js/jquery.easing.min.js" type="text/javascript"></script>
        <script src="libs/insititutional/js/wow.min.js" type="text/javascript"></script>
        <script src="libs/insititutional/js/common.js" type="text/javascript"></script>
        <script src="libs/adminLTE/pages/dashboard.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                $("#form").submit(function (e) {
                    e.preventDefault();
                    var datos = {
                        "user": $("#user").val(),
                        "pass": $("#pass").val()
                    };
                    $.ajax({
                        data: datos,
                        type: "post",
                        url: "loginControlador",
                        success: function (response) {
                            if (response != 0) {
                                userData(function (data) {
                                    if (JSON.stringify(data) === '{}') {
                                        $("#error").removeClass("hide").addClass("show shake-horizontal shake-constant");
                                        setTimeout(function () {
                                            $("#error").removeClass("shake-horizontal shake-constant");
                                        }, 500);
                                    } else {
                                        location.href = 'dashboard.jsp';
                                    }
                                });
                            } else {
                                $("#error").removeClass("hide").addClass("show shake-horizontal shake-constant");
                                setTimeout(function () {
                                    $("#error").removeClass("shake-horizontal shake-constant");
                                }, 500);
                            }
                        }
                    });
                });
            });
        </script>
    </body>
</html>