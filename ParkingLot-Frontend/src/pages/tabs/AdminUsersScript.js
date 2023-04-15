import {ref} from 'vue'
import axios from 'axios'

export default {
    name: 'AdminUsers',
    data () {
        return {
            person_name: ref(''),
            person_number: ref(''),
            person_email: ref(''),
            person_id: ref(''),
            new_name: ref(''),
            new_phone: ref(''),
            new_email: ref(''),
            found: false,
            found_user: ref({}),
            persons: [
           
            ],
            parkingSpots: [
            ],
            errorParkingSpot:'',
            tabPosition: ref('top'),
            reservations: [],
            newReservation: '',
            errorReservation: '',
            response: [],
            selectedSpot: [],
            selectedFloor: "",
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
            AXIOS: axios.create({
                baseURL: 'http://localhost:8080',
                headers: {'Access-Control-Allow-Origin': 'http://localhost:5173'},
                withCredentials: true
            })
        }
    },
    setup () {
        function MonthlyReservationDto (startDate, endDate, personID) {
            this.startDate = startDate
            this.endDate = endDate
            this.personID = personID
        }
        function PersonDto (personID, phoneNumber, personName) {
            this.personID = personID
            this.phoneNumber = phoneNumber
            this.personName = personName
        }
        function ParkingSpotDto (psID, floor, number) {
            this.psID = psID
            this.floor = floor
            this.number = number
        }
    },
    created() {
     
      // Initializing persons from backend
      this.AXIOS.get('http://localhost:8080/api/person/get', {}, {
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
          this.persons = response.data
         
        })
        .catch(e => {
          this.errorPerson = e
        })
      
   

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
      // JSON responses are automatically parsed.
      //this.persons.push({id: 1, name: 'Jim', phoneNumber: '555-1234'});
      this.reservations = response.data
     
    })
    .catch(e => {
      this.errorReservation = e
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
    },
    methods: {
      createPerson: function (personName, personNumber) {
        this.AXIOS.post('http://localhost:8080/api/person/create', {}, {
          withCredentials: true,
          headers: {
              "Access-Control-Allow-Origin": 'localhost:8080',
          },
          params: {
            name: personName,
             phoneNumber: personNumber
          }
      })
      .then(response => {
        console.log(response)
        // JSON responses are automatically parsed.
        this.persons.push(response.data)
        this.errorPerson = ''
        this.newPerson = ''
       
      })
      .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorPerson = errorMsg
      })
      },

      findPerson: function (personID) {
        this.AXIOS.get('http://localhost:8080/api/person/get/'.concat(personID), {}, {
          withCredentials: true,
          headers: {
              "Access-Control-Allow-Origin": 'localhost:8080',
          },
          params: {
            personID: personID,
          }
      })
      .then(response => {
        console.log(response)
        // JSON responses are automatically parsed.
        this.found_user = response.data
        this.found=true
        this.errorPerson = ''
        this.newPerson = ''
       
      })
      .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorPerson = errorMsg
      })
      },
      updatePerson: function (personID, newName, newPhone) {
        this.AXIOS.put('http://localhost:8080/api/person/update/'.concat(personID), {}, {
          withCredentials: true,
          headers: {
              "Access-Control-Allow-Origin": 'localhost:8080',
          },
          params: {
            personID: personID,
            name: newName,
            phoneNumber: newPhone
          }
      })
      .then(response => {
        console.log(response)
        // JSON responses are automatically parsed.
        for (let i = 0; i < this.persons.length; i++) {
          if (this.persons[i].id == id) {
            if (!(newName == "")) {
              this.persons[i].name = newName
             }
            if (!(newPhone == "")) {
              this.persons[i].phoneNumber = newPhone
            }
            
            break
                      }
                    }
          this.new_name=""
          this.new_email=""
          this.new_phone=""
       
      })
      .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorPerson = errorMsg
      })
      },
     
  updateReservation: function (resID, selectedSpot) {
      const selectedSpotDto ={
      parkingSpotID : selectedSpot.parkingSpotID,
      floor: selectedSpot.floor,
      number: selectedSpot.number,
      monthlyReservationDto: selectedSpot.monthlyReservationDto // or set this to a valid MonthlyReservationDto object if needed
    };
        this.AXIOS.put('http://localhost:8080/api/monthlyReservation/updateLocation', {parkingSpotDto: selectedSpotDto}, {
          withCredentials: true,
          headers: {
              "Access-Control-Allow-Origin": 'localhost:8080',
          },
          params: {
            reservationIdToUpdate: resID,
          }
      })
      .then(response => {
        console.log('Hello world')
        console.log(response)
        // JSON responses are automatically parsed.
         for (let i = 0; i < this.parkingSpots.length; i++) {
                   let temp = this.parkingSpots[i].monthlyReservationDto
                   if (temp.monthlyReservationID == resID) {
                    this.parkingSpots[j].monthlyReservationDto = null
                    break
                 }
                }
                 for (let j = 0; j < this.parkingSpots.length; j++) {
                  if (this.parkingSpots[j].parkingSpotID == selectedSpot.parkingSpotID) {
                    this.parkingSpots[j].monthlyReservationDto = selectedSpotDto
                    break
                 }}
       
      })
      .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorReservation = errorMsg
      })
      },
}}