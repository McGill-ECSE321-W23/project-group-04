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
          <el-menu-item v-for="(car, i) in cars" :index="i.toString()" @click="handleCarSelectionChange(i)">
            <span>{{car.make}} {{car.model}}</span>
          </el-menu-item>
        </el-menu>
        <br>
        <el-button :icon="Plus" @click="this.dialogVisible = true">Add Car</el-button>
        <el-dialog v-model="this.dialogVisible" title="Car Information">
          <el-form :model="carForm">
            <el-form-item label="Make" :label-width="formLabelWidth">
              <el-input v-model="carForm.make" autocomplete="off" />
            </el-form-item>
            <el-form-item label="Model" :label-width="formLabelWidth">
              <el-input v-model="carForm.model" autocomplete="off" />
            </el-form-item>
            <el-form-item label="License Plate Number" :label-width="formLabelWidth">
              <el-input v-model="carForm.plate" autocomplete="off" />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible = false">Cancel</el-button>
              <el-button type="success" @click="registerCar">
                Register
              </el-button>
            </span>
          </template>
        </el-dialog>
      </el-aside>

      <el-container>
        <el-card style="width: 100%">
          <template #header>
            License Number: {{cars[currentIndex]? cars[currentIndex].licensePlate : 0}}
          </template>
          <el-table :data="tableData"
                    @selection-change="handleSelectionChange"
                    ref="multipleTableRef"
                    style="width: 100%"          >
<!--            <el-table-column type="selection" width="55" />-->
            <el-table-column prop="service.offeredServiceDescription" label="Service Name" width="150" />
            <el-table-column prop="garage.garageNumber" label="Garage" width="100" />
            <el-table-column prop="startTime" label="Date" width="180" :formatter="dateTimeArrToString"/>

            <el-table-column prop="appointmentStatus" label="Status">
<!--              <template #default>-->
<!--                <el-button type="primary" :icon="Check" circle @click="handleClick"/>-->
<!--              </template>-->
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
import {Check, Edit, Plus} from "@element-plus/icons-vue";

export default {
    name: "UserProfile",
  computed: {
    Plus() {
      return Plus
    },
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
        carForm: {},
        currentIndex: 0,
        dialogVisible: false,
        times: [],
        tickets: [],
        cars: [],
        serviceData: [],
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

                  this.cars.forEach((car, i) => {
                    this.axios.get('/api/appointment/getByCar/' + car.carID)
                        .then(res => {
                          console.log(res.data)
                          this.serviceData.push(res.data)
                          if (i === 0) {
                            this.tableData = res.data
                          }
                        })
                        .catch(err => console.log(err))
                  })
                })
                .catch(err => {
                  console.log(err)
                })
          })
          .catch(err => {
            console.log(err)
          })
    },
    onBeforeUpdate() {
      this.tableData = this.serviceData[i]
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
              alert("renew successful")
            })
            .catch(err => {
              console.log(err)
            })
            .finally(() => {
              window.location.reload()
            })
      },
      handleSelect(key, keyPath) {
        this.currentIndex = key
      },
      handleSelectionChange(k) {
        console.log("ay")
        console.log(k)
      },
      handleCarSelectionChange(i) {
        this.tableData = this.serviceData[i]
      },
      dateTimeArrToString(row, col) {
        console.log(row,  col)
        let d = new Date(...row.startTime)
        const options = {
          year: 'numeric',
          month: 'short',
          day: 'numeric',
          hour12: false,
          hour: 'numeric',
          minute: 'numeric',
          second: 'numeric'
        };        return d.toLocaleString('en-US', options)
      },
      registerCar() {
        const userService = new UserService(this.axios)

        this.axios.post('/api/cars/register', {
          personID: userService.getCookie("personId"),
          phoneNumber: this.form.phone,
          name: this.form.name
        }, {
          params: {
            licensePlate: this.carForm.plate,
            make: this.carForm.make,
            model: this.carForm.model,

          }
        })
            .then(res => {
              console.log(res)
              alert("car added")
            })
            .catch()
            .finally(() => {
              this.dialogVisible = false
              window.location.reload();
            })
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