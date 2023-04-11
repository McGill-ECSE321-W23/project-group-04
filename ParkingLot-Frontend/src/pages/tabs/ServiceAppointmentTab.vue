<template>

  <div class="container">
    <h1>Book a Service Appointment</h1>
  </div>
  <br />

  <!-- Select Offered service -->
  <el-card class="container" style="display:block;" id="OfferedServices">
    <!-- Header -->
    <div class="card-header">
        <h2>Offered Services:</h2> 
    </div>
    <br />
      
    <!-- Table for the offered services -->
    <el-table :data="offeredServicesAvailable" height="500" style="width: 100%" @row-click="handleOfferedServiceRowClick" :highlight-current-row="true">
      <el-table-column prop="description" label="Description" style="width: 100%" />
      <el-table-column prop="duration" label="Duration (minutes)" style="width: 100%" />
      <el-table-column prop="cost" label="Cost ($)" style="width: 100%" />
    </el-table>
    <br/>

    <!-- Select the offered service button -->
    <div class="button-container">
        <el-button type="primary" id="select_offered_service_button" >Select Offered Service</el-button>
    </div>
    <br />
  </el-card>
  <br />

  <!-- Allow to book a request for an appointment -->
  <el-card wrap class="button" style="display:block;" id="BookAppointment">
    <!-- Header -->
    <div class="card-header">
      <h2>Request an appointment:</h2> 
    </div>
    <br />

    <!-- Show the offered service information -->
    <div class="text item">
      <!-- {{ 'Description: ' + selectedRow.description }} <br />
      {{ 'Duration: ' + selectedRow.duration }} <br />
      {{ 'Cost: ' + selectedRow.cost }} <br /> -->
      {{ 'Description: ' + description }} <br />
      {{ 'Duration: ' + duration }} <br />
      {{ 'Cost: ' + cost }} <br />
    </div>
    <br />

    <!-- Pick a date -->
    <div class="demo-date-picker">
      <div class="block">
        <span>Pick a date: </span>
        <el-date-picker v-model="date" type="date" placeholder="Date" :picker-options="pickerOptions" size="size" />
      </div>
    </div>  

    <!-- Pick a time -->  
    <div class="example-basic">
      <span>Pick a time: </span>
      <el-time-picker v-model="time" placeholder="Time" />
    </div>
    <br />

    <!-- Choose the appointment -->
    <el-button type="warning" :icon="ArrowLeft" plain id="go_back_button"> Go back</el-button>
    <el-button type="primary" id="saveSelectedAppointment">Book appointment</el-button>
    <div v-if="showMessage" class="message-container">
      <el-alert title="Appointment booked successfully" type="success" :closable="true" @close="back" :show-icon="true">
      </el-alert>
    </div>
    <br />
  </el-card>

</template>

<script setup>

import { offeredServicesAvailable } from './ServiceAppointment.js'
import { ref } from 'vue'
import $ from 'jquery'
import { ArrowLeft } from '@element-plus/icons-vue'

var showMessage = ref(false)
const date = ref('')
const time = ref('')

// let date = ref('')
// let time = ref('')
let selectedRow = null;
let description = ref('')
let duration = ref('')
let cost = ref('')

// Save the row of the offered service that was selected
function handleOfferedServiceRowClick(row) {
    selectedRow = row;
};

// Only show the offered services when opening the tab
$(document).ready(function() {
    $('#OfferedServices').show();
    $('#BookAppointment').hide();

      // Bo back to the selection of offered service section
      $('#select_offered_service_button').click(function() {
        if (selectedRow) {
          description.value = selectedRow.description;
          duration.value = selectedRow.duration;
          cost.value = selectedRow.cost;
          $('#OfferedServices').hide();
          $('#BookAppointment').show();
        }
      });

      // Bo back to the selection of offered service section
      $('#go_back_button').click(function() {
        $('#OfferedServices').show();
        $('#BookAppointment').hide();
      });
      
      // Bo back to the selection of offered service section
      $('#saveSelectedAppointment').click(function() {
        if (date.value !== '' && time.value !== '') {
          $('#OfferedServices').show();
          $('#BookAppointment').hide();
          showMessage = true;
        }
      });
});

</script>

<style>

.container {
  display: flex;
  align-items: center;
  justify-content: center;
};

.message-container {
  position: absolute;
  top: 50px;
  left: 50%;
  transform: translateX(-50%);
}

.example-basic .el-date-editor {
  margin: 8px;
}

.card-header {
  text-align: center;
}

.button-container {
  display: flex;
  align-items: center;
  justify-content: center;
}

.table {
  display: flex;
  justify-content: center;
}

#BookAppointment {
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
}

</style>
