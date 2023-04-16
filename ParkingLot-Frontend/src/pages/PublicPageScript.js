import Header from '../components/Header.vue';
import HomePage from './tabs/HomePage.vue';
import Reservations from "./tabs/Reservations.vue";
import ServiceAppointmentTab from "./tabs/ServiceAppointmentTab.vue";
import UserProfile from "@/pages/tabs/UserProfile.vue";
import {inject} from "vue";

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
            tabs: [],
            loggedIn: false,
            isStaff: false
        }
    },
    created() {
        const api = inject('axios')

        this.tabs.push({
            title: 'HOME',
            index: 'home',
            bodyID: 'page_home'
        });

        // Check if the user is logged in
        api.get('api/auth/isLoggedIn')
            .then(res => {
                this.loggedIn = res.data;
                if (this.loggedIn) {
                    api.get('api/auth/isStaff')
                        .then(resp => {
                            this.isStaff = resp.data
                            console.log("isStaff: " + this.isStaff);
                        })
                        .catch(err => {
                            console.log(err);
                        })
                        .finally(() => {
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
                        })
                }
                console.log("isLoggedIn: " + this.loggedIn);
            })
            .catch(err => {
                console.log(err);
            });
    }
};