import $ from 'jquery'

export default {
    name: "AdminUsers",
    components: {
    },
    mounted() {
        // // Only show login form on page load
        // $("#register").hide();
        // $("#recovery").hide();

        // // Switch to recovery form
        // $("#login_forgotPassword").click(function () {
        //     $("#login").hide();
        //     $("#register").hide();
        //     $("#recovery").show();
        // });

        // // Switch to register form
        // $("#login_register").click(function () {
        //     $("#login").hide();
        //     $("#recovery").hide();
        //     $("#register").show();
        // });

        // // Switch to login form
        // $("#register_login").click(function () {
        //     $("#register").hide();
        //     $("#recovery").hide();
        //     $("#login").show();
        // });
        // $("#recovery_login").click(function () {
        //     $("#register").hide();
        //     $("#recovery").hide();
        //     $("#login").show();
        // });

        // // Login
        // $("#login_button").click(function() {
        //     console.log("clicked");
        //     $.ajax({
        //         url: "http://127.0.0.1:8080/api/auth/smokeTest",
        //         type: "GET"
        //     }).done(function(data) {
        //         console.log(data);
        //     });
        // });
    }
}