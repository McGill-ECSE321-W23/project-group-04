import $ from 'jquery'
import { ref } from 'vue'

      function PLSSystemDto (id, costper15m, numRegSpots, numLargeSpots) {
        this.id = id
        this.costper15m= costper15m
        this.numRegSpots = numRegSpots
        this.numLargeSpots = numLargeSpots
      }

      function TicketDto(id, date, time, type, pls){
        this.id = id
        this.date = date
        this.time = time
        this.pls = pls
        this.type = type
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
          regLeft:'',
          largeLeft:'',
        }
      },
      created: function () {
        // Test data
        const pls = new PLSSystemDto('1', '3', '100', '20')
        const t1 = new TicketDto('1111','2023-04-06','8:15', 'Regular',pls)
        const t2 = new TicketDto('1211','2023-04-06','9:35','Large', pls)
        this.tickets = [t1, t2]
        this.regLeft = '99'
        this.largeLeft = '19'

      },
    mounted() {
    },
    methods: {
        createRegTicket: function (id,  date, time) {
            var t= new newTicketDtop(id, date, time, 'Regular', this.pls)
            this.tickets.push(t)
            this.newTicket = ''
            // let regLeftInt = parseInt(this.regleft)
            // regLeftInt = regLeftInt -1
            // this.regLeft = regLeftInt.toString
          },

        createLargeTicket: function (id,  date, time) {
            var t= new newTicketDtop(id, date, time, 'Large', this.pls)
            this.tickets.push(t)
            this.newTicket = ''
            
            this.largeLeft = this.largeLeft - 1
          },
        
          payTicket: function (id) {
            
            for (let i = 0; i < tickets.length; i++) {
                if (tickets[i].id == id) {
                 var t = tickets[i];
                }
              }

            this.tickets.pop(t)
          },
          decRegTicket: function(){
            let regLeftInt = parseInt(this.regleft)
            regLeftInt = regLeftInt -1
            regLeft = regLeftInt
          }

          
      }
    
}