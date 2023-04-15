import { ArrowLeft } from '@element-plus/icons-vue'
import { ref } from 'vue'
import axios from 'axios'

var AXIOS = axios.create({
  baseURL: 'http://localhost:8080/api',   // backend url
  withCredentials: true,
  headers: {
      "Access-Control-Allow-Origin": 'localhost:5173' // frontend url
  }
})

export default {
  name: 'serviceAppointmentManagement',

  data() {
    return {

      // Main page
      owner: '',
          
      // Offered services
      showOfferedServices: true,
      offeredServices: [],
      selectedOfferedServiceRow: '',
      offeredServiceSelected: '',
      showErrorOfferedService: false,

      // Book
      showBookAppointment: false,
      appointments: [],
      description: '',
      duration: '',
      cost: '',
      date: ref(''),
      time: ref(''),
      garage: ref(''),
      garages: [],
      car: ref(''),
      cars: [],
      showErrorBookAppointment: false,
      errorAppointment: '',
      showConfirmation: false,

      response: [],

      // Icons
      icons: {
          arrowLeftIcon: ArrowLeft,
      },
    }
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

      // Initializing cars from backend
      AXIOS.get('/cars/get/ByOwner/'.concat(this.owner)).then(response => {
        this.appointments = response.data
      })
      .catch(e => {
          this.errorAppointment = e
      })
  },

  watch: {

  },

  methods: {
    handleOfferedServiceRowClick(row) {
      this.selectedOfferedServiceRow = row;

      // Fill offered service info
      this.offeredServiceSelected = row.offeredServiceID;
      this.description = row.offeredServiceDescription;
      this.duration = row.offeredServiceDuration;
      this.cost = row.offeredServiceCost;

      // Show container
      this.showErrorOfferedService = false;

      // Confirmation
      this.showConfirmation = false;
    },

    // Go back to the selection of offered service section when clicking the back button
    goBack() {
      // Show containers
      this.showOfferedServices = true;
      this.showBookAppointment = false;

      // Reset row
      this.selectedOfferedServiceRow = null;

      // Reset info
      this.description = '';
      this.duration = '';
      this.cost = '';
      this.date = ref('');
      this.time = ref('');
      this.garage = ref('');

      // Error
      this.showErrorBookAppointment = false;
    },
  
    // Reset confirmation:
    closeConfirmation() {
      this.showConfirmation = false;
    },
  
    // Start booking an appointment
    selectOfferedService() {
      if (this.selectedOfferedServiceRow) {
        // Show containers
        this.showOfferedServices = false;
        this.showBookAppointment = true;

        // Errors
        this.showErrorOfferedService = false;

        // Confirmation
        this.showConfirmation = false;
      }

      else {
        this.showErrorOfferedService = true;
      }
    },

    saveAddAppointment: function (gID, sID, cID, dateTime) {
      AXIOS.post('/appointment/create/', {}, {
        params: {
            garageID: gID,
            serviceID: sID,
            carID: cID, 
            startTime: dateTime
        }
      }).then(response => {      
        // Save changes
        this.appointments.push(response.data);
        
        // Show containers
        this.showOfferedServices = true;
        this.showBookAppointment = false;

        // Reset offered service
        this.selectedRow = null;
        this.description = '';
        this.duration = '';
        this.cost = '';
        
        // Reset selection boxes
        this.date = '';
        this.time = '';
        this.garage = '';

        // Confirmation
        this.showConfirmation = true;

        // Error
        this.showErrorBookAppointment = false;
      })
      .catch(e => {
        // If the date is not selected
        if (this.date === '' || this.date === 'undefined' || this.date === null) {
          this.errorAppointment = "Please select a date.";
        }

        // If the time is not selected
        else if (this.time === '' || this.time === 'undefined' || this.time === null) {
          this.errorAppointment = "Please select a time.";
        }

        // If the garage is not selected
        else if (this.garage === '' || this.garage === 'undefined' || this.garage === null) {
          this.errorAppointment = "Please select a garage.";
        }

        // An error that is caught inside the controller 
        else {
          this.errorAppointment = e.response.data;
        }

        this.showErrorBookAppointment = true;
      })
    },
  }
}
