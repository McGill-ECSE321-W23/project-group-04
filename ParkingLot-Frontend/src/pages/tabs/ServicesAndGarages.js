import { Delete, ArrowLeft } from '@element-plus/icons-vue'
// import { inject } from "vue";
import axios from 'axios'
// import ServicesAndGaragesService from "@/service/ServicesAndGaragesService";

var AXIOS = axios.create({
    baseURL: 'http://localhost:8080/api',   // backend url
    withCredentials: true,
    headers: {
        "Access-Control-Allow-Origin": 'localhost:5173' // frontend url
    }
})


export default {
    name: 'servicesAndGaragesManagement',

    data() {
        return {

            // Main page

            // Offered services
            showOfferedServicesEdit: true,
            offeredServices: [],
            selectedOfferedServiceRow: '',
            showErrorEditOfferedService: false,
            errorOfferedService: '',

            // Garages
            showGaragesEdit: true,
            garages: [],
            selectedGarageRow: '',
            showErrorEditGarage: false,
            errorGarage: '',
            
            // Edit

            // Offered services
            showOfferedServiceEdit: false,
            editOfferedServiceDescription: '',
            editOfferedServiceDuration: '',
            editOfferedServiceCost: '',
            showErrorSaveEditOfferedService: false,

            // Garages
            showGarageEdit: false,
            editGarageGarageNumber: '',
            showErrorSaveEditGarage: false,

            // Add 

            // Offered services
            showOfferedServiceAdd: false,
            newOfferedServiceDescription: '',
            newOfferedServiceDuration: '',
            newOfferedServiceCost: '',
            showErrorSaveAddOfferedService: false,

            // Garages
            showGarageAdd: false,
            newGarageGarageNumber: '',
            showErrorSaveAddGarage: false,

            // Delete

            // Offered services
            deleteOfferedService: '',
            showErrorDeleteOfferedService: false,

            // Garages
            deleteGarage: '',
            showErrorDeleteGarage: false,

            response: [],

            // Icons
            icons: {
                deleteIcon: Delete,
                arrowLeftIcon: ArrowLeft,
            },
        };
    },

    created() {
        // const axios = inject('axios')
        // const offeredServices = new ServicesAndGaragesService(axios)

        // Initializing offered services from backend
        AXIOS.get('/offeredServices/get')
        .then(response => {
            this.offeredServices = response.data
        })
        .catch(e => {
            this.errorOfferedService = e
            // alert(e)
        })

        // Initializing garages from backend
        AXIOS.get('/garages/get').then(response => {
            this.garages = response.data
        })
        .catch(e => {
            this.errorGarage = e
            alert(e)
            // this.errors.push(e)
        })
    }, 

    methods: {
        handleOfferedServiceRowClick(row) {
            this.selectedOfferedServiceRow = row;
            this.editOfferedServiceDescription = row.description;
            this.editOfferedServiceDuration = row.duration;
            this.editOfferedServiceCost = row.cost;

            this.showErrorEditOfferedService = false;
            this.showErrorDeleteOfferedService = false;
        },
    
        handleGarageRowClick(row) {
            this.selectedGarageRow = row;
            this.editGarageGarageNumber = row.garageNumber;

            this.showErrorEditGarage = false;
            this.showErrorDeleteGarage = false;
        },

        goBack() {
            this.showOfferedServicesEdit = true;
            this.showGaragesEdit = true;
            this.showOfferedServiceEdit = false;
            this.showGarageEdit = false;
            this.showOfferedServiceAdd = false;
            this.showGarageAdd = false;

            this.selectedOfferedServiceRow = null;
            this.selectedGarageRow = null;

            this.editOfferedServiceDescription = '';
            this.editOfferedServiceDuration = '';
            this.editOfferedServiceCost = '';

            this.editGarageGarageNumber = '';

            this.newOfferedServiceDescription = '';
            this.newOfferedServiceDuration = '';
            this.newOfferedServiceCost = '';

            this.newGarageGarageNumber = '';
        },

        deleteOfferedServiceSelected() {
            if (this.selectedOfferedServiceRow) {

                this.selectedOfferedServiceRow = null;
                this.selectedGarageRow = null;
            }

            else {
                this.showErrorDeleteOfferedService = true;
            }
        },

        deleteGarageSelected() {
            if (this.selectedGarageRow) {
                this.selectedOfferedServiceRow = null;
                this.selectedGarageRow = null;
            }

            else {
                this.showErrorDeleteGarage = true;
            }
        },

        // Edit an offered service
        editOfferedService() {
            if (this.selectedOfferedServiceRow) {
                this.showOfferedServiceEdit = true;
                this.showOfferedServicesEdit = false;
                this.showGaragesEdit = false;

                this.showErrorEditGarage = false;
                this.showErrorDeleteGarage = false;
            }

            else {
                this.showErrorEditOfferedService = true;
            }
        },

        saveEditOfferedService() {
            if (this.editOfferedServiceDescription !== '' && this.editOfferedServiceDuration !== '' && this.editOfferedServiceCost !== '') {
                this.showOfferedServiceEdit = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;

                this.selectedOfferedServiceRow = null;
                this.editOfferedServiceDescription = '';
                this.editOfferedServiceDuration = '';
                this.editOfferedServiceCost = '';
            }
        },

        // Edit a garage
        editGarage() {
            if (this.selectedGarageRow) {
                this.showGarageEdit = true;
                this.showOfferedServicesEdit = false;
                this.showGaragesEdit = false;

                this.showErrorEditOfferedService = false;
                this.showErrorDeleteOfferedService = false;
            }

            else {
                this.showErrorEditGarage = true;
            }
        },

        saveEditGarage() {
            if (this.editGarageGarageNumber !== '') {
                this.showGarageEdit = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;

                this.selectedGarageRow = null;
                this.editGarageGarageNumber = '';
            }
        },

        // Add an offered service
        addOfferedService() {
            this.showOfferedServiceAdd = true;
            this.showOfferedServicesEdit = false;
            this.showGaragesEdit = false;

            this.showErrorEditOfferedService = false;
            this.showErrorEditGarage = false;
            this.showErrorDeleteOfferedService = false;
            this.showErrorDeleteGarage = false;    
        },

        saveAddOfferedService: function (description, duration, cost) {
            // Create a new offered service and add it to the list of offered services
            AXIOS.post('/offeredServices/create', {}, {
                params: {
                    description: description,
                    duration: duration,
                    cost: cost,
                }
            })
            .then(response => {
              // JSON responses are automatically parsed.
              this.offeredServices.push(response.data)
              this.errorOfferedService = ''
              this.newOfferedServiceDescription = '';
              this.newOfferedServiceDuration = '';
              this.newOfferedServiceCost = '';    

              this.showOfferedServiceAdd = false;
              this.showOfferedServicesEdit = true;
              this.showGaragesEdit = true;    
            })
            .catch(e => {
              var errorMsg = e.response.data.message
            //   alert(errorMsg)
              this.errorOfferedService = errorMsg
            })
        },

        // Add a garage
        addGarage() {
            this.showGarageAdd = true;
            this.showOfferedServicesEdit = false;
            this.showGaragesEdit = false;

            this.showErrorEditOfferedService = false;
            this.showErrorEditGarage = false;
            this.showErrorDeleteOfferedService = false;
            this.showErrorDeleteGarage = false;    
        },

        saveAddGarage: function (garageNum) {
            // alert("reached")

            // Create a new garage and add it to the list of garages
            AXIOS.post('/garages/create', {}, {
                params: {
                    garageNumber: garageNum
                }
            })
            .then(response => {
                // alert("reached")

            // JSON responses are automatically parsed.
              this.garages.push(response.data)
              this.errorGarage = ''
              this.newGarageGarageNumber = ''

              this.showGarageAdd = false;
              this.showOfferedServicesEdit = true;
              this.showGaragesEdit = true;
            })
            .catch(e => {
              var errorMsg = e.response.data.message
            //   alert(errorMsg)
              this.errorGarage = errorMsg
            })
        }, 
    }
}
