<!-- Select the service to view the availabilities -->
<template>
  <div class="card flex justify-content-center">
    <h2>Services</h2>
    <form @submit="onSubmit" class="flex flex-column gap-2">
      <span class="p-float-label">
        <Dropdown
          id="serviceAppointments"
          v-model="value"
          :options="services"
          optionLabel="description"
          :class="['w-full md:w-14rem', { 'p-invalid': errorMessage }]"
          aria-describedby="serviceAppointments-error"
        />
        <label for="serviceAppointments">Select a Service</label>
      </span>
      <small class="p-error" id="serviceAppointments-error">{{
        errorMessage || '&nbsp;'
      }}</small>
      <Button type="submit" label="Select" />
    </form>
    <Toast />
  </div>
</template>

<script>
import { useToast } from 'primevue/usetoast';
import { useField, useForm } from 'vee-validate';

export default {
  setup() {
    const { handleSubmit, resetForm } = useForm();
    const { value, errorMessage } = useField('value', validateField);
    const toast = useToast();

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
          summary: 'Form Submitted',
          detail: values.value.description,
          life: 3000,
        });
        resetForm();
      }
    });

    return { value, handleSubmit, onSubmit, errorMessage };
  },
  data() {
    return {
      services: [
        { description: 'Change Tires', code: 'CT' },
        { description: 'Clean Car', code: 'CC' },
      ],
    };
  },
};
</script>
