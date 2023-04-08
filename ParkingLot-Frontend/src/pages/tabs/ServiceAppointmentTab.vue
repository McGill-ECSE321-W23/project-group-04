<template>
  <div class="container">
    <h1>Book a Service Appointment</h1>
  </div>
  <br />

  <!-- Select Offered service -->
  <el-card class="container" style="display:block;" id="ViewOfferedServices">
      <!-- Header -->
      <div class="card-header">
          <h2>Offered Services:</h2> 
      </div>
      <br />

      <!-- Container for the individual offered services -->
      <div>
          <el-space wrap class="button" style="text-align: center;">
              <el-card v-for="item in offeredServicesAvailable" :key="item.id" class="box-card" style="width: 250px" id="offeredService_{{item.id}}">
                  <!-- Show the offered service -->
                  <div class="text item">
                      {{ 'Description: ' + item.description }} <br />
                      {{ 'Duration: ' + item.duration + ' minutes'}} <br />
                      {{ 'Cost: ' + item.cost + '$' }} <br />
                  </div>
                  <br />

                  <!-- Select the offered service button -->
                  <el-button type="primary" id="select_offered_service_button_{{item.id}}">Select Offered Service</el-button>
              </el-card>
          </el-space>
      </div>
      <br />

  <!-- Show all the service appointments available -->
  <div v-if="showAppointments">
    <!-- Show the common information -->
    <p>Service chosen: {{ selectedService.label }} <br />
      Duration: {{ selectedService.duration }} minutes <br />
      Cost: {{ selectedService.cost }} $ </p>

    <!-- Display the available appointments -->
    <el-table :data="availableServiceAppointments" height="250" style="width: 100%" @row-click="handleRowClick"
      :highlight-current-row="true">
      <el-table-column prop="date" label="Date" width="180" />
      <el-table-column prop="time" label="Time" width="180" />
      <el-table-column prop="garage" label="Garage" />
    </el-table>

    <!-- Choose the appointment -->
    <el-button type="primary" @click="saveSelectedAppointment">Book appointment</el-button>
    <div v-if="showMessage" class="message-container">
      <el-alert title="Appointment booked successfully" type="success" :closable="true" @close="resetTable"
        :show-icon="true">
      </el-alert>
    </div>
  </div>

  </el-card>
  <br />


</template>

<script src="./ServiceAppointment.js">


</script>

<style>
.container {
  display: flex;
  align-items: center;
  justify-content: center;
}

;

.message-container {
  position: absolute;
  top: 50px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
