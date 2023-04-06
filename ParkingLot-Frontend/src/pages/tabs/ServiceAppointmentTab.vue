<template>
  <div class="container">
      <h1>Services</h1>
  </div>
  <br />

  <!-- Dropdown box to select the desired service -->
  <div class="container">
      <label for="offeredServiceSelect" style="padding-right: 10px">Select a service:</label>
      <el-select v-model="description" placeholder="Select a service" style="width: 300px">
          <el-option v-for="item in offeredServicesAvailable" :key="item.description" :label="item.label"
              :value="item.description">
          </el-option>
      </el-select>

      <!-- Choose the offered service -->
      <el-button type="primary" @click="showTable">Show available appointments</el-button>
  </div>
  <br />

  <!-- Show all the service appointments available -->
  <div v-if="showAppointments">
    <!-- Show the common information -->
    <p>Service chosen: {{ selectedService.label }} <br />
       Duration: {{ selectedService.duration }} minutes <br />
       Cost: {{ selectedService.cost }} $ </p>

    <!-- Display the available appointments -->
    <el-table :data="availableServiceAppointments" height="250" style="width: 100%" @row-click="handleRowClick" :highlight-current-row="true">
      <el-table-column prop="date" label="Date" width="180" />
      <el-table-column prop="time" label="Time" width="180" />
      <el-table-column prop="garage" label="Garage" />
    </el-table>
    
    <!-- Choose the appointment -->
    <el-button type="primary" @click="saveRow">Book appointment</el-button>
    <div v-if="showMessage" class="message-container">
      <el-alert
        title="Appointment booked successfully"
        type="success"
        :closable="false"
        @closed="resetTable"
        :show-icon="true"
        :duration="3000"
      >
      </el-alert>
    </div>
  </div>
  
</template>

<script src="./ServiceAppointment.js">
  

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
</style>
