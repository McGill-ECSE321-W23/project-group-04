import $ from 'jquery'
import CustomHeader from '../components/CustomHeader.vue'

export default {
    name: "PublicPage",
    components: {
        CustomHeader
    },
    data() {
        return {
            tabs: [
                { title: 'Home', id: "home" },
                { title: 'Services', id: "services" },
                { title: 'My Account', id: "myAccount" }
            ]
        }
    },
    mounted() {
        // Get tabs from vue data into jquery
        var tabs = this.tabs;

        // Set all tabs as inactive
        function clearActive() {
            tabs.forEach(function (tab) {
                $("#tabBody_" + tab.id).removeAttr("active");
                $("#tabHeader_" + tab.id).removeAttr("active");
            });
        }

        // Home tab
        $("#tabHeader_home").on("click", function () {
            clearActive();
            $("#tabBody_home").attr("active", "");
            $("#tabHeader_home").attr("active", "");
        });

        // Services tab
        $("#tabHeader_services").on("click", function () {
            clearActive();
            $("#tabBody_services").attr("active", "");
            $("#tabHeader_services").attr("active", "");
        });

        // My Account tab
        $("#tabHeader_myAccount").on("click", function () {
            clearActive();
            $("#tabBody_myAccount").attr("active", "");
            $("#tabHeader_myAccount").attr("active", "");
        });

        // Set home tab as active
        $("#tabBody_home").attr("active", "");
        $("#tabHeader_home").attr("active", "");
    }
}


