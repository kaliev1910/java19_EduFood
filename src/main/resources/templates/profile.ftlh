<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Complete Responsive Grocery Store Website</title>

    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">

    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700&display=swap');

        :root {
            --primary-color: #3bb77e;
            --secondary: #ffba43;
            --bg: #e2f9ee;
            --black: #1c2137;
            --white: #fff;
            --box-shadow: 0 .5rem 1rem rgba(0, 0, 0, 0.1);
        }

        /* Your CSS styles here */
    </style>
</head>
<body>

<header class="header">
    <a href="#" class="logo"> <i class="fas fa-shopping-cart"></i> Grocery </a>

    <nav class="navbar">
        <a href="/" class="nav-link">Home</a>
        <a href="#product" class="nav-link">Profile</a>
    </nav>

    <div class="log">
        <a class="j" href="/logout">
            <div>Log out</div>
        </a>
    </div>
</header>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="profile-container">
                <div class="profile-picture">
                    <img src="https://www.pngarts.com/files/10/Default-Profile-Picture-Download-PNG-Image.png"
                         alt="Profile Picture">
                </div>
                <div class="profile-info">
                    <h2>${cafe?exists(cafe.name, customer.name)}</h2>
                    <p>${cafe?exists(cafe.description, customer.email)}</p>
                    <#if cafe??>
                        <p>Contact: ${cafe.phoneNumber}</p>
                        <p>Address: ${cafe.address}</p>
                    </#if>
                </div>
                <a href="#">
                    <button class="btn btn-primary set-image-button">Set Image</button>
                </a>
            </div>
        </div>
    </div>

    <div class="row mt-5">
        <div class="col-md-10 offset-md-1">
            <div class="table-container">
                <table class="table">
                    <thead class="table-dark">
                    <tr>
                        <th scope="col">Dish Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Price</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list dishes.content as dish >
                        <tr>
                            <td>${dish.name}</td>
                            <td>${dish.description}</td>
                            <td>$${dish.price}</td>
                            <td>
                                <a href="/dishes/delete/${dish.id}" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <a href="/dishes/add" class="btn btn-success add-dish-button">Add Dish</a>
            </div>
        </div>
    </div>

    <#if cafe?? >
        <#if dishes.hasPrevious()>
            <a class="pag" href="/profile?page=${dishes.number - 1}">Previous Page</a>
        </#if>
        <#if dishes.hasNext()>
            <a class="pag" href="/profile?page=${dishes.number + 1}">Next Page</a>
        </#if>
    <#else>
        <#if carts.hasPrevious()>
            <a class="pag" href="/profile?page=${carts.number - 1}">Previous Page</a>
        </#if>
        <#if carts.hasNext()>
            <a class="pag" href="/profile?page=${carts.number + 1}">Next Page</a>
        </#if>
    </#if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
