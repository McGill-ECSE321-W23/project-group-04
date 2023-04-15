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
            showConfirmationEditOfferedService: false,

            // Garages
            showGarageEdit: false,
            editGarageSelected: '',
            editGarageGarageNumber: '',
            showErrorSaveEditGarage: false,
            showConfirmationEditGarage: false,

            // Add 

            // Offered services
            showOfferedServiceAdd: false,
            newOfferedServiceDescription: '',
            newOfferedServiceDuration: '',
            newOfferedServiceCost: '',
            showErrorSaveAddOfferedService: false,
            showConfirmationAddOfferedService: false,

            // Garages
            showGarageAdd: false,
            newGarageGarageNumber: '',
            showErrorSaveAddGarage: false,
            showConfirmationAddGarage: false,

            // Delete

            // Offered services
            deleteOfferedService: '',
            showErrorDeleteOfferedService: false,
            showConfirmationDeleteOfferedService: false,

            // Garages
            deleteGarage: '',
            showErrorDeleteGarage: false,
            showConfirmationDeleteGarage: false,

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

            // Confirmations
            this.showConfirmationDeleteOfferedService = false;
            this.showConfirmationDeleteGarage = false;
            this.showConfirmationAddOfferedService = false;
            this.showConfirmationAddGarage = false;
            this.showConfirmationEditOfferedService = false;
            this.showConfirmationEditGarage = false;
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

            // Confirmations
            this.showConfirmationDeleteOfferedService = false;
            this.showConfirmationDeleteGarage = false;
            this.showConfirmationAddOfferedService = false;
            this.showConfirmationAddGarage = false;
            this.showConfirmationEditOfferedService = false;
            this.showConfirmationEditGarage = false;
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

            // Confirmations
            this.showConfirmationDeleteOfferedService = false;
            this.showConfirmationDeleteGarage = false;
            this.showConfirmationAddOfferedService = false;
            this.showConfirmationAddGarage = false;
            this.showConfirmationEditOfferedService = false;
            this.showConfirmationEditGarage = false;
        },

        // Reset confirmation:
        closeConfirmation() {
            this.showConfirmation = false;
        },

        deleteOfferedServiceSelected: function (deleteos) {
            AXIOS.post('/offeredServices/delete/'.concat(deleteos), {}, {}).then(response => {
                // Save change
                this.offeredServices.pop(response.data);

                // Reset input boxes
                this.deleteOfferedService = '';
                this.editOfferedServiceSelected = '';

                // Rows
                this.selectedOfferedServiceRow = null;
                this.selectedGarageRow = null;

                // Confirmations
                this.showConfirmationDeleteOfferedService = true;
                this.showConfirmationDeleteGarage = false;
                this.showConfirmationAddOfferedService = false;
                this.showConfirmationAddGarage = false;
                this.showConfirmationEditOfferedService = false;
                this.showConfirmationEditGarage = false;
            })
            .catch(e => {
                this.showErrorDeleteOfferedService = true;
            })
        },

        deleteGarageSelected: function (deleteg) {
            AXIOS.post('/garages/delete/'.concat(deleteg), {}, {}).then(response => {
                // Save change
                this.garages.pop(response.data);

                // Reset input boxes
                this.deleteGarage = '';
                this.editGarage = '';

                // Rows
                this.selectedOfferedServiceRow = null;
                this.selectedGarageRow = null;

                // Confirmations
                this.showConfirmationDeleteOfferedService = false;
                this.showConfirmationDeleteGarage = true;
                this.showConfirmationAddOfferedService = false;
                this.showConfirmationAddGarage = false;
                this.showConfirmationEditOfferedService = false;
                this.showConfirmationEditGarage = false;
            })
            .catch(e => {
                this.showErrorDeleteGarage = true;
            })
        },

        // Edit an offered service
        editOfferedService() {
            if (this.selectedOfferedServiceRow) {
                // Show containers
                this.showOfferedServiceEdit = true;
                this.showOfferedServicesEdit = false;
                this.showGaragesEdit = false;

                // Errors
                this.showErrorEditGarage = false;
                this.showErrorDeleteGarage = false;

                // Confirmations
                this.showConfirmationDeleteOfferedService = false;
                this.showConfirmationDeleteGarage = false;
                this.showConfirmationAddOfferedService = false;
                this.showConfirmationAddGarage = false;
                this.showConfirmationEditOfferedService = false;
                this.showConfirmationEditGarage = false;
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
                // Save changes
                const newOfferedService = response.data;
                const index = this.offeredServices.findIndex(os => os.id === editos);
                this.offeredServices.splice(index, 1, newOfferedService);
                
                // Reset input boxes
                this.editOfferedServiceSelected = '';
                this.editOfferedServiceDescription = '';
                this.editOfferedServiceDuration = '';
                this.editOfferedServiceCost = '';
                this.deleteOfferedService = '';

                // Show containers
                this.showOfferedServiceEdit = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;

                // Row
                this.selectedOfferedServiceRow = null;

                // Error
                this.showErrorSaveEditOfferedService = false;

                // Confirmations
                this.showConfirmationDeleteOfferedService = false;
                this.showConfirmationDeleteGarage = false;
                this.showConfirmationAddOfferedService = false;
                this.showConfirmationAddGarage = false;
                this.showConfirmationEditOfferedService = true;
                this.showConfirmationEditGarage = false;
            })
            .catch(e => {
                // If the duration is empty
                if (this.editOfferedServiceDuration === '' || this.editOfferedServiceDuration === 'undefined' || this.editOfferedServiceDuration === null) {
                    this.errorOfferedService = "Duration cannot be empty.";
                }

                // If the duration is not a number
                else if (this.editOfferedServiceDuration % 1 !== 0) {
                    this.errorOfferedService = "Duration must be an integer.";
                }

                // If the cost is empty
                else if (this.editOfferedServiceCost === '' || this.editOfferedServiceCost === 'undefined' || this.editOfferedServiceCost === null) {
                    this.errorOfferedService = "Cost cannot be empty.";
                }

                // If the cost is not a number
                else if (Number.isNaN(parseInt(this.editOfferedServiceCost))) {
                    this.errorOfferedService = "Cost must be a number.";
                }

                // An error that is caught inside the controller
                else {
                    this.errorOfferedService = e.response.data;
                }

                this.showErrorSaveEditOfferedService = true;
            })
        },

        // Edit a garage
        editGarage() {
            if (this.selectedGarageRow) {
                // Show containers
                this.showGarageEdit = true;
                this.showOfferedServicesEdit = false;
                this.showGaragesEdit = false;

                // Errors
                this.showErrorEditOfferedService = false;
                this.showErrorDeleteOfferedService = false;

                // Confirmations
                this.showConfirmationDeleteOfferedService = false;
                this.showConfirmationDeleteGarage = false;
                this.showConfirmationAddOfferedService = false;
                this.showConfirmationAddGarage = false;
                this.showConfirmationEditOfferedService = false;
                this.showConfirmationEditGarage = false;
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
                // Save changes
                const newGarage = response.data;
                const index = this.garages.findIndex(g => g.id === editg);
                this.garages.splice(index, 1, newGarage);                
                
                // Reset input boxes
                this.editGarage = '';
                this.editGarageGarageNumber = '';
                this.deleteGarage = '';

                // Show containers
                this.showGarageEdit = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;

                // Row
                this.selectedGarageRow = null;

                // Error
                this.showErrorSaveEditGarage = false;

                // Confirmations
                this.showConfirmationDeleteOfferedService = false;
                this.showConfirmationDeleteGarage = false;
                this.showConfirmationAddOfferedService = false;
                this.showConfirmationAddGarage = false;
                this.showConfirmationEditOfferedService = false;
                this.showConfirmationEditGarage = true;
            })
            .catch(e => {
                // If the garage number is empty
                if (this.editGarageGarageNumber === '' || this.editGarageGarageNumber === 'undefined' || this.editGarageGarageNumber === null) {
                    this.errorGarage = "Garage number cannot be empty.";
                }

                // If the garage number is not an integer
                else if (this.editGarageGarageNumber % 1 !== 0) {
                    this.errorGarage = "Garage number must be an integer.";
                }

                // Error handled by the controller
                else {
                    this.errorGarage = e.response.data;
                }

                this.showErrorSaveEditGarage = true;
            })
        },

        // Add an offered service
        addOfferedService() {
            // Show containers
            this.showOfferedServiceAdd = true;
            this.showOfferedServicesEdit = false;
            this.showGaragesEdit = false;

            // Errors
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
                // Save changes
                this.offeredServices.push(response.data);

                // Reset input boxes
                this.errorOfferedService = '';
                this.newOfferedServiceDescription = '';
                this.newOfferedServiceDuration = '';
                this.newOfferedServiceCost = '';    

                // Show containers
                this.showOfferedServiceAdd = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;    

                // Error
                this.showErrorSaveAddOfferedService = false;

                // Confirmations
                this.showConfirmationDeleteOfferedService = false;
                this.showConfirmationDeleteGarage = false;
                this.showConfirmationAddOfferedService = true;
                this.showConfirmationAddGarage = false;
                this.showConfirmationEditOfferedService = false;
                this.showConfirmationEditGarage = false;
            })
            .catch(e => {
                // If the duration is empty
                if (this.newOfferedServiceDuration === '' || this.newOfferedServiceDuration === 'undefined' || this.newOfferedServiceDuration === null) {
                    this.errorOfferedService = "Duration cannot be empty.";
                }

                // If the duration is not an integer
                else if (this.newOfferedServiceDuration % 1 !== 0) {
                    this.errorOfferedService = "Duration must be an integer.";
                }

                // If the cost is empty
                else if (this.newOfferedServiceCost === '' || this.newOfferedServiceCost === 'undefined' || this.newOfferedServiceCost === null) {
                    this.errorOfferedService = "Cost cannot be empty.";
                }

                // If the cost is not a number
                else if (Number.isNaN(parseInt(this.newOfferedServiceCost))) {
                    this.errorOfferedService = "Cost must be a number.";
                }

                // An error that is caught inside the controller
                else {
                    this.errorOfferedService = e.response.data;
                }

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
                // Save changes
                this.garages.push(response.data);

                // Reset input boxes
                this.errorGarage = '';
                this.newGarageGarageNumber = '';

                // Show containers
                this.showGarageAdd = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;

                // Error
                this.showErrorSaveAddGarage = false;

                // Confirmations
                this.showConfirmationDeleteOfferedService = false;
                this.showConfirmationDeleteGarage = false;
                this.showConfirmationAddOfferedService = false;
                this.showConfirmationAddGarage = true;
                this.showConfirmationEditOfferedService = false;
                this.showConfirmationEditGarage = false;
            })
            .catch(e => {
                // If the garage number is empty
                if (this.newGarageGarageNumber === '' || this.newGarageGarageNumber === 'undefined' || this.newGarageGarageNumber === null) {
                    this.errorGarage = "Garage number cannot be empty.";
                }

                // If the garage number is not an integer
                else if (this.newGarageGarageNumber % 1 !== 0) {
                    this.errorGarage = "Garage number must be an integer.";
                }

                // Error handled by the controller
                else {
                    this.errorGarage = e.response.data;
                }
                
                this.showErrorSaveAddGarage = true;
            })
        }, 
    }
}
