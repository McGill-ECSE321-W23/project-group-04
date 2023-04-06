// import axios from "axios";

export default {
  data() {
    return {
      offeredServicesAvailable: [
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
      ],
      description: "",

      availableServiceAppointments: [
        {
          id: 1,
          date: '12-04-2023',
          time: '11:30 am',
          garage: '5',
        },
        {
          id: 2,
          date: '12-04-2023',
          time: '2:00 pm',
          garage: '5',
        },
        {
          id: 3,
          date: '12-04-2023',
          time: '5:00 pm',
          garage: '5',
        },
      ],
      selectedRow: null,
      showMessage: false,
      showAppointments: false,
      selectedService: null,
    };
  },

  computed: {
    selectedAppointment() {
      return this.$refs.appointmentTable.selection[0];
    },
    offeredServicesAvailable() {
      return ServiceAppointment.offeredServices().offeredServicesAvailable;
    }
  },

  methods: {
    handleRowClick(row) {
      this.selectedRow = row;
    },
    saveSelectedAppointment() {
      if (this.selectedRow) {
        this.showMessage = true;
      }
    },
    resetTable() {
      this.selectedRow = null;
      this.showAppointments = false;
      this.showMessage = false;
    },
    showTable() {
      this.showAppointments = true;
      this.selectedService = this.offeredServicesAvailable.find((service) => {
        return service.description === this.description;
      });

      this.selectedService = selectedService;
    }
  }
}
