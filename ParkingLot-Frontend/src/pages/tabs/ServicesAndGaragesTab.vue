<template>
    <!-- Offered services -->
    <el-card class="container" id="OfferedServicesEdit">
      <div>
        <div class="card-header">
          <h2>Offered Services:</h2> <br />
        </div>
      </div>
      <div>
        <el-space wrap class="button" style="text-align: center;">
            <el-card v-for="item in offeredServicesAvailable" :key="item.id" class="box-card" style="width: 250px">
              <div class="text item">
                {{ 'Description: ' + item.description }} <br />
                {{ 'Duration: ' + item.duration }} <br />
                {{ 'Cost: ' + item.cost }} <br />
              </div>
              <br />
              <!-- Edit an offered service -->
              <a key="item.id" :href="'/edit_OfferedService/' + item.id">
                <el-button type="primary">Edit Offered Service</el-button>
              </a>
              <!-- Delete an offered service -->
              <el-button type="danger" :icon="Delete" circle />
            </el-card>
        </el-space>
      </div>
      <br />
      <!-- Add an offered service -->
      <a href="/add_OfferedService">
        <el-button type="success">Add Offered Service</el-button>
      </a>
        </el-card>
    <br />
  
    <!-- Garages -->
    <el-card class="container" id="GaragesEdit">
      <div>
        <div class="card-header">
          <h2>Garages:</h2> <br />
        </div>
      </div>
      <div>
        <el-space wrap class="button" style="text-align: center;">
            <el-card v-for="item in garages" :key="item.id" class="box-card" style="width: 250px">
              <div class="text item">
                {{ 'Garage number: ' + item.garageNumber }} <br />
              </div>
              <br />
              <!-- Edit a garage -->
              <a key="item.id" :href="'/edit_Garage/' + item.id">
                <el-button type="primary">Edit Garage</el-button>
              </a>
              <!-- Delete a garage -->
              <el-button type="danger" :icon="Delete" circle @click="deleteGarage" />
            </el-card>
        </el-space>
      </div>
      <br />
      <!-- Add a garage -->
      <a href="/add_garage">
        <el-button type="success">Add Garage</el-button>
      </a>
    </el-card>
  </template>
  
  <script setup>
  import { offeredServicesAvailable, garages } from './ServicesAndGarages.js'
  import { reactive } from 'vue'
  import { Delete } from '@element-plus/icons-vue'
  
//   const state = reactive({
//     // State for adding an offered service
//     newOfferedService: {
//       description: '',
//       duration: '',
//       cost: ''
//     }
//   })
  
//   function addOfferedService() {
//     // Generate a unique ID for the new offered service
//     const id = Math.max(...offeredServicesAvailable.map(item => item.id)) + 1
  
//     // Add the new offered service to the list
//     offeredServicesAvailable.push({
//       id,
//       description: state.newOfferedService.description,
//       duration: state.newOfferedService.duration,
//       cost: state.newOfferedService.cost
//     })

  </script>
  
  <style scoped>
  .card-header {
    text-align: center;
  }
  
  .button {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  </style>
  