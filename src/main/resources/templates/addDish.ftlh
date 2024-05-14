<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration and Login Page</title>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


    <style>
        /* Your existing custom styles here */
    </style>
</head>
<body>
<div class="container">
    <h2>Food</h2>
    <form action="/dishes/add" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <div class="mb-3">
            <label for="cafe_name" class="form-label">Food Name:</label>
            <input type="text" class="form-control" id="cafe_name" name="dish_name" placeholder="Enter dish name"
                   required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description:</label>
            <input type="text" class="form-control" id="description" name="description"
                   placeholder="Enter cafe description" required>
        </div>

        <div class="mb-3">
            <label for="address" class="form-label">Price:</label>
            <input type="number" class="form-control" id="address" name="price" placeholder="Enter price" required
                   min="0">
        </div>

        <button type="submit" class="btn btn-primary">Add</button>
    </form>

    <div class="login-option mt-4">
        Do you want to return?<a href="/profile" class="text-decoration-none ms-1">Back</a>
    </div>

</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>

</body>
</html>
