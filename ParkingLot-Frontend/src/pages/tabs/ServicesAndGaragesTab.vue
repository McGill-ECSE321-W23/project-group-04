<template>
    <!-- Edit the offered services -->
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
                <el-button type="primary" @click="editOfferedService">Edit Offered Service</el-button>
                <!-- Delete an offered service -->
                <el-button type="danger" :icon="Delete" circle @click="deleteOfferedService" />
            </el-card>
        </el-space>
    </div>
    <br />
    <!-- Add an offered service -->
    <el-button type="success" @click="addOfferedService">Add Offered Service</el-button>
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
                    <el-button type="primary" @click="editGarage">Edit Garage</el-button>
                    <!-- Delete a garage -->
                    <el-button type="danger" :icon="Delete" circle @click="deleteGarage" />
                </el-card>
            </el-space>
        </div>
        <br />
        <!-- Add a garage -->
        <el-button type="success" @click="addGarage">Add Garage</el-button>
    </el-card>
</template>
      

<script setup>
import { offeredServicesAvailable, garages } from './ServicesAndGarages.js'
import { reactive } from 'vue'
import { Delete } from '@element-plus/icons-vue'
</script>


<style scoped>
.card-header {
    text-align: center;
};

.button {
    display: flex;
    align-items: center;
    justify-content: center;
};

.container {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>