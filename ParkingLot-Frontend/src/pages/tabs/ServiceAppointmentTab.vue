<template>
  
  <div class="header-container">
    <h1>Book a Service Appointment</h1>
  </div>

  <!-- Select Offered service -->
  <div class="page">
    <div class="container" id="OfferedServices">
      <!-- Header -->
      <div class="card-header">
          <h2>Offered Services:</h2> 
      </div>
      
      <!-- Table for the offered services -->
      <el-table ref="table" :data="offeredServicesAvailable" height="500" style="width: 100%" @row-click="handleOfferedServiceRowClick" :highlight-current-row="true">
        <el-table-column prop="description" label="Description" style="width: 100%" />
        <el-table-column prop="duration" label="Duration (minutes)" style="width: 100%" />
        <el-table-column prop="cost" label="Cost ($)" style="width: 100%" />
      </el-table>
      <br/>

    <!-- Confirmation message -->
    <el-alert v-if="showMessage" title="Appointment booked successfully" type="success" :closable="true" @close="reset" show-icon  id="confirmation"/>
    <br/>

      <!-- Select the offered service button -->
      <div class="button-container">
          <el-button type="primary" id="select_offered_service_button" >Select Offered Service</el-button>
      </div>
    </div>
  </div>

  <!-- Allow to book a request for an appointment -->
  <div class="page">
    <div class="container" id="BookAppointment">
        <!-- Header -->
        <div class="card-header">
          <h2>Request an appointment:</h2> 
        </div>

      <!-- Show the offered service information -->
      <div class="text item">
        {{ 'Description: ' + description }} <br />
        {{ 'Duration: ' + duration }} <br />
        {{ 'Cost: ' + cost }} <br />
      </div>
      <br />

      <!-- Pick a date -->
      <div class="demo-date-picker">
        <div class="block">
          <span>Pick a date: </span>
          <el-date-picker v-model="date" type="date" placeholder="Date" :picker-options="pickerOptions" size="size" id="datePicker" />
        </div>
      </div>  

      <!-- Pick a time -->  
      <div class="example-basic">
        <span>Pick a time: </span>
        <el-time-picker v-model="time" placeholder="Time" />
      </div>
      <br />

      <!-- Pick a garage -->  
      <div>
        <span>Pick a garage: </span>
        <el-select v-model="garageNumber" placeholder="Select a garage" style="width: 145px" id="selectGarage">
          <el-option v-for="item in garagesAvailable" :key="item.garageNumber" :label="item.id" :value="item.garageNumber"></el-option>
        </el-select>
      </div>
      <br />

      <!-- Choose the appointment -->
      <div>
        <el-button type="warning" :icon="ArrowLeft" plain id="go_back_button"> Go back</el-button>
        <el-button type="primary" id="saveSelectedAppointment">Book appointment</el-button>
      </div>
    </div>
  </div>

</template>

<script setup>

  import { offeredServicesAvailable, garagesAvailable } from './ServiceAppointment.js'
  import { reactive, ref } from 'vue'
  import $ from 'jquery'
  import { ArrowLeft } from '@element-plus/icons-vue'

  let showMessage = ref(false);
  const date = ref('');
  const time = ref('');
  let garageNumber = ref('');
  let table = null;

  let selectedRow = null;
  let description = ref('');
  let duration = ref('');
  let cost = ref('');


  // Save the row of the offered service that was selected
  function handleOfferedServiceRowClick(row) {
    selectedRow = row;
    description.value = selectedRow.description;
    duration.value = selectedRow.duration;
    cost.value = selectedRow.cost;
  };

  // Reset confirmation:
  function reset() {
    showMessage.value = false;
    const table = $refs.table;
    table.clearSelection();
  }

  // Only show the offered services when opening the tab
  $(document).ready(function() {
    $('#OfferedServices').show();
    $('#BookAppointment').hide();
    $('#confirmation').show();

      // Go back to the selection of offered service section
      $('#select_offered_service_button').click(function() {
        if (selectedRow) {
          $('#OfferedServices').hide();
          $('#BookAppointment').show();
          $('#confirmation').show();
        }
      });

      // Go back to the selection of offered service section when clicking the back button
      $('#go_back_button').click(function() {
        $('#OfferedServices').show();
        $('#BookAppointment').hide();
        $('#confirmation').show();

        date.value = '';
        time.value = '';
        garageNumber.value = '';
        selectedRow = null;
        description.value = '';
        duration.value = '';
        cost.value = '';
      });
      
      // Go back to the selection of offered service section after confirming the appointment
      $('#saveSelectedAppointment').click(function() {
        if (date.value !== '' && time.value !== '' && garageNumber.value !== '') {
          $('#OfferedServices').show();
          $('#BookAppointment').hide();
          $('#confirmation').show();

          date.value = '';
          time.value = '';
          garageNumber.value = '';
          selectedRow = null;
          description.value = '';
          duration.value = '';
          cost.value = '';
          showMessage.value = true;
        }
      });
  });

</script>

<style>

  .header {
    text-align: center;
  }

  #OfferedServices {
    background-color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 20px 30px;
    border-radius: 10px;
    box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.5);
  }

  #BookAppointment {
    max-width: 600px;
    margin-left: auto;
    margin-right: auto;
    background-color: white;
    display: flex;
    flex-direction: column;
    padding: 20px 30px;
    border-radius: 10px;
    box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.5);
  }

  .header-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100px;
  }

  .message-container {
    display: flex;
    position: absolute;
    top: 50px;
    width: 300px;
    text-align: right;
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

  .el-alert {
    margin: 20px 0 0;
  }

</style>