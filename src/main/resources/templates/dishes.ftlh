<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <title>Complete Responsive Grocery Store Website</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>

    <!-- font awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Your existing CSS styles here */
    </style>

</head>
<body>

<header class="header">
    <a href="#" class="logo"> <i class="fas fa-shopping-cart"></i> Grocery </a>

    <nav class="navbar">
        <a href="/">home</a>
        <#if username??><a href="/profile">profile</a></#if>
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


<section class="blog" id="blog">
    <h1 class="heading">${cafe.name} </h1>
    <div class="box-container">
        <div class="box">
            <div class="image">
                <img src="https://as1.ftcdn.net/v2/jpg/02/52/38/80/1000_F_252388016_KjPnB9vglSCuUJAumCDNbmMzGdzPAucK.jpg"
                     alt="">
            </div>
            <div class="content">
                <div class="icons">
                    <a href="#"> <i class="fas fa-user"></i> ${cafe.getUser().name} </a>
                </div>
                <h3>About us</h3>
                <p>${cafe.description}</p>
            </div>
        </div>
    </div>
</section>


<section class="product" id="product">
    <div class="box-container">
        <#list dishes.content as dish >
            <div class="box">
                <div class="image">
                    <img src="https://s.tmimgcdn.com/scr/800x500/253100/modelo-de-logotipo-de-servico-de-catering-para-todos-os-servicos-de-alimentacao_253135-original.jpg"
                         alt="">
                </div>
                <div class="content">
                    <h3 class="fn">${dish.name}</h3>
                    <div class="price">$${dish.price}</div>
                    <#if customer?? >
                    <div class="add-btn" data-user-id="${customer.id}" data-dish-id="${dish.id}"><i
                                class="fas fa-shopping-cart"></i></div></#if>
                </div>
            </div>
        </#list>
    </div>
</section>
<#if dishes.hasPrevious()>
    <a class="pag" href="/dishes/${cafe.id}?page=${dishes.number - 1}">Previous Page</a>
</#if>
<#if dishes.hasNext()>
    <a class="pag" href="/dishes/${cafe.id}?page=${dishes.number + 1}">Next Page</a>
</#if>
<div class="rp"></div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const addButtons = document.querySelectorAll(".add-btn");
        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

        addButtons.forEach(function (button) {
            button.addEventListener("click", function (event) {
                const dishId = button.getAttribute("data-dish-id");
                const userId = button.getAttribute("data-user-id");

                console.log("Dish ID clicked:", dishId);

                if (confirm('Are you sure you want to add to cart?')) {
                    const data = {
                        dishId: dishId,
                        userId: userId
                    };

                    fetch('/cart/add', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'X-CSRF-TOKEN': csrfToken
                        },
                        body: JSON.stringify(data)
                    })
                        .then(response => {
                            if (response.ok) {
                                alert('Item added to cart successfully!');
                            } else {
                                alert('Failed to add item to cart.');
                            }
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                }
            });
        });
    })


</script>
</body>
</html>
