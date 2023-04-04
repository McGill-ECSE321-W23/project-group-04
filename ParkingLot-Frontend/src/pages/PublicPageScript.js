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