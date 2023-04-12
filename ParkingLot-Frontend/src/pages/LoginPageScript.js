import {reactive} from 'vue'
import $ from 'jquery'
import router from '@/router'
import axios from 'axios';

export default {
    name: "LoginPage",
    components: {
    },
    setup() {
        const loginForm = reactive({
            email: '',
        })

        return {
            loginForm
        }
    },
    mounted() {
        // Logo
        $("#img_logo").click(function () {
            window.location.href = '/';
        });

        // Only show login form on page load
        $("#register").hide();
        $("#recovery").hide();

        // Switch to recovery form
        $("#login_forgotPassword").click(function () {
            $("#login").hide();
            $("#register").hide();
            $("#recovery").show();
        });

        // Switch to register form
        $("#login_register").click(function () {
            $("#login").hide();
            $("#recovery").hide();
            $("#register").show();
        });

        // Switch to login form
        $("#register_login").click(function () {
            $("#register").hide();
            $("#recovery").hide();
            $("#login").show();
        });
        $("#recovery_login").click(function () {
            $("#register").hide();
            $("#recovery").hide();
            $("#login").show();
        });

        // Login
        $("#login_button").click(function() {
            axios.post('http://localhost:8080/api/auth/login', {}, {
                withCredentials: true,
                headers: {
                    "Access-Control-Allow-Origin": 'localhost:8080',
                },
                params: {
                    email: 'testemail',
                    password: '123'
                }
            })
            .then((res) => {
                // console.log(document.cookie)
                // document.cookie = res.headers['Set-Cookie']
                // console.log(document.cookie)
                console.log(res)
                router.push("/")

            })
            .catch(err => {
                console.log(err)
            })
            // $.ajax({
            //     url: "http://127.0.0.1:8080/api/auth/login",
            //     type: "POST",
            //     data: {
            //         email: $("#login_email").val(),
            //         password: $("#login_password").val()
            //     },
            //     success: function(data, status, xhr) {
            //         alert(xhr.getResponseHeader('Set-Cookie'));
            //         // router.push("/")
            //         // window.location.href = '/';
            //
            //         // Check if the user is logged in
            //         // $.ajax({
            //         //     url: 'http://127.0.0.1:8080/api/auth/isLoggedIn',
            //         //     type: 'GET',
            //         //     success: function(data) {
            //         //         console.log(data);
            //         //     },
            //         //     error: function(data) {
            //         //         console.log(data);
            //         //     }
            //         // });
            //     },
            //     error: function(data) {
            //         console.log("error "+ data);
            //     }
            // });
        });
        
        // Register
        $("#register_button").click(function() {
            $.ajax({
                url: "http://127.0.0.1:8080/api/account/register",
                type: "POST",
                data: {
                    email: $("#register_email").val(),
                    password: $("#register_password").val(),
                    name: $("#register_name").val(),
                    phoneNumber: $("#register_phoneNumber").val()
                },
                success: function(data) {
                    console.log(data);
                    // switch to login form
                    $("#register").hide();
                    $("#recovery").hide();
                    $("#login").show();
                },
                error: function(data) {
                    console.log(data);
                }
            });
        });

        // Recover (smoke test)
        $("#recovery_button").click(function() {
            $.ajax({
                url: "http://127.0.0.1:8080/api/auth/smokeTest",
                type: "GET",
                success: function(data) {
                    console.log(data);
                    window.location.href = '/';
                },
                error: function(data) {
                    console.log(data);
                }
            });
        });
    },
}
