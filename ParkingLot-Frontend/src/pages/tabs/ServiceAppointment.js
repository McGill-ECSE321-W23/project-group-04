import axios from "axios";

export const ServiceAppointment = {
  offeredServices() {
    return [
      {
        id: 1,
        description: 'Change Tires',
        duration: 60,
        cost: 99.99,
      },
      {
        id: 2,
        description: 'Clean Car',
        duration: 120,
        cost: 50.00,
      },
      {
        id: 3,
        description: 'Change Wipers',
        duration: 15,
        cost: 10.00,
      },
    ];
  },

  availableServiceAppointments() {
    return [
      {
        id: 1,
        date: '12-04-2023',
        time: '1:30 pm',
        garage: '5',
      },
      {
        id: 2,
        date: '06-04-2023',
        time: '2:00 pm',
        garage: '5',
      },
      {
        id: 3,
        date: '12-04-2023',
        time: '11:00 am',
        garage: '5',
      },
    ];
  },

  getOfferedServices() {
    return Promise.resolve(this.offeredServices());
  },

  getOfferedServicesInfo(offeredServiceID) {
    return axios.get(`/api/offeredServices/${offeredServiceID}`).then((res) => res.data);
  },

  getAvailableServiceAppointments() {
    return Promise.resolve(this.availableServiceAppointments());
  },
};
