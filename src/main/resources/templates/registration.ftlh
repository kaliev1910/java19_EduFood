<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration and Login Page</title>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 400px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .container h2 {
            margin-bottom: 20px;
            color: #333;
        }

        form {
            width: 100%;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="tel"],
        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #3bb77e;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #00a86b;
        }

        .login-option {
            margin-top: 20px;
            font-size: 14px;
            color: #333;
        }

        .login-option a {
            color: #00a86b;
            text-decoration: none;
        }

        .login-divider {
            width: 100%;
            text-align: center;
            margin: 20px 0;
            position: relative;
        }

        .login-divider hr {
            position: absolute;
            top: 50%;
            left: 0;
            width: 100%;
            margin: 0;
        }

        .login-divider .or-text {
            background-color: #f9f9f9;
            padding: 5px 10px;
            font-weight: bold;
            color: #777;
        }

        .social-login {
            display: flex;
            flex-direction: column;
            gap: 10px;
            align-items: center;
            margin-top: 20px;
        }

        .social-login-btn {
            display: flex;
            align-items: center;
            gap: 10px;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 10px 15px;
            font-weight: bold;
            color: #333;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .social-login-btn:hover {
            background-color: #eee;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Registration</h2>
    <form action="/register" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" placeholder="Enter your name" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Enter your email address" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required>

        <label for="account_type">Account Type:</label>
        <select id="account_type" name="account_type" required>
            <option value="">Select account type</option>
            <option value="0">Customer</option>
            <option value="1">Cafe</option>
        </select>

        <div id="cafeFields" style="display: none;">
            <label for="cafe_name">Cafe Name:</label>
            <input type="text" id="cafe_name" name="cafe_name" placeholder="Enter cafe name">

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" placeholder="Enter cafe description">

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" placeholder="Enter cafe address">

            <label for="phone_number">Phone Number:</label>
            <input type="tel" id="phone_number" name="phone_number" placeholder="Enter phone number">
        </div>

        <input type="submit" value="Register">
    </form>

    <div class="login-option">
        Already have an account? <a href="/login">Login</a>
    </div>

    <div class="login-divider">
        <hr>
        <span class="or-text">OR</span>
    </div>

    <div class="social-login">
        <div class="social-login-btn">
            <i class="fab fa-google"></i> Sign in with Google
        </div>
    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
<script>

    const accountTypeSelect = document.getElementById('account_type');
    const cafeFields = document.getElementById('cafeFields');
    const cafeInputs = cafeFields.querySelectorAll('input');

    accountTypeSelect.addEventListener('change', function () {
        if (accountTypeSelect.value === '1') {
            cafeFields.style.display = 'block';
            cafeInputs.forEach(input => {
                input.setAttribute('required', 'required');
            });
        } else {
            cafeFields.style.display = 'none';
            cafeInputs.forEach(input => {
                input.removeAttribute('required');
            });
        }
    });
</script>
</body>
</html>
