import $ from 'jquery'
import { ref } from 'vue'
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
      function ReservationDto (resID, phoneNumber, personName, personEmail, licensePlate1, licensePlate2, 
         floor, spotNumber) {
        this.resID = resID
        this.phoneNumber = phoneNumber
        this.personName = personName
        this.personEmail = personEmail
        this.licensePlate1 = licensePlate1
        this.licensePlate2 = licensePlate2
        this.floor = floor
        this.spotNumber = spotNumber
        

      }
      function PersonDto (personID, phoneNumber, personName, personEmail) {
       this.personID = personID
       this.phoneNumber = phoneNumber
       this.personName = personName
       this.personEmail = personEmail

     }
     function ParkingSpotDto (psID, floor, number) {
      this.psID = psID
      this.floor = floor
      this.number = number

    }

  
export default {
    name: "AdminUsers",
    components: {
    },
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
          found_user: new PersonDto(),
          persons: [],
          tabPosition: ref('top'),
          reservations: [],
          newReservation: '',
          errorReservation: '',
          response: [],
          selectedSpot: "",
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
          ]
        }
      },
      created: function () {

        // Test data
        // const r1 = new ReservationDto('1111','123-456-7890','John Doe', 'abc@gmail.com','CRA-123','ABC-123', '1', '12')
        // const r2 = new ReservationDto('1211','000-000-0000','Jane Doe','N/A', 'ABT-345', 'ABC-123', 'B1','10')
        // const r3 = new ReservationDto('1311','100-000-0000','Mary Doe','N/A', 'ASR-565','ABC-123', '2','14')
        // const r4 = new ReservationDto('1411','200-000-0000','Tom Doe','N/A', 'LSL-335','ABC-123', '3','16')
        // const r5 = new ReservationDto('1511','300-000-0000','Paul Doe','N/A', 'MOV-348','ABC-123', 'B1','10')
        // this.reservations = [r1, r2, r3, r4, r5]

        // const p1 = new PersonDto('101','123-456-7890','John Doe', 'abc@gmail.com')
        // const p2 = new PersonDto('102','000-000-0000','Jane Doe', 'N/A')
        // const p3 = new PersonDto('103','100-000-0000','Mary Doe', 'def@gmail.com')
        // const p4 = new PersonDto('104','200-000-0000','Jim Doe', 'N/A')
        // this.persons = [p1, p2, p3, p4]
        
          // Initializing persons from backend
          AXIOS.get('/api/persons/get')
          .then(response => {
            // JSON responses are automatically parsed.
            this.persons = response.data
          })
          .catch(e => {
            this.errorPerson = e
          })
          // Initializing events
          AXIOS.get('/api/monthlyReservation/allReservations')
          .then(response => {
            this.reservations = response.data
          })
          .catch(e => {
            this.errorReservation = e
            // this.errors.push(e)
          })
          AXIOS.get('/api/parkingSpot/getAll')
          .then(response => {
            this.parkingSpots = response.data
          })
          .catch(e => {
            this.errorSpot = e
            // this.errors.push(e)
          })
        },
      
    mounted() {
    },
    methods: {
        
          createPerson: function (personName, phoneNumber) {
            AXIOS.post('/api/person/create?name='.concat(personName).concat('&phoneNumber=').concat(phoneNumber)
            , {}, {})
              .then(response => {
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

          findPersonByID: function (personID) {
            AXIOS.post('/api/person/get/'.concat(personID), {}, {})
              .then(response => {
              // JSON responses are automatically parsed.
                this.found_user = response.data
                this.found = true
              })
              .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorPerson = errorMsg
              })
          },
          updatePerson: function (personID, newName, newPhone) {
            AXIOS.post('/api/person/update/'.concat(personID).concat('/name?').concat(newName).concat('&/phoneNumber?').concat(newPhone)
            , {}, {})
              .then(response => {
              // JSON responses are automatically parsed.

              for (let i = 0; i < this.persons.length; i++) {
                    if (this.persons[i].id == id) {
                     if (!(newName == "")) {
                      this.persons[i].personName = newName
                      }
                    if (!(newPhone == "")) {
                        this.persons[i].phoneNumber = newPhone
                      }
                      if (!(newEmail == "")) {
                        this.persons[i].personEmail = newEmail
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
          updateReservation: function (resID, parkingSpotDto) {
            AXIOS.post('/api/person/get/'.concat(resID), {parkingSpotDto: {parkingSpotID: psID, floor: aFloor, number: aNumber, monthlyReservationDto: aMonthlyReservationDto}}, {})
              .then(response => {
              // JSON responses are automatically parsed.

              for (let i = 0; i < this.parkingSpots.length; j++) {
                temp = this.parkingSpots[j].monthlyReservation
                if (temp.id == resID) {
                  this.parkingSpots[j].monthlyReservation = null
                  break
               }
              }
               for (let i = 0; i < this.parkingSpots.length; j++) {
                
                if (this.parkingSpots[j].id == psID) {
                  this.parkingSpots[j].monthlyReservation = temp
                  break
               }}

              })
              .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorPerson = errorMsg
              })
          },

         
          // findPersonByPhone: function (phoneNumber) {
          //   for (let i = 0; i < this.persons.length; i++) {
          //     if (this.persons[i].phoneNumber == phoneNumber) {
          //      this.found_user = this.persons[i]
          //      this.found = true
          //     }
          //   }
          // },
          // findPersonByID: function (id) {
          //   for (let i = 0; i < this.persons.length; i++) {
          //     if (this.persons[i].id == id) {
          //      this.found_user = this.persons[i]
          //      this.found = true
          //     }
          //   }
          // },
          // updatePerson: function(id, newName, newPhone, newEmail) {
          //   for (let i = 0; i < this.persons.length; i++) {
          //     if (this.persons[i].id == id) {
          //      if (!(newName == "")) {
          //       this.persons[i].personName = newName
          //       }
          //     if (!(newPhone == "")) {
          //         this.persons[i].phoneNumber = newPhone
          //       }
          //       if (!(newEmail == "")) {
          //         this.persons[i].personEmail = newEmail
          //       }
          //       break
          //     }
          //   }
          //   this.new_name=""
          //   this.new_email=""
          //   this.new_phone=""
          // },
          // updateReservation: function (id, newFloor, newSpot) {
            
          //   for (let i = 0; i < this.reservations.length; i++) {
          //       if (this.reservations[i].id == id) {
          //        this.reservations[i].floor = newFloor
          //        this.reservations[i].spotNumber = newSpot
          //        break
          //       }
          //     }
            
          // }
          
      }
    
}