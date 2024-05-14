<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login and Registration Page</title>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form action="/login" method="post" id="login-form">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email" name="username" placeholder="Enter your email address" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>

    <div class="mt-3">
        <p class="register-option">Don't have an account? <a href="/register">Register</a></p>
    </div>

    <div class="mt-3">
        <hr>
        <p class="text-center or-text">OR</p>
    </div>

    <div class="social-login">
        <div class="social-login-btn btn btn-light">
            <i class="fab fa-google"></i> Sign in with Google
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-XEyYxBlbxseYXYokLmexf4U3Vvu2n7cwC3SUqpyb/vFmz+JpP1V9Fq+JLqnpYNFd" crossorigin="anonymous"></script>
</body>
</html>
