<template>
  
  <div class="header-container">
    <h1>Book a Service Appointment</h1>
  </div>

  <!-- Select Offered service -->
  <div class="page">
    <div class="container" v-if="showOfferedServices" id="OfferedServices">
      <!-- Header -->
      <div class="card-header">
          <h2>Offered Services:</h2> 
      </div>
      
      <!-- Table for the offered services -->
      <el-table ref="table" :data="offeredServices" height="500" style="width: 100%" @row-click="handleOfferedServiceRowClick" :highlight-current-row="true">
        <el-table-column prop="description" label="Description" style="width: 100%" />
        <el-table-column prop="duration" label="Duration (minutes)" style="width: 100%" />
        <el-table-column prop="cost" label="Cost ($)" style="width: 100%" />
      </el-table>
      <br/>

    <!-- Alert to choose an offered service -->
    <el-alert v-if="showErrorOfferedService" title="Please choose an offered service" type="error" :closable="false" show-icon />
    <br/>

    <!-- Confirmation message -->
    <el-alert v-if="showConfirmation" title="Appointment booked successfully" type="success" :closable="true" @close="closeConfirmation" show-icon/>
    <br/>

      <!-- Select the offered service button -->
      <div class="button-container">
          <el-button type="primary" @click="selectOfferedService">Select Offered Service</el-button>
      </div>
    </div>

  <!-- Allow to book a request for an appointment -->
    <div class="container" v-if="showBookAppointment" id="BookAppointment">
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
          <el-date-picker v-model="date" type="date" placeholder="Date" :picker-options="pickerOptions" size="size"/>
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
        <el-select v-model="garage" placeholder="Select a garage" style="width: 145px">
          <el-option v-for="item in garages" :key="item.garageNumber" :label="item.id" :value="item.garageNumber"></el-option>
        </el-select>
      </div>
      <br />

      <!-- Choose the appointment -->
      <div>
        <el-button type="warning" :icon="icons.arrowLeftIcon" @click="goBack">Go back</el-button>
        <el-button type="primary" @click="saveAddAppointment">Book appointment</el-button>
      </div>
    </div>
  </div>

</template>

<script src="./ServiceAppointment.js"></script>

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