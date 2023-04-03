<template>
    <div class="page">
        <div class="container" id="login">
            <h1>Login</h1>
            <label for="login_email">Email</label>
            <input type="text" name="login_email" id="login_email">
            <label for="login_password">Password</label>
            <input type="password" name="login_password" id="login_password">
            <a href="#" id="login_forgotPassword" style="display: block; width: 100%;">Forgot password?</a>
            <button id="login_button">Login</button>
            <div>
                <span>Not a member? </span>
                <a href="#" id="login_register">Register</a>
            </div>
        </div>

        <div class="container" id="register">
            <h1>Register</h1>
            <label for="register_name">Name</label>
            <input type="text" name="register_name" id="register_name">
            <label for="register_phoneNumber">Phone Number</label>
            <input type="text" name="register_phoneNumber" id="register_phoneNumber">
            <label for="register_email">Email</label>
            <input type="text" name="register_email" id="register_email">
            <label for="register_password">Password</label>
            <input type="password" name="register_password" id="register_password">
            <button id="register_button">Register</button>
            <div>
                <span href="#">Already have an account? </span>
                <a href="#" id="register_login">Login</a>
            </div>
        </div>

        <div class="container" id="recovery">
            <h1>Recovery</h1>
            <label for="recovery_email">Email</label>
            <input type="text" name="recovery_email" id="recovery_email">
            <button id="recovery_button">Recover</button>
            <div>
                <span href="#">Remember your password? </span>
                <a href="#" id="recovery_login">Login</a>
            </div>
        </div>
    </div>
</template>

<script src="./LoginPageScript.js">
</script>

<style scoped>
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

.page {
    display: flex;
    height: 100vh;
    align-items: center;
    justify-content: center;
}

.container {
    background-image: linear-gradient(45deg, #a0248f, #d34826);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 50px 40px;
    border-radius: 10px;
    box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.5);
}

.container h1 {
    display: block;
    width: 100%;
    text-align: center;
    padding-bottom: 40px;
}

.container label {
    display: block;
    width: 100%;
    margin-top: 20px;
}

.container input {
    min-width: 300px;
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border: none;
    border-bottom: 2px solid black;
    outline: none;
    background-color: transparent;
    color: #fff;
}

.container button {
    padding: 10px 20px;
    margin: 30px 0;
    border: none;
    border-radius: 5px;
    outline: none;
    background-color: #8f0030;
    color: #fff;
    cursor: pointer;
}

.container a {
    color: #161b27;
}
</style>