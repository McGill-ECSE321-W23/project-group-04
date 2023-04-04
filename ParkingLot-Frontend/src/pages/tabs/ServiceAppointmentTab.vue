<!-- Select the service to view the availabilities -->
<template>
  <div class="card flex justify-content-center">
    <form @submit="onSubmit" class="flex flex-column gap-2">
      <span class="p-float-label">
        <Dropdown
          id="dd"
          v-model="value"
          :options="services"
          optionLabel="description"
          :class="['w-full md:w-14rem', { 'p-invalid': errorMessage }]"
          aria-describedby="dd-error"
        />
        <label for="dd">Select a Service</label>
      </span>
      <small class="p-error" id="dd-error">{{
        errorMessage || '&nbsp;'
      }}</small>
      <Button type="submit" label="View" />
    </form>
    <Toast />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useToast } from 'primevue/usetoast';
import { useField, useForm } from 'vee-validate';

const { handleSubmit, resetForm } = useForm();
const { value, errorMessage } = useField('value', validateField);
const toast = useToast();
const services = ref([
  { description: 'Change Tires' },
  { description: 'Clean Car' },
  { description: 'Change Wipers' },
]);

function validateField(value) {
  if (!value) {
    return 'Service is required.';
  }

  return true;
}

const onSubmit = handleSubmit((values) => {
  if (values.value && values.value.description) {
    toast.add({
      severity: 'info',
      summary: 'Service to view selected',
      detail: values.value.name,
      life: 3000,
    });
    resetForm();
  }
});
</script>




<!-- Select the appointment to book -->
<template>
  <div class="table">
    <DataTable
      v-model:selection="selectedService"
      :value="services"
      dataKey="id"
      tableStyle="min-width: 50rem"
    >
      <Column selectionMode="single" headerStyle="width: 3rem"></Column>
      <Column field="date" header="Date"></Column>
      <Column field="time" header="Time"></Column>
      <Column field="garage" header="Garage"></Column>
    </DataTable>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { ServiceAppointment } from '@/service/ServiceAppointment';

onMounted(() => {
  ServiceAppointment.getServices().then((data) => (services.value = data));
});

const services = ref();
const selectedService = ref();
const metaKey = ref(true);
</script>
