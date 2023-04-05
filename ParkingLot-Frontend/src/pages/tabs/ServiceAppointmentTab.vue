<template>

  <!-- The dropdown to select an offered service to book -->
  <div class="card flex justify-content-center">
    <form @submit="onSubmit" class="flex flex-column gap-2">
      <span class="p-float-label">
        <Dropdown
          id="dropboxID"
          v-model="value"
          :options="offeredServices"
          optionLabel="description"
          :class="['w-full md:w-15rem', { 'p-invalid': errorMessage }]"
          aria-describedby="dropboxID-error"
        />
        <label for="dropboxID">Select a Service</label>
      </span>
      <small class="p-error" id="dropboxID-error">{{ errorMessage || '&nbsp;'
      }}</small>
      <Button type="submit" label="View" />
    </form>
  </div>

  <!-- The common info (from the offered service) to all the service appointments -->
  <div class="card flex" v-if="value" >
      Service to view: {{ value.description }} <br>
      Duration: {{ value.duration }} minutes <br>
      Cost: {{ value.cost }} $ <br>
  </div>
  <div class="card flex" v-if="selectedOfferedService && showContents" id="chosenOfferedServiceID" >
    Service chosen: {{ selectedOfferedService.description }} <br>
    Duration: {{ selectedOfferedService.duration }} minutes <br>
    Cost: {{ selectedOfferedService.cost }} $ <br>
</div>

  <!-- The available appointments to book -->
  <div class="table" v-if="showContents" id="tableID" >
      <!-- The common info (from the offered service) to all the service appointments -->
    <DataTable
      v-model:selection="selectedServiceAppointment"
      :value="availableServicesAppointments"
      dataKey="id"
      tableStyle="min-width: 50rem"
      @selectionChange="onSelectionChange"
    >
      <Column selectionMode="single" headerStyle="width: 3rem"></Column>
      <Column field="date" header="Date"></Column>
      <Column field="time" header="Time"></Column>
      <Column field="garage" header="Garage"></Column>
    </DataTable>
    <div class="card flex justify-content-center">
      <Button type="submit" label="Choose" @click="onChoose" />
      <Toast v-if="selectedServiceAppointment" />
    </div>
  </div>

  <!-- The button to choose the appointment
  <div class="card flex justify-content-center">
    <Button type="submit" label="Choose" />
    <form @submit="onChoose" class="flex flex-column gap-2" />
  </div> -->

</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { ServiceAppointment } from '@/service/ServiceAppointment';
import { useToast } from 'primevue/usetoast';
import { useField, useForm } from 'vee-validate';

// For the offered services
const { handleSubmit, resetForm } = useForm();
const { value, errorMessage } = useField('value', validateField);
const offeredServices = ref();

// For the service appointments
const selectedOfferedService = ref();
const selectedServiceAppointment = ref();
const offeredServiceInfo = ref({});
const availableServicesAppointments = ref();
const showContent = ref(false);
const toast = useToast();

// Show the offered services on the dropdown box
onMounted(() => {
  ServiceAppointment.getOfferedServices().then(
    (data) => (offeredServices.value = data)
  );
});

// Make sure that the user selects an offered service
function validateField(value) {
  if (!value) {
    return 'Service is required.';
  }
  return true;
}

function showContents(showContent) {
  if (showContent.value) {
    document.getElementById('chosenOfferedServiceID').style.display = "block";
    document.getElementById('tableID').style.display = "block";
  }
  else {
    document.getElementById('chosenOfferedServiceID').style.display = "none";
    document.getElementById('tableID').style.display = "none";
  }
}

// Save the selected service appointment
function onSelectionChange(event) {
  selectedServiceAppointment.value = event.data;
}

// Reset the common info and table of offered services as soon as the dropbox selection is changed
watch(value, () => {
  showContent.value = false;
});

// Show the available service appointments depending on the offered service chosen
const onSubmit = handleSubmit((value) => {
  if (value.value && value.value.description) {
    showContent.value = !showContent.value;
    selectedOfferedService.value = value.value;
    // Show the common info of all available service appointments
    ServiceAppointment.getOfferedServicesInfo(selectedOfferedService.id).then(
    (data) => (offeredServiceInfo.value = data)
    );
    // Show all available service appointments
    ServiceAppointment.getAvailableServiceAppointments().then(
    (data) => (availableServicesAppointments.value = data)
    );
  resetForm();
  }
});

// Choose a service appointment
const onChoose = () => {
  if (selectedServiceAppointment.value) {
    toast.add({ severity: 'info', summary: 'Appointment booked', detail: selectedOfferedService.value.description + ' on ' + selectedServiceAppointment.value.date + ' at ' + selectedServiceAppointment.value.time, life: 3000 });
    resetForm();
  }
};
</script>
