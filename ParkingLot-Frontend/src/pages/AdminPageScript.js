import Header from '../components/Header.vue';

export default {
    name: 'AdminPage',
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
            title: 'SYSTEM',
            index: 'system',
            bodyID: 'page_system'
        });
        this.tabs.push({
            title: 'SERVICES/GARAGES',
            index: 'servicesAndGarages',
            bodyID: 'page_servicesAndGarages'
        });
        this.tabs.push({
            title: 'USERS',
            index: 'users',
            bodyID: 'page_users'
        });
        this.tabs.push({
            title: 'SCHEDULES',
            index: 'schedules',
            bodyID: 'page_schedules'
        });
        this.tabs.push({
            title: 'BOOK RESERVATION',
            index: 'bookReservation',
            bodyID: 'page_bookReservation'
        });
    }
};