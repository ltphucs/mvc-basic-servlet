<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<title>Colorlib Balita &mdash; Minimal Blog Template</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Josefin+Sans:300, 400,700"
	rel="stylesheet">

<link rel="stylesheet" href="ui-assets/css/bootstrap.css">
<link rel="stylesheet" href="ui-assets/css/animate.css">
<link rel="stylesheet" href="ui-assets/css/owl.carousel.min.css">

<link rel="stylesheet"
	href="ui-assets/fonts/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="ui-assets/fonts/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet" href="ui-assets/fonts/flaticon/font/flaticon.css">

<!-- Theme Style -->
<link rel="stylesheet" href="ui-assets/css/style.css">
</head>
<body>



	<header role="banner">
		<div class="top-bar">
			<div class="container">
				<div class="row">
					<div class="col-9 social">Travel Blog</div>
					<div class="col-3 search-top"></div>
				</div>
			</div>
		</div>

		
		<div class="container logo-wrap">
			<div class="row pt-5">
				<div class="col-12 text-center">
					<a class="absolute-toggle d-block d-md-none" data-toggle="collapse"
						href="#navbarMenu" role="button" aria-expanded="false"
						aria-controls="navbarMenu"><span class="burger-lines"></span></a>
					<h1 class="site-logo">
						<a href="index.html">Balita</a>
					</h1>
				</div>
			</div>
		</div>

		<nav class="navbar navbar-expand-md  navbar-light bg-light">
			<div class="container">


				<div class="collapse navbar-collapse" id="navbarMenu">
					<ul class="navbar-nav mx-auto">
						<li class="nav-item"><a class="nav-link active"
							href="index.html">Home</a></li>


						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="category.html"
							id="dropdown05" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Categories</a>
							<div class="dropdown-menu" aria-labelledby="dropdown05">
								<c:forEach items='${requestScope["listCat"]}' var='cate'>
									<a class="dropdown-item" href="category?id=${cate.id}">${cate.name}</a>
								</c:forEach>
							</div></li>
						<li class="nav-item"><a class="nav-link" href="about.html">About</a>
						</li>

					</ul>

				</div>
			</div>
		</nav>
	</header>
	
	<!-- END section -->

	<section class="site-section py-sm">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h2 class="mb-4">${cat.name}</h2>
				</div>
			</div>
			<div class="row blog-entries">
				<div class="col-md-12 col-lg-8 main-content">
					<div class="row">
						<!-- Khu vuc hien thi 82 blog moi nhat list12Blogs -->
						<c:forEach items='${requestScope["listBlog"]}' var='blog12s'>

							<div class="col-md-12">
								<a href="blog-single.html" class="blog-entry element-animate"
									data-animate-effect="fadeIn"> <img
									src="${blog12s.imageUrl}" alt="Image placeholder">
									<div class="blog-content-body">
										<div class="post-meta">
											<span class="category">${blog12s.categoryName}</span> <span
												class="mr-2"> ${blog12s.publishDate} </span> &bullet; <span
												class="ml-2"><span class="fa fa-comments"></span>
												${blog12s.likes}</span>
										</div>
										<h2>${blog12s.title}</h2>
									</div>
								</a>
							</div>

						</c:forEach>
					</div>








				</div>

				<!-- END main-content -->

				<div class="col-md-12 col-lg-4 sidebar">
					<div class="sidebar-box search-form-wrap">
						<form action="#" class="search-form">
							<div class="form-group">
								<span class="icon fa fa-search"></span> <input type="text"
									class="form-control" id="s"
									placeholder="Type a keyword and hit enter">
							</div>
						</form>
					</div>
					<!-- END sidebar-box -->
					<div class="sidebar-box">
						<div class="bio text-center">
							<img src="${about.imageUrl}" alt="Image Placeholder"
								class="img-fluid">
							<div class="bio-body">
								<h2>${about.title}</h2>
								<p>${about.shortContent}</p>
								<p>
									<a href="about" class="btn btn-primary btn-sm">Read my bio</a>
								</p>
								<p class="social">
									<a href="#" class="p-2"><span class="fa fa-facebook"></span></a>
									<a href="#" class="p-2"><span class="fa fa-twitter"></span></a>
									<a href="#" class="p-2"><span class="fa fa-instagram"></span></a>
									<a href="#" class="p-2"><span class="fa fa-youtube-play"></span></a>
								</p>
							</div>
						</div>
					</div>
					<!-- END sidebar-box -->
					<div class="sidebar-box">
						<!-- list5Popular HIEN THI 5 TIN XEM NHIEU NHAT O DAY -->
						<h3 class="heading">Popular Posts</h3>
						<div class="post-entry-sidebar">
							<ul>
								<c:forEach items='${requestScope["listTop5Popular"]}'
									var='blog5Popular'>
									<li><a href=""> <img src="${blog5Popular.imageUrl}"
											alt="Image placeholder" class="mr-4">
											<div class="text">
												<h4>${blog5Popular.title}</h4>
												<div class="post-meta">
													<span class="mr-2">${blog5Popular.publishDate} </span>
													&bullet; <span class="ml-2"><span
														class="fa fa-comments"></span> ${blog5Popular.likes}</span>
												</div>
											</div>
									</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<!-- END sidebar-box -->

					<div class="sidebar-box">
						<h3 class="heading">Categories</h3>
						<ul class="categories">
							<c:forEach items='${requestScope["listCat"]}' var='cate'>
								<li><a href="category?id=${cate.id}">${cate.name} </a></li>
							</c:forEach>
							
						</ul>
					</div>
					<!-- END sidebar-box -->


				</div>
				<!-- END sidebar -->

			</div>
		</div>
	</section>

	<footer class="site-footer">
		<div class="container">

			<div class="row">
				<div class="col-md-12">
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Copyright &copy;
					<script>
						document.write(new Date().getFullYear());
					</script>
					All rights reserved | This template is made with <i
						class="fa fa-heart-o" aria-hidden="true"></i> by <a
						href="https://colorlib.com" target="_blank">Colorlib</a>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</div>
			</div>
		</div>
	</footer>
	<!-- END footer -->

	<!-- loader -->
	<div id="loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#f4b214" /></svg>
	</div>

	<script src="ui-assets/js/jquery-3.2.1.min.js"></script>
	<script src="ui-assets/js/jquery-migrate-3.0.0.js"></script>
	<script src="ui-assets/js/popper.min.js"></script>
	<script src="ui-assets/js/bootstrap.min.js"></script>
	<script src="ui-assets/js/owl.carousel.min.js"></script>
	<script src="ui-assets/js/jquery.waypoints.min.js"></script>
	<script src="ui-assets/js/jquery.stellar.min.js"></script>


	<script src="ui-assets/js/main.js"></script>
</body>
</html>