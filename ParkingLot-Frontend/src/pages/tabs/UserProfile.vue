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
    <el-button type="primary" round>Update</el-button>
  </el-form>

  <el-divider />
  <h2>Tickets & Monthly Reservations</h2>
  <br>
  <el-container>
    <template v-for="(ticket, i) in tickets">
      <el-card class="box-card">
        <template #header>
          <span >Ticket {{i+1}}</span>
        </template>
        <div>Start Time: {{ticket.start}}</div>
        <span>Time Remaining: {{ticket.remainingTime}} min</span>
        <br>
        <br>
          <el-time-select
              v-model="times[i]"
              start="00:00"
              step="00:15"
              end="12:00"
              placeholder="Select time"
          />
        <el-button type="success">
          Add Time
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
            License Number: {{cars[currentIndex].license}}
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

<script setup>
import {inject, ref} from "vue";
import {Check, Edit,} from '@element-plus/icons-vue'
// import UserService from "@/services/UserService";
//
// const axios = inject('axios')
// const userService = new UserService(axios)

// const form = ref({
//   name: 'jon',
//   email: 'jon@mcgill',
//   password: '123',
//   phone: '514'
// })

// axios.get('api/account/get/' + userService.getCookie("accountId"))
//     .then((data) => {
//       data = data.data
//       console.log(data.person.name)
//       const form = ref({
//         name: data.person.name,
//         email: data.email,
//         password: data.password,
//         phone: data.person.phoneNumber
//       })
//     })
//     .catch(err => {
//       console.log(err)
//     })

const times = ref([])

const cars = ref([
  {
    license: "dasd",
    make: "honda",
    model: "civic"
  },
  {
    license: "yayayaya",
    make: "toyota",
    model: "rav4"
  }
])

const tickets = ref([
  {
    carLicense: "141",
    start: "15h45",
    remainingTime: 54
  },
  {
    carLicense: "141",
    start: "5h45",
    remainingTime: 42
  }
])

const tableData = [
  {
    name: 'Tire Change',
    location: "5",
    date: '2016-05-03',
    status: 'Check',
  },
  {
    name: 'Wash',
    location: "3",
    date: '2016-05-02',
    status: 'Star',
  }
]

const currentIndex = ref(0)

const handleSelect = (key, keyPath) => {
  currentIndex.value = key
}

const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
}

const handleSelectionChange = () => {}
</script>

<script>
  import {inject, onMounted} from "vue";
  import UserService from "@/services/UserService";

  export default {
    name: "UserProfile",
    props: {
      // isLoggedIn: {
      //   type: Boolean,
      //   default: false
      // }
    },
    data() {
      return {
        form: {
          name: 'jon',
          email: 'jon@mcgill',
          password: '123',
          phone: '514'
        }
      }
    },
    mounted() {
      const axios = inject('axios')
      const userService = new UserService(axios)

      axios.get('api/account/get/' + userService.getCookie("accountId"))
          .then((data) => {
            data = data.data
            this.form.name = data.person.name
            this.form.email = data.email
            this.form.password = data.password
            this.form.phone = data.person.phoneNumber
            console.log(this.form)
          })
          .catch(err => {
            console.log(err)
          })
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