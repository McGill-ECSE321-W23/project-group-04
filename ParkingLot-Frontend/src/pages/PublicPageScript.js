import $ from 'jquery';
import Header from '../components/Header.vue';

export default {
    name: 'PublicPage',
    components: {
        Header
    },
    data() {
        return {
            tabs: []
        }
    },
    created() {
        this.tabs.push({
            title: 'Home',
            index: 'home',
            bodyID: 'page_home'
        });
        this.tabs.push({
            title: 'Services',
            index: 'services',
            bodyID: 'page_services'
        });
        this.tabs.push({
            title: 'Profile',
            index: 'profile',
            bodyID: 'page_profile'
        });
    },
    mounted() {
        
    }
};