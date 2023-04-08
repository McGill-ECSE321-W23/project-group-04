import Header from '../components/Header.vue';
import HomePage from './tabs/HomePage.vue';
import Reservations from "./tabs/Reservations.vue";
import ServiceAppointmentTab from "./tabs/ServiceAppointmentTab.vue";
import UserProfile from "@/pages/tabs/UserProfile.vue";

export default {
    name: 'PublicPage',
    components: {
        UserProfile,
        Header,
        HomePage,
        Reservations,
        ServiceAppointmentTab
    },
    data() {
        return {
            tabs: []
        }
    },
    created() {
        this.tabs.push({
            title: 'HOME',
            index: 'home',
            bodyID: 'page_home'
        });
        this.tabs.push({
            title: 'SERVICES',
            index: 'services',
            bodyID: 'page_services'
        });
        this.tabs.push({
            title: 'MY PROFILE',
            index: 'profile',
            bodyID: 'page_profile'
        });
    }
};