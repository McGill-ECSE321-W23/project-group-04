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
      <el-input v-model="form.name" />
    </el-form-item>
    <el-form-item label="password">
      <el-input v-model="form.name" />
    </el-form-item>
    <el-form-item label="phone number">
      <el-input v-model="form.name" />
    </el-form-item>
  </el-form>

  <el-divider />
  <h2>Tickets</h2>
  <br>
  <el-container>
    <template v-for="(ticket, i) in tickets">
      <el-card class="box-card">
        <template #header>
          <span >Ticket {{i+1}}</span>
        </template>
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
        <el-button>
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
        </el-menu>
      </el-aside>

      <el-container>
        <el-card>
          {{cars[currentIndex].license}}
        </el-card>
      </el-container>
  </el-container>

</template>

<script setup>
import {reactive, ref} from "vue";

const form = reactive({
  name: '',
  region: '',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: '',
})
const times = ref([])

const cars = ref([
  {
    license: "dasd",
    make: "honda",
    model: "civic"
  },
  {
    license: "dasdaa",
    make: "toyota",
    model: "rav4"
  }
])

const tickets = ref([
  {
    carLicense: "141",
    remainingTime: 54
  },
  {
    carLicense: "141",
    remainingTime: 42
  }
])

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