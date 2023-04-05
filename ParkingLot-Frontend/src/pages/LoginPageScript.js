import { reactive, ref } from 'vue'
import $ from 'jquery'

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
    methods: {
        onLogin() {
            console.log("log in");
        },
        onRegister() {
            console.log("register");
        },
        onRecover() {
            console.log("recover");
        }
    },
    mounted() {
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
            $.ajax({
                url: "http://127.0.0.1:8080/api/auth/login",
                type: "POST",
                data: {
                    email: $("#login_email").val(),
                    password: $("#login_password").val()
                },
                success: function(data) {
                    console.log(data);
                },
                error: function(data) {
                    console.log(data);
                }
            });
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
                },
                error: function(data) {
                    console.log(data);
                }
            });
        });
    }
}
