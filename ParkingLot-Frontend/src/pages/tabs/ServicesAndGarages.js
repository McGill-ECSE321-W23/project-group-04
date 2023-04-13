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
            // Offered services
            offeredServices: [],
            newOfferedService: '',
            editOfferedService: '',
            deleteOfferedService: '',
            errorOfferedService: '',

            // Garage
            garages: [],
            newGarage: '',
            editGarage: '',
            deleteGarage: '',
            errorGarage: '',

            response: []
        }
    },

    created: function() {
        // Test data

        const os1 = new OfferedServiceDto('Change Tires', 60, 99.99)
        const os2 = new OfferedServiceDto('Clean Car', 120, 50.00)
        const os3 = new OfferedServiceDto('Change Wipers', 15, 10.00)
        const g1 = new GarageDto(1);
        const g2 = new GarageDto(2);

        // Sample initial content
        this.offeredServices = [os1, os2, os3]
        this.garages = [g1, g2]
    }, 

    methods: {
        createOfferedService: function (description, duration, cost) {
            // Create a new offered service and add it to the list of offered services
            var os = new OfferedServiceDto(description, duration, cost)
            this.offeredServices.push(os)
            this.newOfferedService = ''
        },

        createGarage: function (garageNumber) {
            // Create a new garage and add it to the list of garages
            var g = new GarageDto(garageNumber)
            this.garages.push(g)
            this.newGarage = ''
        }, 

        // Add the edit and delete
    }
}

export const offeredServicesAvailable = [
    {
        id: 1,
        label: 'Change Tires',
        description: 'Change Tires',
        duration: 60,
        cost: 99.99,
    },
    {
        id: 2,
        label: 'Clean Car',
        description: 'Clean Car',
        duration: 120,
        cost: 50.00,
    },
    {
        id: 3,
        label: 'Change Wipers',
        description: 'Change Wipers',
        duration: 15,
        cost: 10.00,
    },
]

export const garages = [
    {
        id: 1,
        label: '1',
        garageNumber: '1',
    },
    {
        id: 2,
        label: '2',
        garageNumber: '2',
    },
    {
        id: 3,
        label: '3',
        garageNumber: '3',
    },
    {
        id: 4,
        label: '4',
        garageNumber: '4',
    },
    {
        id: 5,
        label: '5',
        garageNumber: '',
    },
]