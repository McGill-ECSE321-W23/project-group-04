import { ref } from 'vue'
import axios from 'axios'
  
export default {
    name: "HomePage",
    data () {
        return {
          ticketInput: ref(''),
          tickets: [],
          regTickets: [],
          largeTickets: [],
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
          costPMin:'',
          totalCost: '',

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
        //AXIOS.get('/api/parkinglotsoftwaresystem/get/1')
        //.then(response => {
          //this.pls = response.data
          //this.maxReg = response.data.numberOfRegularParkingSpots
          //this.regLeft = response.data.numberOfRegularParkingSpots
          //this.maxLarge = response.data.numberOfLargeParkingSpots
          //this.largeLeft = response.data.numberOfLargeParkingSpots
          //this.cost15Min = response.data.feePer15m
       // })
        //.catch(e => {
          //if (e.response) {
            //console.log(e.response.data)
            //console.log(e.response.status)
          //}
          //this.errorMessage = e.response.data
        //})
        
        // Initializing tickets from backend
       // AXIOS.get('/api/ticket/getAll').then(response => {
        // JSON responses are automatically parsed.
       // this.tickets = response.data

        //})
        //.catch(e => {
         // if (e.response) {
           // console.log(e.response.data)
            //console.log(e.response.status)
          //}
          //this.errorMessage = e.response.data
        //})

       //AXIOS.get('/api/ticket/getAllRegular').then(response => {
        // JSON responses are automatically parsed.

       // this.regTickets = response.data
        //this.regLeft = this.regLeft - this.regTickets.length
        //})
        //.catch(e => {
         // if (e.response) {
           // console.log(e.response.data)
            //console.log(e.response.status)
          //}
          //this.errorMessage = e.response.data
        //})

      // AXIOS.get('/api/ticket/getAllLarge').then(response => {
        // JSON responses are automatically parsed.
        //this.tickets = response.data
        //this.largeLeft = this.largeLeft = this.largeTickets.length
        //})
        //.catch(e => {
         // if (e.response) {
           // console.log(e.response.data)
            //console.log(e.response.status)
         // }
          //this.errorMessage = e.response.data
        //})

    },

    methods: {

        createRegTicket: function () {

          if (this.regLeft == 0){
            this.errorMessage = 'The parking lot cannot accomodate more regular cars'
          }else{
            
            AXIOS.post('/api/ticket/create' , {carTypeDto:'Regular', plsDto: this.pls}, {})
            .then(response => {
            // JSON responses are automatically parsed.
              this.tickets.push(response.data)
              this.regTickets.push(response.data)
              //this.regLeft = this.regLeft -1
              this.errorTicket = ''
              this.newTicket = ''
            })
            .catch(e => {
              //var errorMsg = e.response.data.message
              //console.log(errorMsg)
              this.errorTicket = e
            })
          }
        },

        createLargeTicket: function () {

            if (this.largeLeft == 0){
              this.errorMessage = 'The parking lot cannot accomodate more large cars'
            }
            
            AXIOS.post('/api/ticket/create', {carTypeDto:'Large', plsDto: this.pls}, {})
            .then(response => {
            // JSON responses are automatically parsed.
              this.tickets.push(response.data)
              this.largeLeft.push(response.data)
              this.largeLeft = this.largeLeft -1
              this.errorTicket = ''
              this.newTicket = ''
            })
            .catch(e => {
              //var errorMsg = e.response.data.message
              //console.log(errorMsg)
              this.errorTicket = e
            })

        },
        
        /* payTicket: function (id) {
            
            for (let i = 0; i < tickets.length; i++) {
                if (this.tickets[i].ticketID == id) {

            AXIOS.post('/api/ticket/delete/'+id, {}, {})
            .then(response => {
              var t = this.tickets[i];
              this.entryTime = t.startTime
              var today = new Date();
              this.endTime =  today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
              
              var elapsedHr =  entryTime.getHour() - this.endTime.getHours()
              var elapsedMin = entryTime.getMinute() - this.endTime.getMinutes()
              var elapsedTime = elapsedHr*60 + elapsedMin
              this.totalCost =  (elapsedTime/15)*this.costPMin
              this.tickets.pop(t)

            })
            .catch(e => {
              var errorMsg = e.response.data.message
              console.log(errorMsg)
              this.errorTicket = errorMsg
            })

                }
              }

        },*/
          
      }
}

