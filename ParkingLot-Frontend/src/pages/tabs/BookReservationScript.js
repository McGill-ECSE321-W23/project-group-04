import $ from 'jquery'
import { start } from 'repl'
import { ref } from 'vue'


      function ReservationDto (phoneNumber, personName, licensePlate, carMake, carModel, 
         floor, spotNumber, startDate, endDate) {
        this.phoneNumber = phoneNumber
        this.personName = personName
        this.licensePlate = licensePlate
        this.carMake = carMake
        this.carModel = carModel
        this.floor = floor
        this.spotNumber = spotNumber
        this.startDate = startDate
        this.endDate = endDate
      }
      function OfferedServiceDto (phoneNumber, personName, licensePlate, carMake, carModel, 
        service, garage, timeSlot, startDate) {
       this.phoneNumber = phoneNumber
       this.personName = personName
       this.licensePlate = licensePlate
       this.carMake = carMake
       this.carModel = carModel
       this.service = service
       this.garage = garage
       this.timeSlot = timeSlot
       this.startDate = startDate
     }
  
export default {
    name: "AdminUsers",
    components: {
    },
    data () {
        return {
          tabPosition: ref('left'),
          reservations: [],
          newReservation: '',
          errorReservation: '',
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
              label: 'Wheel Slignment',
            },
            {
              value: '6',
              label: 'Air Conditioning Service',
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

        const num = ref(1)
        const handleChange = (value: number) => {
          console.log(value)
        }
        
        

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
            // Create a new person and add it to the list of people
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
    
  

