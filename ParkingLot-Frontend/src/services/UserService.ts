import axios from "axios";

export const UserService = {

    async getAccount() {
        try {
            let loggedIn = await axios.get('http://localhost:8080/api/account/get/'+ this.getCookie("accountId"), {
                withCredentials: true,
                headers: {
                    "Access-Control-Allow-Origin": 'localhost:8080'
                }
            })
            return loggedIn.data
        } catch (err) {
            return null
        }
    },

     async isStaff() {
        try {
            let loggedIn = await axios.get('http://localhost:8080/api/auth/isStaff', {
                withCredentials: true,
                headers: {
                    "Access-Control-Allow-Origin": 'localhost:8080'
                }
            })
            return loggedIn.data as boolean
        } catch (err) {
            return false
        }

    },
    async checkIsLoggedIn() {
        try {
            const res = await axios.get('http://localhost:8080/api/auth/isLoggedIn', {
                withCredentials: true,
                headers: {
                    "Access-Control-Allow-Origin": 'localhost:8080'
                }
            });
            return res.data;
        } catch (err) {
            console.log(err);
            return false;
        }
    },
    getCookie(cname: string): string {
        let name = cname + "=";
        let ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                let s = c.substring(name.length, c.length);
                console.log(s)
                return s
            }
        }
        return "";
    }

}