import { ArrowLeft } from '@element-plus/icons-vue'
import { ref, inject } from 'vue'
import ServiceAppointmentService from "@/service/ServiceAppointmentService";

function GarageDto (garageNumber) {
  this.garageNumber = garageNumber;
}

function OfferedServiceDto (description, duration, cost) {
  this.description = description;
  this.duration = duration;
  this.cost = cost;
}

export default {
  name: 'serviceAppointmentManagement',

  data() {
    return {

      // Main page
          
      // Offered services
      showOfferedServices: true,
      offeredServices: [],
      selectedOfferedServiceRow: '',
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
      showErrorEditGarage: false,
      errorGarage: '',
      showConfirmation: false,

      response: [],

      // Icons
      icons: {
          arrowLeftIcon: ArrowLeft,
      },
    }
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

  // mounted() {
  //   const axios = inject('axios')
  //   const userService = new UserService(axios)

  //   axios.get('api/account/get/' + userService.getCookie("accountId"))
  //       .then((data) => {
  //         data = data.data
  //         this.form.name = data.person.name
  //         this.form.email = data.email
  //         this.form.password = data.password
  //         this.form.phone = data.person.phoneNumber
  //         console.log(this.form)
  //       })
  //       .catch(err => {
  //         console.log(err)
  //       })
  // },

  methods: {
    handleOfferedServiceRowClick(row) {
      this.selectedOfferedServiceRow = row;
      this.description = row.description;
      this.duration = row.duration;
      this.cost = row.cost;

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
      }

      else {
        this.showErrorOfferedService = true;
      }
    },

    saveAddAppointment() {
      if (this.date !== '' && this.time !== '' && this.garage !== '') {
        
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
      }
    },
  }
}
