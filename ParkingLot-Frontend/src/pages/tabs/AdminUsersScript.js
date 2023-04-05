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
  
export default {
    name: "AdminUsers",
    components: {
    },
    data () {
        return {
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

      },
    mounted() {
    },
    methods: {
        createReservation: function (id, phoneNumber, personName, personEmail, licensePlate1, licensePlate2,floor, spotNumber) {
            // Create a new person and add it to the list of people
            var r = new ReservationDto(id, phoneNumber, personName, personEmail, licensePlate1, licensePlate2, floor, spotNumber)
            this.reservations.push(r)
            // Reset the name field for new people
            this.newReservation = ''
          },
        
          updateReservation: function (id, newFloor, newSpot) {
            
            for (let i = 0; i < reservations.length; i++) {
                if (reservations[i].id == id) {
                 var r = reservations[i]
                }
              }
            r.floor = newFloor
            r.spotNumber = newSpot
            this.reservations.push(r)
          },
      }
    
}