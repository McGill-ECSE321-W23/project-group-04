<template>
    <!-- Edit Offered services -->
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
            <a href="#" id="edit_OfferedService">
                <el-button type="primary">Edit Offered Service</el-button>
            </a>
            <!-- Delete an offered service -->
            <a href="#" id="delete_OfferedService">
                <el-button type="danger" :icon="Delete" circle />
            </a>
            </el-card>
        </el-space>
    </div>
    <br />
    <!-- Add an offered service -->
    <a href="#" id="add_OfferedService">
        <el-button type="success">Add Offered Service</el-button>
    </a>
        </el-card>
    <br />

    <!-- Edit Garages -->
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
            <a key="item.id" :href="'/edit_Garage/' + item.id">
                <el-button type="primary">Edit Garage</el-button>
            </a>
            <!-- Delete a garage -->
            <el-button type="danger" :icon="Delete" circle @click="deleteGarage" />
            </el-card>
        </el-space>
    </div>
    <br />
    <!-- Add a garage -->
    <a href="/add_garage">
        <el-button type="success">Add Garage</el-button>
    </a>
    </el-card>
    <br />
    <br />
    <br />
    <br />
    <br />
        <!-- Create Offered services -->
        <el-card class="container" id="OfferedServiceAdd">
            <div>
            <div class="card-header">
                <h2>Create an Offered Service:</h2> <br />
            </div>
            </div>
            <br />
            <div>
                <span>Add Description: </span>
                <el-input v-model="descriptionInput" placeholder="Enter description"></el-input>
            </div>
            <br />
            <div>
                <span>Add Duration: </span>
                <el-input v-model="durationInput" placeholder="Enter duration"></el-input>
            </div>
            <br />
            <div>
                <span>Add Cost: </span>
                <el-input v-model="costInput" placeholder="Enter cost"></el-input>
            </div>
                <br />
            <!-- Add an offered service -->
            <a href="#" id="createOfferedService">
            <el-button type="success">Add Offered Service</el-button>
            </a>
            </el-card>
            <br />

                    <!-- Create garages -->
        <el-card class="container" id="GarageAdd">
            <div>
            <div class="card-header">
                <h2>Create a Garage:</h2> <br />
            </div>
            </div>
            <br />
            <div>
                <span>Add Garage Number: </span>
                <el-input v-model="garageNumberInput" placeholder="Enter garage number"></el-input> 
            </div>
            <br />
            <!-- Add an offered service -->
            <a href="#" id="createGarage">
            <el-button type="success">Add Garage</el-button>
            </a>
            </el-card>
            <br />

                <!-- Edit Offered services -->
        <el-card class="container" id="OfferedServiceEdit">
            <div>
            <div class="card-header">
                <h2>Edit an Offered Service:</h2> <br />
            </div>
            </div>
            <br />
            <div>
                <span>Change Description: </span>
                <el-input v-model="descriptionEdit" placeholder="Enter description"></el-input>
            </div>
            <br />
            <div>
                <span>Change Duration: </span>
                <el-input v-model="durationEdit" placeholder="Enter duration"></el-input>
            </div>
            <br />
            <div>
                <span>Change Cost: </span>
                <el-input v-model="costEdit" placeholder="Enter cost"></el-input>
            </div>
                <br />
            <!-- Add an offered service -->
            <a href="#" id="editOfferedService">
            <el-button type="warning">Save Offered Service</el-button>
            </a>
            </el-card>
            <br />

        <!-- Edit garages -->
        <el-card class="container" id="GarageEdit">
            <div>
            <div class="card-header">
                <h2>Edit a Garage:</h2> <br />
            </div>
            </div>
            <br />
            <div>
                <span>Change Garage Number: </span>
                <el-input v-model="garageNumberEdit" placeholder="Enter garage number"></el-input> 
            </div>
            <br />
            <!-- Add an offered service -->
            <a href="#" id="editGarage">
            <el-button type="warning">Save Garage</el-button>
            </a>
            </el-card>
            <br />

</template>

<script setup>
import { offeredServicesAvailable, garages } from './ServicesAndGarages.js'
import { reactive, ref } from 'vue'
import { Delete } from '@element-plus/icons-vue'

const descriptionInput = ref('')
const durationInput = ref('')
const costInput = ref('')
const garageNumberInput = ref('')
const descriptionEdit = ref('')
const durationEdit = ref('')
const costEdit = ref('')
const garageNumberEdit = ref('')

//   function addOfferedService() {
//     // Generate a unique ID for the new offered service
//     const id = Math.max(...offeredServicesAvailable.map(item => item.id)) + 1

//     // Add the new offered service to the list
//     offeredServicesAvailable.push({
//       id,
//       description: state.newOfferedService.description,
//       duration: state.newOfferedService.duration,
//       cost: state.newOfferedService.cost
//     })

</script>

<style scoped>
.card-header {
    text-align: center;
}

.button {
    display: flex;
    align-items: center;
    justify-content: center;
}

.container {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
