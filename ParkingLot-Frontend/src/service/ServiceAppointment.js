export const ServiceAppointment = {
  getServicesData() {
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

  getServices() {
    return Promise.resolve(this.getServicesData());
  },
};
