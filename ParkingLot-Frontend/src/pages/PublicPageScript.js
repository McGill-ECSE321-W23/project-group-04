import Header from '../components/Header.vue';
import Reservations from "@/pages/tabs/Reservations.vue";

export default {
    name: 'PublicPage',
    components: {
        Reservations,
        Header
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
            title: 'RESERVATIONS',
            index: 'reservations',
            bodyID: 'page_reservations'
        });
        this.tabs.push({
            title: 'MY PROFILE',
            index: 'profile',
            bodyID: 'page_profile'
        });
    }
};