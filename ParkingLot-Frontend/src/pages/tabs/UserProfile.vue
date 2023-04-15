<template>
  <h2>My Info</h2>
  <br>
  <el-form
      :model="form"
      :inline="true"
      label-width="120px"
  >
    <el-form-item label="name">
      <el-input v-model="form.name" />
    </el-form-item>
    <el-form-item label="email">
      <el-input v-model="form.email" readonly/>
    </el-form-item>
    <el-form-item label="password">
      <el-input v-model="form.password" show-password/>
    </el-form-item>
    <el-form-item label="phone number">
      <el-input v-model="form.phone" />
    </el-form-item>
    <br>
    <el-button type="primary" round @click="updateProfile()">Update</el-button>
  </el-form>

  <el-divider />
  <h2>Monthly Reservations</h2>
  <br>
  <el-container>
    <template v-for="(ticket, i) in tickets">
      <el-card class="box-card">
        <template #header>
          <span >Ticket {{i+1}}</span>
        </template>
        <div>Start Time: {{ticket.startDate}}</div>
        <span>End Time: {{ticket.endDate}}</span>
        <br>
        <br>
<!--          <el-time-select-->
<!--              v-model="times[i]"-->
<!--              start="00:00"-->
<!--              step="00:15"-->
<!--              end="12:00"-->
<!--              placeholder="Select time"-->
<!--          />-->
<!--        <el-button type="success">-->
<!--          Add Time-->
<!--        </el-button>-->
        <el-button type="success" @click="renewMonthly(i)">
          Renew
        </el-button>
      </el-card>
      &nbsp;
    </template>
  </el-container>

  <el-divider />

  <h2>My cars</h2>
  <br>
  <el-container>
      <el-aside>
        <el-menu
            default-active="0"
            class="el-menu-vertical-demo"
            @select="handleSelect"
        >
          <el-menu-item v-for="(car, i) in cars" :index="i.toString()">
            <span>{{car.make}} {{car.model}}</span>
          </el-menu-item>
          <el-menu-item>
            <el-button :icon="Edit"></el-button>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-card>
          <template #header>
            License Number: {{cars[currentIndex]? cars[currentIndex].licensePlate : 0}}
          </template>
          <el-table :data="tableData"
                    @selection-change="handleSelectionChange"
                    ref="multipleTableRef"
          >
            <el-table-column type="selection" width="55" />
            <el-table-column prop="name" label="Service Name" width="150" />
            <el-table-column prop="location" label="Garage" width="100" />
            <el-table-column prop="date" label="Date" width="180" />
            <el-table-column prop="status" label="Status">
              <template #default>
                <el-button type="primary" :icon="Check" circle @click="handleClick"/>
              </template>
            </el-table-column>

          </el-table>
        </el-card>
      </el-container>
  </el-container>

</template>

<script lang="js">
import {inject} from "vue";
import UserService from "@/services/UserService";
import {ElNotification} from "element-plus";
import {Check, Edit} from "@element-plus/icons-vue";

export default {
    name: "UserProfile",
  computed: {
    Check() {
      return Check
    },
    Edit() {
      return Edit
    }
  },
    props: {
    },
    data() {
      return {
        form: {},
        currentIndex: 0,
        times: [],
        tickets: [],
        cars: [],
        tableData: [],
        axios: inject('axios')
      }
    },
    setup() { },
    mounted() {
      const userService = new UserService(this.axios)

      // get account data
      this.axios.get('api/account/get/' + userService.getCookie("accountId"))
          .then((data) => {
            data = data.data
            this.form.name = data.person.name
            this.form.email = data.email
            this.form.password = data.password
            this.form.phone = data.person.phoneNumber
            console.log(this.form)

            let date = new Date();
            date.setTime(date.getTime() + (3 * 60 * 60 * 1000)); // 1 hour in milliseconds
            document.cookie = "personId="+data.person.personID

            // get account reservations
            this.axios.get('api/monthlyReservation/' + userService.getCookie("personId"))
                .then(data => {
                  console.log(data.data)
                  this.tickets = data.data
                })
                .catch(err => {
                  console.log(err)
                })

            // get cars
            this.axios.get('api/cars/get/ByOwner/' + userService.getCookie("personId"))
                .then(data => {
                  this.cars = data.data
                })
                .catch(err => {
                  console.log(err)
                })
          })
          .catch(err => {
            console.log(err)
          })
    },
    methods: {
      updateProfile() {
        console.log("pressed")
        const userService = new UserService(this.axios)
        this.axios.put("/api/account/update/" + userService.getCookie("accountId"), {}, {
          params: {
            email: this.form.email,
            password: this.form.password
          }
        })
            .then(res => {
              console.log(res)
              ElNotification({
                title: 'Success',
                message: 'Account Updated',
                type: 'success',
                showClose: false,
                duration: 3000
              })
            })
            .catch(err => {
              console.log(err)
            })
      },
      renewMonthly(i) {
        this.axios.put('/api/monthlyReservation/pay', {}, {
          params: {
            reservationId: this.tickets[i].monthlyReservationID,
            amount: 999999
          }
        })
            .then(data => {
              console.log(data.data)
            })
            .catch(err => {
              console.log(err)
            })
      },
      handleSelect(key, keyPath) {
        this.currentIndex = key
      },
      handleSelectionChange(k) {
        console.log("ay")
        console.log(k)
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath)
      },
      handleClose (key, keyPath) {
        console.log(key, keyPath)
      },
      handleClick() {

      }
    }
  }
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>