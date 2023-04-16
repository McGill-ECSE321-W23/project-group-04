import $ from 'jquery'
import axios from 'axios';
import router from '@/router'
import Header from '../components/Header.vue';
import AdminUsers from './tabs/AdminUsers.vue';
import BookReservation from './tabs/BookReservation.vue';
import StaffSchedule from './tabs/StaffSchedule.vue';
import SystemManager from './tabs/SystemManager.vue';
import ServicesAndGaragesTab from './tabs/ServicesAndGaragesTab.vue';

export default {
    name: 'AdminPage',
    components: {
        Header,
        AdminUsers,
        BookReservation,
        StaffSchedule,
        SystemManager,
        ServicesAndGaragesTab
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
    },
    mounted() {
        axios.get('http://localhost:8080/api/auth/isStaff', {
            withCredentials: true,
            headers: {
                "Access-Control-Allow-Origin": 'localhost:8080'
            }
        })
        .then(response => { // logged in, need to check if is staff
            if (response.data === false) {
                router.push('/');
            }
        })
        .catch(error => {   // not logged in
            console.log(error);
            router.push('/login');
            alert('You are not logged in. Redirecting to login page...');
        });
    }
};
