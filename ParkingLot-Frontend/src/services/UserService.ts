import type {AxiosInstance} from "axios/index";

export default class UserService {
    private axios: AxiosInstance;
    constructor(axiosInstance: AxiosInstance) {
        this.axios = axiosInstance
    }
        async getAccount() {
            try {
                let loggedIn = await this.axios.get('api/account/get/'+ this.getCookie("accountId"))
                return loggedIn.data
            } catch (err) {
                return null
            }
        }
        async isStaff() {
            try {
                let loggedIn = await this.axios.get('api/auth/isStaff')
                return loggedIn.data as boolean
            } catch (err) {
                return false
            }

        }
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