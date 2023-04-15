import { Delete, ArrowLeft } from '@element-plus/icons-vue'
import axios from 'axios'

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
            editOfferedServiceSelected: '',
            editOfferedServiceDescription: '',
            editOfferedServiceDuration: '',
            editOfferedServiceCost: '',
            showErrorSaveEditOfferedService: false,

            // Garages
            showGarageEdit: false,
            editGarageSelected: '',
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
        // Initializing offered services from backend
        AXIOS.get('/offeredServices/get').then(response => {
            this.offeredServices = response.data
        })
        .catch(e => {
            this.errorOfferedService = e
        })

        // Initializing garages from backend
        AXIOS.get('/garages/get').then(response => {
            this.garages = response.data
        })
        .catch(e => {
            this.errorGarage = e
        })
    }, 

    methods: {
        handleOfferedServiceRowClick(row) {
            this.selectedOfferedServiceRow = row;

            // Edit
            this.editOfferedServiceSelected = row.offeredServiceID;
            this.editOfferedServiceDescription = row.offeredServiceDescription;
            this.editOfferedServiceDuration = row.offeredServiceDuration;
            this.editOfferedServiceCost = row.offeredServiceCost;

            // Delete
            this.deleteOfferedService = row.offeredServiceID;

            // Errors
            this.showErrorEditOfferedService = false;
            this.showErrorDeleteOfferedService = false;
        },
    
        handleGarageRowClick(row) {
            this.selectedGarageRow = row;

            // Edit
            this.editGarageSelected = row.garageID;
            this.editGarageGarageNumber = row.garageNumber;

            // Delete
            this.deleteGarage = row.garageID;

            // Errors
            this.showErrorEditGarage = false;
            this.showErrorDeleteGarage = false;
        },

        goBack() {
            // Sections
            this.showOfferedServicesEdit = true;
            this.showGaragesEdit = true;
            this.showOfferedServiceEdit = false;
            this.showGarageEdit = false;
            this.showOfferedServiceAdd = false;
            this.showGarageAdd = false;

            // Rows
            this.selectedOfferedServiceRow = null;
            this.selectedGarageRow = null;

            // Edit offered service
            this.editOfferedServiceSelected = '';
            this.editOfferedServiceDescription = '';
            this.editOfferedServiceDuration = '';
            this.editOfferedServiceCost = '';

            // Edit garage
            this.editGarageSelected = '';
            this.editGarageGarageNumber = '';

            // Add offered service
            this.newOfferedServiceDescription = '';
            this.newOfferedServiceDuration = '';
            this.newOfferedServiceCost = '';

            // Add garage
            this.newGarageGarageNumber = '';

            // Delete offered service
            this.deleteOfferedService = '';

            // Delete garage
            this.deleteGarage = '';

            // Errors
            this.showErrorEditOfferedService = false;
            this.showErrorEditGarage = false;
            this.showErrorSaveEditOfferedService = false;
            this.showErrorSaveEditGarage = false;
            this.showErrorSaveAddOfferedService = false;
            this.showErrorSaveAddGarage = false;
            this.showErrorDeleteOfferedService = false;
            this.showErrorDeleteGarage = false;
        },

        deleteOfferedServiceSelected: function (deleteos) {
            AXIOS.post('/offeredServices/delete/'.concat(deleteos), {}, {}).then(response => {
                this.offeredServices.pop(response.data);
                this.deleteOfferedService = '';
                this.editOfferedServiceSelected = '';

                this.selectedOfferedServiceRow = null;
                this.selectedGarageRow = null;
            })
            .catch(e => {
                this.showErrorDeleteOfferedService = true;
            })
        },

        deleteGarageSelected: function (deleteg) {
            AXIOS.post('/garages/delete/'.concat(deleteg), {}, {}).then(response => {
                this.garages.pop(response.data);
                this.deleteGarage = '';
                this.editGarage = '';

                this.selectedOfferedServiceRow = null;
                this.selectedGarageRow = null;
            })
            .catch(e => {
                this.showErrorDeleteGarage = true;
            })
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

        saveEditOfferedService: function (editos, desc, dur, co) {
            AXIOS.post('/offeredServices/modify/'.concat(editos), {}, {
                params: {
                    description: desc,
                    duration: dur,
                    cost: co,
                }
            }).then(response => {
                const newOfferedService = response.data;
                const index = this.offeredServices.findIndex(os => os.id === editos);
                this.offeredServices.splice(index, 1, newOfferedService);
                this.editOfferedServiceSelected = '';
                this.editOfferedServiceDescription = '';
                this.editOfferedServiceDuration = '';
                this.editOfferedServiceCost = '';
                this.deleteOfferedService = '';

                this.showOfferedServiceEdit = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;

                this.selectedOfferedServiceRow = null;

                this.showErrorSaveEditOfferedService = false;
            })
            .catch(e => {
                alert(e)
                this.errorOfferedService = e.response.data.message;
                this.showErrorSaveEditOfferedService = true;
            })
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

        saveEditGarage: function (editg, garageNum) {
            AXIOS.post('/garages/modify/'.concat(editg), {}, {
                params: {
                    garageNumber: garageNum,
                }
            }).then(response => {
                const newGarage = response.data;
                const index = this.garages.findIndex(g => g.id === editg);
                this.garages.splice(index, 1, newGarage);                
                this.editGarage = '';
                this.editGarageGarageNumber = '';
                this.deleteGarage = '';

                this.showGarageEdit = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;

                this.selectedGarageRow = null;

                this.showErrorSaveEditGarage = false;
            })
            .catch(e => {
                this.errorGarage = e.message;
                this.showErrorSaveEditGarage = true;
            })
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

        saveAddOfferedService: function (desc, dur, co) {
            // Create a new offered service and add it to the list of offered services
            AXIOS.post('/offeredServices/create', {}, {
                params: {
                    description: desc,
                    duration: dur,
                    cost: co,
                }
            })
            .then(response => {
              this.offeredServices.push(response.data)
              this.errorOfferedService = ''
              this.newOfferedServiceDescription = '';
              this.newOfferedServiceDuration = '';
              this.newOfferedServiceCost = '';    

              this.showOfferedServiceAdd = false;
              this.showOfferedServicesEdit = true;
              this.showGaragesEdit = true;    

              this.showErrorSaveAddOfferedService = false;
            })
            .catch(e => {
              this.errorOfferedService = e.response.data.message
              this.showErrorSaveAddOfferedService = true;
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
            // Create a new garage and add it to the list of garages
            AXIOS.post('/garages/create', {}, {
                params: {
                    garageNumber: garageNum
                }
            })
            .then(response => {
              this.garages.push(response.data)
              this.errorGarage = ''
              this.newGarageGarageNumber = ''

              this.showGarageAdd = false;
              this.showOfferedServicesEdit = true;
              this.showGaragesEdit = true;

              this.showErrorSaveAddGarage = false;
            })
            .catch(e => {
              this.errorGarage = e.response.data.message
              this.showErrorSaveAddGarage = true;
            })
        }, 
    }
}
