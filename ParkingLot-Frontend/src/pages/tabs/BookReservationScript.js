import {ref} from 'vue'
import axios from 'axios'

  
export default {
    name: "BookReservation",
   
    data () {
        return {
          oldSpot:[],
          newRes:[],
          tabPosition: ref('top'),
          service_id: ref(''),
          reservations: [],
          selectedDateRange: [],
          parkingSpots: [],
          errorReservation: '',
          errorCars: '',
          errorAppointment: '',
          errorGarage: '',
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
          
          garages: [],
          services: [],
          aptTime: '',
          aptDate: '',
          cars: [],

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
    this.AXIOS.get('http://localhost:8080/api/appointments/get/all', {}, {
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
      this.errorAppointments = e
    })
    // Initializing persons from backend
    this.AXIOS.get('http://localhost:8080/api/parkingSpot/getAll', {}, {
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
    this.parkingSpots = response.data
   
  })
  .catch(e => {
    this.errorParkingSpot = e
  })
  this.AXIOS.get('http://localhost:8080/api/garages/get', {}, {
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
      this.garages = response.data
     
    })
    .catch(e => {
      this.errorGarage = e
    }), 
    this.AXIOS.get('http://localhost:8080/api/offeredServices/get', {}, {
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
      this.services = response.data
     
    })
    .catch(e => {
      this.errorService = e
    })
    this.AXIOS.get('http://localhost:8080/api/cars/getAll', {}, {
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
    this.cars = response.data
   
  })
  .catch(e => {
    this.errorCar = e
  })


      },
   
    methods: {
        createReservation: function (selectedDateRange, person_id, selectedSpot) {
        const [startDate, endDate] = selectedDateRange;
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
        console.log(response)
        // JSON responses are automatically parsed.
        this.reservations.push(response.data)
        newRes = response.data
        this.errorReservation = ''
        //this.newPerson = ''
        this.AXIOS.put('http://localhost:8080/api/parkingSpot/attachReservation', {}, {
          withCredentials: true,
          headers: {
              "Access-Control-Allow-Origin": 'localhost:8080',
          },
          params: {
          parkingSpotID: selectedSpot.parkingSpotID,
          reservationId: newRes.reservationId
          }
      })
      .catch(e => {
        console.log("Hello world")
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorReservation = errorMsg
      })
      
      .then(response => {
        //console.log(response)
        console.log(response)
      })
       
      })
     
      .catch(e => {
        console.log("Hello world")
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorReservation = errorMsg
      })
    },

          createServiceAppointment: function (license_plate, selectedGarage, selectedService, aptTime, aptDate) {
            
            for (let i = 0; i < this.cars.length; i++) {
                   if (this.cars[i].licensePlate == license_plate) {
                    appointmentCar = cars[i]
                    break}}
             const carDTo = {
              carID: appointmentCar.carID,
              licensePlate: appointmentCar.licensePlate,
              make: appointmentCar.make,
              model:appointmentCar.model,
              owner: appointmentCar.owner
            }
           

              this.AXIOS.post('http://localhost:8080/api/appointments/create', selectedGarage, selectedService, carDto, {
                withCredentials: true,
                headers: {
                    "Access-Control-Allow-Origin": 'localhost:8080',
                },
                params: {
                }
            })
            .then(response => {
              console.log('Hello world')
              console.log(response)
              // JSON responses are automatically parsed.
               this.appointments.push(response)
               this.errorAppointment = ''
             
            })
            .catch(e => {
                  var errorMsg = e.response.data.message
                  console.log(errorMsg)
                  this.errorAppointment = errorMsg
            })
            }}}