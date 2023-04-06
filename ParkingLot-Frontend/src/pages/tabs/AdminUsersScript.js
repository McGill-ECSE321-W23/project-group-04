import $ from 'jquery'
import { ref } from 'vue'

      function ReservationDto (id, phoneNumber, personName, personEmail, licensePlate1, licensePlate2, 
         floor, spotNumber) {
        this.id = id
        this.phoneNumber = phoneNumber
        this.personName = personName
        this.personEmail = personEmail
        this.licensePlate1 = licensePlate1
        this.licensePlate2 = licensePlate2
        this.floor = floor
        this.spotNumber = spotNumber

      }
      function PersonDto (id, phoneNumber, personName, personEmail) {
       this.id = id
       this.phoneNumber = phoneNumber
       this.personName = personName
       this.personEmail = personEmail

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
        const r1 = new ReservationDto('1111','123-456-7890','John Doe', 'abc@gmail.com','CRA-123','ABC-123', '1', '12')
        const r2 = new ReservationDto('1211','000-000-0000','Jane Doe','N/A', 'ABT-345', 'ABC-123', 'B1','10')
        const r3 = new ReservationDto('1311','100-000-0000','Mary Doe','N/A', 'ASR-565','ABC-123', '2','14')
        const r4 = new ReservationDto('1411','200-000-0000','Tom Doe','N/A', 'LSL-335','ABC-123', '3','16')
        const r5 = new ReservationDto('1511','300-000-0000','Paul Doe','N/A', 'MOV-348','ABC-123', 'B1','10')
        this.reservations = [r1, r2, r3, r4, r5]

        const p1 = new PersonDto('101','123-456-7890','John Doe', 'abc@gmail.com')
        const p2 = new PersonDto('102','000-000-0000','Jane Doe', 'N/A')
        const p3 = new PersonDto('103','100-000-0000','Mary Doe', 'def@gmail.com')
        const p4 = new PersonDto('104','200-000-0000','Jim Doe', 'N/A')
        this.persons = [p1, p2, p3, p4]
      },
    mounted() {
    },
    methods: {
        createPerson: function (id, phoneNumber, personName, personEmail) {
            // Create a new person and add it to the list of people
            if (personEmail == "") {
              personEmail = 'N/A'
            }
            var p = new PersonDto(id, phoneNumber, personName, personEmail)
            this.persons.push(p)
            // Reset the name field for new people
            this.newPerson = ''
          },
          findPersonByPhone: function (phoneNumber) {
            for (let i = 0; i < this.persons.length; i++) {
              if (this.persons[i].phoneNumber == phoneNumber) {
               this.found_user = this.persons[i]
               this.found = true
              }
            }
          },
          findPersonByID: function (id) {
            for (let i = 0; i < this.persons.length; i++) {
              if (this.persons[i].id == id) {
               this.found_user = this.persons[i]
               this.found = true
              }
            }
          },
          updatePerson: function(id, newName, newPhone, newEmail) {
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
          },
          updateReservation: function (id, newFloor, newSpot) {
            
            for (let i = 0; i < this.reservations.length; i++) {
                if (this.reservations[i].id == id) {
                 this.reservations[i].floor = newFloor
                 this.reservations[i].spotNumber = newSpot
                 break
                }
              }
            
          }
          
      }
    
}