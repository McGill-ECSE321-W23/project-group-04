import $ from 'jquery'
import { ref } from 'vue'

      function PLSSystemDto (id, costper15m, numRegSpots, numLargeSpots) {
        this.id = id
        this.costper15m= costper15m
        this.numRegSpots = numRegSpots
        this.numLargeSpots = numLargeSpots
      }

      function TicketDto(id, date, time, pls){
        this.id = id
        this.date = date
        this.time = time
        this.pls = pls
      }
  
export default {
    name: "HomePage",
    components: {
    },
    data () {
        return {
          ticketInput: ref(''),
          activeName: ref('first'),
          tickets: [],
          newTicket: '',
          errorTicker: '',
          response: [],
          selectedTicket: "",
        }
      },
      created: function () {
        // Test data
        const pls = new PLSSystemDto('1', '3', '100', '20')
        const t1 = new TicketDto('1111','123-456-7890','John Doe', 'abc@gmail.com','CRA-123','ABC-123', '1', '12')
        const t2 = new TicketDto('1211','000-000-0000','Jane Doe','N/A', 'ABT-345', 'ABC-123', 'B1','10')
        this.tickets = [t1, t2]

      },
    mounted() {
    },
    methods: {
        createReservation: function (id,  date, time) {
            var t= new newTicketDtop(id, date, time, pls)
            this.tickets.push(t)
            this.newTicket = ''
          },
        
          payTicket: function (id) {
            
            for (let i = 0; i < tickets.length; i++) {
                if (tickets[i].id == id) {
                 var t = tickets[i]
                }
              }

            this.tickets.pop(t)
          },
      }
    
}