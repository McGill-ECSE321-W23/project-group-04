import { Delete, ArrowLeft } from '@element-plus/icons-vue'

function GarageDto (garageNumber) {
    this.garageNumber = garageNumber;
}

function OfferedServiceDto (description, duration, cost) {
    this.description = description;
    this.duration = duration;
    this.cost = cost;
}

export default {
    name: 'servicesAndGaragesManagement',

    data() {
        return {

            // Main page

            // Offered services
            showOfferedServicesEdit: true,
            offeredServices: [],
            selectedOfferedServiceRow: '',
            showErrorOfferedService: false,
            errorOfferedService: '',

            // Garages
            showGaragesEdit: true,
            garages: [],
            selectedGarageRow: '',
            showErrorGarage: false,
            errorGarage: '',
            
            // Edit

            // Offered services
            showOfferedServiceEdit: false,
            editOfferedServiceDescription: '',
            editOfferedServiceDuration: '',
            editOfferedServiceCost: '',

            // Garages
            showGarageEdit: false,
            editGarageGarageNumber: '',

            // Add 

            // Offered services
            showOfferedServiceAdd: false,
            newOfferedServiceDescription: '',
            newOfferedServiceDuration: '',
            newOfferedServiceCost: '',

            // Garages
            showGarageAdd: false,
            newGarageGarageNumber: '',

            // Delete

            // Offered services
            deleteOfferedService: '',

            // Garages
            deleteGarage: '',

            response: [],

            // Icons
            icons: {
                deleteIcon: Delete,
                arrowLeftIcon: ArrowLeft,
            },
        };
    },

    created() {
        // Test data
    
        // Offered services
        const os1 = new OfferedServiceDto('Change Tires', 60, 99.99)
        const os2 = new OfferedServiceDto('Clean Car', 120, 50.00)
        const os3 = new OfferedServiceDto('Change Wipers', 15, 10.00)
        this.offeredServices = [os1, os2, os3]
    
        // Garages
        const g1 = new GarageDto(1);
        const g2 = new GarageDto(2);
        this.garages = [g1, g2]
    }, 

    methods: {
        handleOfferedServiceRowClick(row) {
            this.selectedOfferedServiceRow = row;
            this.editOfferedServiceDescription = row.description;
            this.editOfferedServiceDuration = row.duration;
            this.editOfferedServiceCost = row.cost;
        },
    
        handleGarageRowClick(row) {
            this.selectedGarageRow = row;
            this.editGarageGarageNumber = row.garageNumber;
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

            this.editOfferedServiceDescription = ''
            this.editOfferedServiceDuration = ''
            this.editOfferedServiceCost = ''  

            this.editGarageGarageNumber = '' 

            this.newOfferedServiceDescription = ''
            this.newOfferedServiceDuration = ''
            this.newOfferedServiceCost = ''  

            this.newGarageGarageNumber = ''
        },

        deleteOfferedService() {

            this.selectedOfferedServiceRow = null;
            this.selectedGarageRow = null;
        },

        deleteGarage() {

            this.selectedOfferedServiceRow = null;
            this.selectedGarageRow = null;
        },

        // Edit an offered service
        editOfferedService() {
            if (this.selectedOfferedServiceRow) {
                this.showOfferedServiceEdit = true;
                this.showOfferedServicesEdit = false;
                this.showGaragesEdit = false;
            }
        },

        saveEditOfferedService() {
            if (this.selectedOfferedServiceRow) {
                this.showOfferedServiceEdit = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;

                this.selectedOfferedServiceRow = null;
                this.editOfferedServiceDescription = ''
                this.editOfferedServiceDuration = ''
                this.editOfferedServiceCost = ''   
            }
        },

        // Edit a garage
        editGarage() {
            if (this.selectedGarageRow) {
                this.showGarageEdit = true;
                this.showOfferedServicesEdit = false;
                this.showGaragesEdit = false;
            }
        },

        saveEditGarage() {
            if (this.selectedOfferedServiceRow) {
                this.showGarageEdit = false;
                this.showOfferedServicesEdit = true;
                this.showGaragesEdit = true;

                this.selectedGarageRow = null;
                this.editGarageGarageNumber = '' 
            }
        },

        // Add an offered service
        addOfferedService() {
            this.showOfferedServiceAdd = true;
            this.showOfferedServicesEdit = false;
            this.showGaragesEdit = false;
        },

        saveAddOfferedService: function (description, duration, cost) {
            // Create a new offered service and add it to the list of offered services
            var os = new OfferedServiceDto(description, duration, cost)
            this.offeredServices.push(os)

            this.showOfferedServiceAdd = false;
            this.showOfferedServicesEdit = true;
            this.showGaragesEdit = true;

            this.newOfferedServiceDescription = ''
            this.newOfferedServiceDuration = ''
            this.newOfferedServiceCost = ''        
        },

        // Add a garage
        addGarage() {
            this.showGarageAdd = true;
            this.showOfferedServicesEdit = false;
            this.showGaragesEdit = false;
        },

        saveAddGarage: function (garageNumber) {
            // Create a new garage and add it to the list of garages
            var g = new GarageDto(garageNumber)
            this.garages.push(g)

            this.showGarageAdd = false;
            this.showOfferedServicesEdit = true;
            this.showGaragesEdit = true;

            this.newGarageGarageNumber = ''
        }, 
    }
}
