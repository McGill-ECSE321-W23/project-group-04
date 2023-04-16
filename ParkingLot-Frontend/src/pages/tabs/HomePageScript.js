import { ref } from 'vue'
import axios from 'axios'
  
export default {
    name: "HomePage",
    data () {
        return {
          ticketInput: ref(''),
          newTicket: '',
          errorTicket: '',
          response: [],
          selectedTicket: "",
          regLeft:'',
          largeLeft: '',
          pls: '',
          errorMessage:'',
          maxReg: '',
          maxLarge: '',
          entryTime:'',
          endTime:'',
          cost15Min:'',
          totalCost: '',
          errorPLS: '',
          currentTicketID:'',
          ticketID: '',

          AXIOS: axios.create({
            baseURL: 'http://localhost:8080',
            headers: {'Access-Control-Allow-Origin': 'http://localhost:5173'},
            withCredentials: true
          })

        }
    },

    setup() {
        function PLSSystemDto (parkingLotSoftwareSystemID, monthlyFee, feePer15m, maxStay, numberOfRegularParkingSpots, 
                              numberOfLargeParkingSpots, numberOfMonthlyFloors, numberOfMonthlySpotsPerFloor, numberOfGarages) {
          this.parkingLotSoftwareSystemID = parkingLotSoftwareSystemID
          this.monthlyFee = monthlyFee
          this.feePer15m = feePer15m
          this.maxStay = maxStay
          this.numberOfRegularParkingSpots = numberOfRegularParkingSpots
          this.numberOfLargeParkingSpots = numberOfLargeParkingSpots
          this.numberOfMonthlyFloors = numberOfMonthlyFloors
          this.numberOfMonthlySpotsPerFloor = numberOfMonthlySpotsPerFloor
          this.numberOfGarages = numberOfGarages
        }

        function TicketDto(TicketID, EntryTime, CarTypeDto, PlsDto){
          this.TicketID = TicketID
          this.EntryTime = EntryTime
          this.PlsDto = PlsDto
          this.CarTypeDto = CarTypeDto
        } 
    },

    created () {
        // Initializing system from backend
        this.AXIOS.get('http://localhost:8080/api/parkinglotsoftwaresystem/get/1', {
          withCredentials: true, headers:{"Access-Control-Allow-Origin": 'localhost:8080'}})
        .then(response => {
          this.pls = response.data
          this.maxReg = response.data.numberOfRegularParkingSpots
          this.maxLarge = response.data.numberOfLargeParkingSpots
          this.cost15Min = response.data.feePer15m
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorPLS = errorMsg
        })

        
        this.AXIOS.get('http://localhost:8080/api/ticket/getCountRegular', {
          withCredentials: true, headers:{"Access-Control-Allow-Origin": 'localhost:8080'}})
          .then(response => {
          //JSON responses are automatically parsed.
          this.regLeft = this.maxReg - response.data
          })
          .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorTicket = errorMsg
        })

        this.AXIOS.get('http://localhost:8080/api/ticket/getCountLarge', { 
          withCredentials: true, headers:{"Access-Control-Allow-Origin": 'localhost:8080'}})
          .then(response => {
          this.largeLeft = this.maxLarge - response.data
          })
          .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorTicket = errorMsg
        })
    },

    methods: {

        createRegTicket: function () {

          if (this.regLeft <= 0){
            this.errorTicket = 'The parking lot cannot accomodate more regular cars.'
            console.log(errorTicket)
          }else{
            
            this.AXIOS.post('http://localhost:8080/api/ticket/create' , {}, {
              withCredentials: true, 
              headers:{"Access-Control-Allow-Origin": 'localhost:8080'},
              params: {
                type: "Regular"
              }
            })
            .then(response => {
              this.regLeft = this.regLeft -1
              this.currentTicketID = response.data.TicketID
              this.errorTicket = ''
              this.newTicket = ''
            })
            .catch(e => {
              var errorMsg = e.response.data.message
              console.log(errorMsg)
              this.errorTicket = errorMsg
            })
          }
        },

        createLargeTicket: function () {

            if (this.largeLeft <= 0){
              this.errorMessage = 'The parking lot cannot accomodate more large cars'
            }
            
            this.AXIOS.post('http://localhost:8080/api/ticket/create', {}, {
              withCredentials: true, 
              headers:{"Access-Control-Allow-Origin": 'localhost:8080'},
              params: {
                type: "Large"
              }
            })
            .then(response => {
            // JSON responses are automatically parsed.
              this.currentTicketID = response.data.TicketID
              this.largeLeft = this.largeLeft -1
              this.errorTicket = ''
              this.newTicket = ''
            })
            .catch(e => {
              var errorMsg = e.response.data.message
              console.log(errorMsg)
              this.errorTicket = errorMsg
            })

        },
        
        payTicket: function (id) {
            
            for (let i = 0; i < tickets.length; i++) {
                if (this.tickets[i].ticketID == id) {

            this.AXIOS.delete('http://localhost:8080/api/ticket/delete', {}, {
              withCredentials: true, 
              headers:{"Access-Control-Allow-Origin": 'localhost:8080'},
              params: {
                id: $("#ticketID").val()
              }
            })
            .then(response => {

              this.entryTime = response.data.startTime
              var today = new Date();
              this.endTime =  today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
              
              var elapsedHr =  entryTime.getHour() - this.endTime.getHours()
              var elapsedMin = entryTime.getMinute() - this.endTime.getMinutes()
              var elapsedTime = elapsedHr*60 + elapsedMin
              this.totalCost =  (elapsedTime/15)*this.costPMin

            })
            .catch(e => {
              var errorMsg = e.response.data.message
              console.log(errorMsg)
              this.errorTicket = errorMsg
            })

                }
              }

        },
      }
}

