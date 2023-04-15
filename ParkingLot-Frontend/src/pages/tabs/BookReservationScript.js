import {ref} from 'vue'
import axios from 'axios'

  
export default {
    name: "BookReservation",
   
    data () {
        return {
          
          tabPosition: ref('top'),
          service_id: ref(''),
          reservations: [],
          selectedDateRange: [],
          
          errorReservation: '',

          appointments: [],
          newService: '',
          errorService: '',

          response: [],
          selectedSpot: "",
          selectedFloor: "",
          selectedService:"",
          selectedGarage:"",
          value: ref(''),
          value1: ref(''),
          value2: ref(''),
          value3: ref(new Date()),

          value4: ref(''),
          size: ref<'default' | 'large' | 'small'>('default'),
          floors: [
            {
                value: '-1',
                label: 'B1',
              },
            {
              value: '1',
              label: 'First',
            },
            {
              value: '2',
              label: 'Second',
            },
            {
              value: '3',
              label: 'Third',
            },
          ],
          spots: [
            {
                value: '10',
                label: '10',
              },
            {
              value: '12',
              label: '12',
            },
            {
              value: '14',
              label: '14',
            },
            {
              value: '16',
              label: '16',
            },
            {
                value: '18',
                label: '18',
            },
          ],
          garages: [
            {
                value: '1',
                label: 'Garage 1',
              },
            {
              value: '2',
              label: 'Garage 2',
            },
            {
              value: '3',
              label: 'Garage 3',
            },
            {
              value: '4',
              label: 'Garage 4',
            },
            
          ],
          
          services: [
            {
              value: '1',
              label: 'Oil Change',
            },
            {
              value: '2',
              label: 'Tire Rotation and Balancing',
            },
            {
              value: '3',
              label: 'Brake Inspection and Service',
            },
            {
              value: '4',
              label: 'Battery Inspection and Replacement',
            },
            {
              value: '5',
              label: 'Wheel Alignment',
            },
            {
              value: '6',
              label: 'Air Conditioning Service',
            },
          ],
          AXIOS: axios.create({
            baseURL: 'http://localhost:8080/',
            headers: {'Access-Control-Allow-Origin': 'http://localhost:5173'},
            withCredentials: true
        })
    }
},
      setup() {
        function MonthlyReservationDto (startDate, endDate, personId) {
         this.startDate = startDate
         this.endDate = endDate
         this.personId = personId
        }
        function ServiceAppointmentDto (id, phoneNumber, personName, licensePlate, carMake, carModel, 
          service, garage, timeSlot, startDate, appointmentStatus) {
            this.id = id;
         this.phoneNumber = phoneNumber
         this.personName = personName
         this.licensePlate = licensePlate
         this.carMake = carMake
         this.carModel = carModel
         this.service = service
         this.garage = garage
         this.timeSlot = timeSlot
         this.startDate = startDate
         this.appointmentStatus = appointmentStatus
        }
      },
      created: function () {
        
        // // Test data
        //  const s1 = new ServiceAppointmentDto('111','111-111-1111', 'Jane Doe', 'ABT-345', 'Toyota', 'Prius', 
        //   'B1', '10', '08/10/2022', '09/10/2022', 'Ready')
        //   const s2 = new ServiceAppointmentDto('112','111-111-1111', 'Jane Doe', 'ABT-345', 'Toyota', 'Prius', 
        //   'B1', '10', '08/10/2022', '09/10/2022', 'Ready')
        // // const r2 = new ReservationDto('1211','000-000-0000','Jane Doe','N/A', 'ABT-345', 'ABC-123', 'B1','10')
        // // const r3 = new ReservationDto('1311','100-000-0000','Mary Doe','N/A', 'ASR-565','ABC-123', '2','14')
        // // const r4 = new ReservationDto('1411','200-000-0000','Tom Doe','N/A', 'LSL-335','ABC-123', '3','16')
        // // const r5 = new ReservationDto('1511','300-000-0000','Paul Doe','N/A', 'MOV-348','ABC-123', 'B1','10')
        //  this.appointments = [s1, s2]
        // Initializing persons from backend
      this.AXIOS.get('http://localhost:8080/api/monthlyReservation/allReservations', {}, {
        withCredentials: true,
        headers: {
            "Access-Control-Allow-Origin": 'localhost:8080',
        },
        params: {
        }
    })
    .then(response => {
      console.log(response)
      console.log(personID)
      console.log(selectedDateRange)
      // JSON responses are automatically parsed.
      this.reservations = response.data
     
    })
    .catch(e => {
      this.errorReservation = e
    })
    this.AXIOS.get('http://localhost:8080//api/appointments/get/all', {}, {
        withCredentials: true,
        headers: {
            "Access-Control-Allow-Origin": 'localhost:8080',
        },
        params: {
        }
    })
    .then(response => {
      console.log(response)
      // JSON responses are automatically parsed.
      //this.persons.push({id: 1, name: 'Jim', phoneNumber: '555-1234'});
      this.appointments = response.data
     
    })
    .catch(e => {
      this.errorService = e
    })


      },
   
    methods: {
        
        createReservation: function (selectedDateRange, person_id) {
          const [startDate, endDate] = selectedDateRange;
        //   const monthlyReservationDto = {
        //  startDate: startDate.toISOString().substr(0, 10), // convert to ISO format
        //  endDate: endDate.toISOString().substr(0, 10), // convert to ISO format
        // personId: person_id
        //   };
        this.AXIOS.post('http://localhost:8080/api/monthlyReservation/create', {}, {
          withCredentials: true,
          headers: {
              "Access-Control-Allow-Origin": 'localhost:8080',
          },
          params: {
          startDate: startDate.toISOString().substr(0, 10), // convert to ISO format
          endDate: endDate.toISOString().substr(0, 10), // convert to ISO format
          personId: person_id
          }


      })
      .then(response => {
        //console.log(response)
        
        console.log(response)
        //console.log(monthlyReservationDto)
        // JSON responses are automatically parsed.
        this.reservations.push(response.data)
        this.errorReservation = ''
        //this.newPerson = ''
       
      })
      .catch(e => {
        console.log("Hello world")
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorReservation = errorMsg
      })

      }}}

          // createServiceAppointment: function (phoneNumber, personName, licensePlate, carMake, carModel, 
          //   service, garage, timeSlot, startDate) {
          //     var s = new ServiceAppointmentDto(phoneNumber, personName, licensePlate, carMake, carModel, 
          //       floor, spotNumber, startDate, endDate, 'Ready')
          //     this.offeredServices.push(s)
          //     // Reset the name field for new people
          //     this.newService = ''
          // },
          // updateAppointment: function(id, newAppointmentStatus) {
          //   for (let i = 0; i < this.appointments.length; i++) {
          //     if (this.appointments[i].id == id) {
          //      this.appointments[i].appointmentStatus = newAppointmentStatus
          //      break
          //     }
          //   }
          // }