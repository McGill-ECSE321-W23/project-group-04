<template>
    <el-form :model="form" label-width="120px">
      <el-form-item label="Customer name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Reservation time">
        <el-col :span="11">
          <el-date-picker
              v-model="form.date1"
              type="date"
              placeholder="Pick a date"
              style="width: 100%"
          />
        </el-col>
        <el-col :span="2" class="text-center">
          <span class="text-gray-500">-</span>
        </el-col>
        <el-col :span="11">
          <el-time-picker
              v-model="form.date2"
              placeholder="Pick a time"
              style="width: 100%"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="Reservation type">
        <el-checkbox-group v-model="form.type">
          <el-checkbox label="Monthly" name="type" />
          <el-checkbox label="Temporary" name="type" />
        </el-checkbox-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Search</el-button>
        <el-button>Reset</el-button>
      </el-form-item>
    </el-form>
  <el-divider />

  <el-table :data="tableData">
    <el-table-column prop="name" label="Name" width="180" />
    <el-table-column prop="date" label="Date" width="180" />
    <el-table-column prop="address" label="Location" />
    <el-table-column prop="address" label="Actions">
      <template #default>
        <el-button link type="primary" size="small" @click="handleClick"
        >Reassign</el-button
        >
        <el-button link type="primary" size="small">Edit</el-button>
        <el-button link type="primary" size="small">Delete</el-button>
      </template>
    </el-table-column>

  </el-table>
</template>

<script setup>
import { reactive } from 'vue'

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

const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'F1-35',
  },
  {
    date: '2016-05-02',
    name: 'Jerry',
    address: 'F2-43',
  }
]

const onSubmit = () => {
  console.log('submit!')
}
</script>

<style scoped>

</style>