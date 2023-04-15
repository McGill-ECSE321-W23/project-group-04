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
        this.garages = response.data
      })
      .catch(e => {
          this.errorGarage = e
      })
  },

  watch: {

  },

  methods: {
    handleOfferedServiceRowClick(row) {
      this.selectedOfferedServiceRow = row;
      this.offeredServiceSelected = row.offeredServiceID;
      this.description = row.offeredServiceDescription;
      this.duration = row.offeredServiceDuration;
      this.cost = row.offeredServiceCost;

      this.showErrorOfferedService = false;
    },

    // Go back to the selection of offered service section when clicking the back button
    goBack() {
      this.showOfferedServices = true;
      this.showBookAppointment = false;

      this.selectedOfferedServiceRow = null;
      this.description = '';
      this.duration = '';
      this.cost = '';

      this.date = ref('');
      this.time = ref('');
      this.garage = ref('');
    },
  
    // Reset confirmation:
    closeConfirmation() {
      this.showConfirmation = false;
    },
  
    // Start booking an appointment
    selectOfferedService() {
      if (this.selectedOfferedServiceRow) {
        this.showOfferedServices = false;
        this.showBookAppointment = true;

        this.showErrorOfferedService = false;
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
        this.showOfferedServices = true;
        this.showBookAppointment = false;

        this.date = '';
        this.time = '';
        this.garage = '';
        this.selectedRow = null;
        this.description = '';
        this.duration = '';
        this.cost = '';
        this.showConfirmation = true;
      })
      .catch(e => {
      })
    },
  }
}
