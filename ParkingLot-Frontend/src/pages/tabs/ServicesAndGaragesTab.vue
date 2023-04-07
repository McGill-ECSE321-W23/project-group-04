<template>

    <!-- Edit Offered services -->
    <el-card class="container" style="display:block;" id="OfferedServicesEdit">
        <!-- Header -->
        <div class="card-header">
            <h2>Offered Services:</h2> 
        </div>
        <br />

        <!-- Container for the individual offered services -->
        <div>
            <el-space wrap class="button" style="text-align: center;">
                <el-card v-for="item in offeredServicesAvailable" :key="item.id" class="box-card" style="width: 250px">
                    <!-- Show the offered service -->
                    <div class="text item">
                        {{ 'Description: ' + item.description }} <br />
                        {{ 'Duration: ' + item.duration }} <br />
                        {{ 'Cost: ' + item.cost }} <br />
                    </div>
                    <br />

                    <!-- Edit the offered service button -->
                    <el-button type="primary" id="edit_OfferedService_button">Edit Offered Service</el-button>
                    
                    <!-- Delete the offered service button -->
                    <el-button type="danger" :icon="Delete" circle id="delete_OfferedService_button" />
                </el-card>
            </el-space>
        </div>
        <br />

        <!-- Add an offered service -->
        <el-button type="success" id="add_OfferedService_button">Add Offered Service</el-button>
    </el-card>
    <br />

    <!-- Edit Garages -->
    <el-card class="container" style="display:block;" id="GaragesEdit">
        <!-- Header -->
        <div class="card-header">
            <h2>Garages:</h2> 
        </div> 
        <br />

        <!-- Container for the individual garages -->
        <div>
            <el-space wrap class="button" style="text-align: center;">
                <el-card v-for="item in garages" :key="item.id" class="box-card" style="width: 250px">
                    <!-- Show the garage -->
                    <div class="text item">
                        {{ 'Garage number: ' + item.garageNumber }} <br />
                    </div>
                    <br />
                    
                    <!-- Edit a garage button -->
                    <el-button type="primary" id="edit_garage_button">Edit Garage</el-button>

                    <!-- Delete a garage button -->
                    <el-button type="danger" :icon="Delete" circle @click="deleteGarage" />
                </el-card>
            </el-space>
        </div>
        <br />

        <!-- Add a garage -->
        <el-button type="success">Add Garage</el-button>
    </el-card>

    <!-- Create Offered services -->
    <el-card class="container" style="display:none;" id="OfferedServiceAdd">
        <!-- Header -->
        <div class="card-header">
            <h2>Create an Offered Service:</h2> <br />
        </div>
        <br />

        <!-- Add the offered service's description -->
        <div>
            <span>Add Description: </span>
            <el-input v-model="descriptionInput" placeholder="Enter description"></el-input>
        </div>
        <br />
        
        <!-- Add the offered service's duration -->
        <div>
            <span>Add Duration: </span>
            <el-input v-model="durationInput" placeholder="Enter duration"></el-input>
        </div>
        <br />

        <!-- Add the offered service's cost -->
        <div>
            <span>Add Cost: </span>
            <el-input v-model="costInput" placeholder="Enter cost"></el-input>
        </div>
        <br />
        
        <!-- Add the offered service button -->
        <el-button type="success" id="create_offered_service_button">Add Offered Service</el-button>
    </el-card>
    <br />

    <!-- Create garages -->
    <el-card class="container" style="display:none;" id="GarageAdd">
        <!-- Header -->
        <div class="card-header">
            <h2>Create a Garage:</h2> <br />
        </div>
        <br />

        <!-- Add the garage's number-->
        <div>
            <span>Add Garage Number: </span>
            <el-input v-model="garageNumberInput" placeholder="Enter garage number"></el-input> 
        </div>
        <br />
                
        <!-- Add the garage button -->
        <el-button type="success" id="create_garage_button">Add Garage</el-button>
    </el-card>
    <br />

    <!-- Edit Offered services -->
    <el-card class="container" style="display:none;" id="OfferedServiceEdit">
        <!-- Header -->
        <div class="card-header">
            <h2>Edit an Offered Service:</h2> <br />
        </div>
        <br />
        
        <!-- Edit the offered service's description -->
        <div>
            <span>Change Description: </span>
            <el-input v-model="descriptionEdit" placeholder="Enter description"></el-input>
        </div>
        <br />
        
        <!-- Edit the offered service's duration -->
        <div>
            <span>Change Duration: </span>
            <el-input v-model="durationEdit" placeholder="Enter duration"></el-input>
        </div>
        <br />

        <!-- Edit the offered service's cost -->
        <div>
            <span>Change Cost: </span>
            <el-input v-model="costEdit" placeholder="Enter cost"></el-input>
        </div>
        <br />
        
        <!-- Save the edited offered service -->
        <el-button type="warning" id="edit_offered_service_button">Save Offered Service</el-button>
    </el-card>
    <br />

    <!-- Edit garages -->
    <el-card class="container" style="display:none;" id="GarageEdit">
        <!-- Header -->
        <div class="card-header">
            <h2>Edit a Garage:</h2> <br />
        </div>
        <br />
        
        <!-- Edit the garage's number-->
        <div>
            <span>Change Garage Number: </span>
            <el-input v-model="garageNumberEdit" placeholder="Enter garage number"></el-input> 
        </div>
        <br />
                
        <!-- Add an offered service -->
        <el-button type="warning" id="edit_garage_button">Save Garage</el-button>
    </el-card>
    <br />

</template>

<script setup>

import { offeredServicesAvailable, garages } from './ServicesAndGarages.js'
import { reactive, ref } from 'vue'
import { Delete } from '@element-plus/icons-vue'
import $ from 'jquery'

// Initialize the constants for the inputs
const descriptionInput = ref('')
const durationInput = ref('')
const costInput = ref('')
const garageNumberInput = ref('')
const descriptionEdit = ref('')
const durationEdit = ref('')
const costEdit = ref('')
const garageNumberEdit = ref('')

// Only show the offered services and garages to edit when opening the tab
$(document).ready(function() {
    $('#OfferedServicesEdit').show();
    $('#GaragesEdit').show();
    $('#OfferedServiceAdd').hide();
    $('#GarageAdd').hide();
    $('#OfferedServiceEdit').hide();
    $('#GarageEdit').hide();
});


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
