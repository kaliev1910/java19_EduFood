<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order food</title>

    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Your existing custom styles here */
    </style>
</head>
<body>

<header class="header">
    <a href="#" class="logo"> <i class="fas fa-shopping-cart"></i> Shop</a>
    <nav class="navbar">
        <a href="#home" class="nav-link">home</a>
        <#if username??><a href="/profile" class="nav-link">profile</a></#if>
    </nav>

    <#if username??>
        <div class="log">
            <a class="j" href="/logout">
                <div>log out</div>
            </a>
        </div>
    <#else>
        <div class="log">
            <a class="j" href="/login">
                <div>sign in</div>
            </a>
            <a class="j" href="/register">
                <div>sign up</div>
            </a>
        </div>
    </#if>
</header>
<section class="product" id="product">

    <h1 class="heading"> our <span> restaurants</span></h1>

    <div class="wrap">
        <form action="/" method="POST">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>

            </#if>

            <div class="search">
                <input type="text" class="form-control searchTerm" name="searchTerm" placeholder="What are you looking for?">
                <button type="submit" class="btn btn-primary searchButton">
                    <i class="fa fa-search"></i>
                </button>
            </div>
        </form>
    </div>


    <#if restaurant??>
    <div class="box-container">
        <div class="box">
            <div class="image">
                <img src="https://img.myloview.com/stickers/chef-hat-vector-logo-chef-hat-logo-vector-illustration-restaurant-logo-cooking-logo-700-229284823.jpg"
                     alt="" class="img-fluid">
            </div>
            <div class="content">
                <h3 class="text-center">${restaurant.name}</h3>
                <div class="stars text-center">
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star-half-alt"></i>
                </div>
                <a href="/foods/${restaurant.id}" class='btn btn-primary mx-auto d-block'>Learn More</a>
            </div>
        </div>
    </div>
</section>
<#else>
    <div class="box-container">
        <#list restaurants.content as restaurant>
            <div class="box">
                <div class="image">
                    <img src="https://img.myloview.com/stickers/chef-hat-vector-logo-chef-hat-logo-vector-illustration-restaurant-logo-cooking-logo-700-229284823.jpg"
                         alt="" class="img-fluid">
                </div>
                <div class="content">
                    <h3 class="text-center">${restaurant.name}</h3>
                    <div class="stars text-center">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star-half-alt"></i>
                    </div>
                    <a href="/foods/${restaurant.id}" class='btn btn-primary mx-auto d-block'>Learn More</a>
                </div>
            </div>
        </#list>
    </div>
    </section>
    <#if restaurants.hasPrevious()>
        <a class="pag btn btn-primary" href="/?page=${restaurants.number - 1}">Previous Page</a>
    </#if>
    <#if restaurants.hasNext()>
        <a class="pag btn btn-primary" href="/?page=${restaurants.number + 1}">Next Page</a>
    </#if>
</#if>
<div class="rp"></div>
</body>
</html>
