<template>

    <!-- Edit Offered services -->
    <div class="page">
        <div class="container" id="OfferedServicesEdit">
            <!-- Header -->
            <div class="card-header">
                <h2>Offered Services:</h2> 
            </div>
            <br />
            
            <!-- Table for the offered services -->
            <el-table :data="offeredServicesAvailable" height="500" style="width: 100%" @row-click="handleOfferedServiceRowClick" :highlight-current-row="true">
                <el-table-column prop="description" label="Description" style="width: 100%" />
                <el-table-column prop="duration" label="Duration (minutes)" style="width: 100%" />
                <el-table-column prop="cost" label="Cost ($)" style="width: 100%" />
            </el-table>
            <br/>

            <div class="button-container">
                <!-- Edit the offered service button -->
                <el-button type="primary" id="edit_offered_service_button" >Edit Offered Service</el-button>
                        
                <!-- Delete the offered service button -->
                <el-button type="danger" :icon="Delete" circle id="delete_offered_service_button" />
            </div>
            <br />

            <!-- Add an offered service -->
            <el-button type="success" @click="addAnOfferedService" id="add_offered_service_button">Add Offered Service</el-button>
        </div>
    </div>
    <br />

    <!-- Edit Garages -->
    <div class="page">
        <div class="container" id="GaragesEdit">
            <!-- Header -->
            <div class="card-header">
                <h2>Garages:</h2> 
            </div> 
            <br />

            <!-- Table for the garages -->
            <el-table :data="garages" highlight-current-row height="250" style="width: 100%" id="garageTable" @row-click="handleGarageRowClick">
                <el-table-column prop="garageNumber" label="Garage number" style="width: 100%" />
            </el-table>
            <br/>

            <div class="button-container">
                <!-- Edit a garage button -->
                <el-button type="primary" id="edit_garage_button">Edit Garage</el-button>

                <!-- Delete a garage button -->
                <el-button type="danger" :icon="Delete" circle @click="deleteGarage" id="delete_garage_button" />
            </div>
            <br />

            <!-- Add a garage -->
            <el-button type="success" id="add_garage_button">Add Garage</el-button>
        </div> 
    </div> 

    <!-- Create Offered services -->
    <div class="page">
        <div class="container" id="OfferedServiceAdd">
            <!-- Header -->
            <div class="card-header">
                <h2>Create an Offered Service:</h2> <br />
            </div>
            <br />

            <!-- Add the offered service's description -->
            <div>
                <el-row :gutter="20">
                    <span>Add Description: </span>
                    <el-input v-model="descriptionInput" placeholder="Enter description"></el-input>
                </el-row>
            </div>
            
            <!-- Add the offered service's duration -->
            <div>
                <span>Add Duration: </span>
                <el-input v-model="durationInput" placeholder="Enter duration"></el-input>
            </div>

            <!-- Add the offered service's cost -->
            <div>
                <span>Add Cost: </span>
                <el-input v-model="costInput" placeholder="Enter cost"></el-input>
            </div>
            <br />
            
            <div>
                <!-- Go back button -->
                <el-button type="warning" :icon="ArrowLeft" plain id="go_back_button_1"> Go back</el-button>

                <!-- Add the offered service button -->
                <el-button type="success" id="save_add_offered_service_button">Add Offered Service</el-button>
            </div>
        </div>
    </div>
    <br />

    <!-- Create garages -->
    <div class="page">
        <div class="container" id="GarageAdd">
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
            
            <div>
                <!-- Go back button -->
                <el-button type="warning" :icon="ArrowLeft" plain id="go_back_button_2"> Go back</el-button>
            
                <!-- Add the garage button -->
                <el-button type="success" id="save_add_garage_button">Add Garage</el-button>
            </div>
        </div>
    </div>
    <br />

    <!-- Edit Offered services -->
    <div class="page">
        <div class="container" id="OfferedServiceEdit">
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
            
            <div>
                <!-- Go back button -->
                <el-button type="warning" :icon="ArrowLeft" plain id="go_back_button_3"> Go back</el-button>

                <!-- Save the edited offered service -->
                <el-button type="success" id="save_edit_offered_service_button">Save Offered Service</el-button>
            </div>
        </div>
    </div>
    <br />

    <!-- Edit garages -->
    <div class="page">
        <div class="container" id="GarageEdit">
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
            
            <div>
                <!-- Go back button -->
                <el-button type="warning" :icon="ArrowLeft" plain id="go_back_button_4"> Go back</el-button>

                <!-- Save the edited garage -->
                <el-button type="success" id="save_edit_garage_button">Save Garage</el-button>
            </div>
        </div>
    </div>
    <br />

</template>

<script setup>

    import { offeredServicesAvailable, garages } from './ServicesAndGarages.js'
    import { reactive, ref } from 'vue'
    import { Delete, ArrowLeft } from '@element-plus/icons-vue'
    import $ from 'jquery'

    // Input boxes
    let descriptionInput = ref('')
    let durationInput = ref('')
    let costInput = ref('')
    let garageNumberInput = ref('')

    let descriptionEdit = ref('')
    let durationEdit = ref('')
    let costEdit = ref('')
    let garageNumberEdit = ref('')

    let selectedOfferedServiceRow = null;
    let selectedGarageRow = null;


    function handleOfferedServiceRowClick(row) {
        selectedOfferedServiceRow = row;
        descriptionEdit.value = row.description;
        durationEdit.value = row.duration;
        costEdit.value = row.cost;
    };

    function handleGarageRowClick(row) {
        selectedGarageRow = row;
        garageNumberEdit.value = row.garageNumber;
    };

    // Only show the offered services and garages to edit when opening the tab
    $(document).ready(function() {
        $('#OfferedServicesEdit').show();
        $('#GaragesEdit').show();
        $('#OfferedServiceAdd').hide();
        $('#GarageAdd').hide();
        $('#OfferedServiceEdit').hide();
        $('#GarageEdit').hide();

        // Only show the edit offered service section
        $('#edit_offered_service_button').click(function() {
            if (selectedOfferedServiceRow) {
                $('#OfferedServicesEdit').hide();
                $('#GaragesEdit').hide();
                $('#OfferedServiceAdd').hide();
                $('#GarageAdd').hide();
                $('#OfferedServiceEdit').show();
                $('#GarageEdit').hide();
            }
        });

        // Go back to the edit section when in the edit offered service section
        $('#go_back_button_3').click(function() {
            $('#OfferedServicesEdit').show();
            $('#GaragesEdit').show();
            $('#OfferedServiceAdd').hide();
            $('#GarageAdd').hide();
            $('#OfferedServiceEdit').hide();
            $('#GarageEdit').hide();

            descriptionEdit.value = '';
            durationEdit.value = '';
            costEdit.value = '';

            selectedOfferedServiceRow = null;
            selectedGarageRow = null;
        });

        // Only show the edit offered service section
        $('#save_edit_offered_service_button').click(function() {
            if (descriptionEdit.value !== '' && durationEdit.value !== '' && costEdit.value !== '') {
                $('#OfferedServicesEdit').show();
                $('#GaragesEdit').show();
                $('#OfferedServiceAdd').hide();
                $('#GarageAdd').hide();
                $('#OfferedServiceEdit').hide();
                $('#GarageEdit').hide();

                descriptionEdit.value = '';
                durationEdit.value = '';
                costEdit.value = '';

                selectedOfferedServiceRow = null;
                selectedGarageRow = null;
            }
        });

        // Only show the edit offered service section
        $('#edit_garage_button').click(function() {
            if (selectedGarageRow) {
                $('#OfferedServicesEdit').hide();
                $('#GaragesEdit').hide();
                $('#OfferedServiceAdd').hide();
                $('#GarageAdd').hide();
                $('#OfferedServiceEdit').hide();
                $('#GarageEdit').show();
            }
        });

        // Go back to the edit section when in the edit garage section
        $('#go_back_button_4').click(function() {
            $('#OfferedServicesEdit').show();
            $('#GaragesEdit').show();
            $('#OfferedServiceAdd').hide();
            $('#GarageAdd').hide();
            $('#OfferedServiceEdit').hide();
            $('#GarageEdit').hide();

            garageNumberEdit.value = '';

            selectedOfferedServiceRow = null;
            selectedGarageRow = null;
        });

        // Only show the edit offered service section
        $('#save_edit_garage_button').click(function() {
            if (garageNumberEdit.value !== '') {
                $('#OfferedServicesEdit').show();
                $('#GaragesEdit').show();
                $('#OfferedServiceAdd').hide();
                $('#GarageAdd').hide();
                $('#OfferedServiceEdit').hide();
                $('#GarageEdit').hide();

                garageNumberEdit.value = '';

                selectedOfferedServiceRow = null;
                selectedGarageRow = null;
            }
        });

        // Only show the add offered service section
        $('#add_offered_service_button').click(function() {
            $('#OfferedServicesEdit').hide();
            $('#GaragesEdit').hide();
            $('#OfferedServiceAdd').show();
            $('#GarageAdd').hide();
            $('#OfferedServiceEdit').hide();
            $('#GarageEdit').hide();
        });

        // Go back to the edit section when in add offered service
        $('#go_back_button_1').click(function() {
            $('#OfferedServicesEdit').show();
            $('#GaragesEdit').show();
            $('#OfferedServiceAdd').hide();
            $('#GarageAdd').hide();
            $('#OfferedServiceEdit').hide();
            $('#GarageEdit').hide();

            descriptionInput.value = '';
            durationInput.value = '';
            costInput.value = '';

            selectedOfferedServiceRow = null;
            selectedGarageRow = null;
        });

        // Save the add offered service
        $('#save_add_offered_service_button').click(function() {
            if (descriptionInput.value !== '' && durationInput.value !== '' && costInput.value !== '') {
                $('#OfferedServicesEdit').show();
                $('#GaragesEdit').show();
                $('#OfferedServiceAdd').hide();
                $('#GarageAdd').hide();
                $('#OfferedServiceEdit').hide();
                $('#GarageEdit').hide();

                descriptionInput.value = '';
                durationInput.value = '';
                costInput.value = '';

                selectedOfferedServiceRow = null;
                selectedGarageRow = null;
            }
        });

        // Only show the add garage section
        $('#add_garage_button').click(function() {
            $('#OfferedServicesEdit').hide();
            $('#GaragesEdit').hide();
            $('#OfferedServiceAdd').hide();
            $('#GarageAdd').show();
            $('#OfferedServiceEdit').hide();
            $('#GarageEdit').hide();
        });

        // Go back to the edit section when in the add garage section
        $('#go_back_button_2').click(function() {
            $('#OfferedServicesEdit').show();
            $('#GaragesEdit').show();
            $('#OfferedServiceAdd').hide();
            $('#GarageAdd').hide();
            $('#OfferedServiceEdit').hide();
            $('#GarageEdit').hide();

            garageNumberInput.value = '';

            selectedOfferedServiceRow = null;
            selectedGarageRow = null;
        });

        // Save the add garage
        $('#save_add_garage_button').click(function() {
            if (garageNumberInput.value !== '') {
                $('#OfferedServicesEdit').show();
                $('#GaragesEdit').show();
                $('#OfferedServiceAdd').hide();
                $('#GarageAdd').hide();
                $('#OfferedServiceEdit').hide();
                $('#GarageEdit').hide();

                garageNumberInput.value = '';

                selectedOfferedServiceRow = null;
                selectedGarageRow = null;
            }
        });
    });

</script>

<style scoped>
    #OfferedServicesEdit {
        background-color: white;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        padding: 20px 30px;
        border-radius: 10px;
        box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.5);
    }

    #GaragesEdit {
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

    #OfferedServiceAdd {
        max-width: 1000px;
        margin-left: auto;
        margin-right: auto;
        background-color: white;
        display: flex;
        flex-direction: column;
        padding: 20px 30px;
        border-radius: 10px;
        box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.5);
    }

    #GarageAdd {
        max-width: 300px;
        margin-left: auto;
        margin-right: auto;
        background-color: white;
        display: flex;
        flex-direction: column;
        padding: 20px 30px;
        border-radius: 10px;
        box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.5);
    }

    #OfferedServiceEdit {
        max-width: 1000px;
        margin-left: auto;
        margin-right: auto;
        background-color: white;
        display: flex;
        flex-direction: column;
        padding: 20px 30px;
        border-radius: 10px;
        box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.5);
    }

    #GarageEdit {
        max-width: 1000px;
        margin-left: auto;
        margin-right: auto;
        background-color: white;
        display: flex;
        flex-direction: column;
        padding: 20px 30px;
        border-radius: 10px;
        box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.5);
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
