<template>

    <!-- Edit Offered services -->
    <div class="page">
        <div class="container" v-if="showOfferedServicesEdit" id="OfferedServicesEdit">
            <!-- Header -->
            <div class="card-header">
                <h2>Offered Services:</h2> 
            </div>
            <br />
            
            <!-- Table for the offered services -->
            <el-table :data="offeredServices" height="500" style="width: 100%" @row-click="handleOfferedServiceRowClick" :highlight-current-row="true">
                <el-table-column prop="offeredServiceDescription" label="Description" style="width: 100%" />
                <el-table-column prop="offeredServiceDuration" label="Duration (minutes)" style="width: 100%" />
                <el-table-column prop="offeredServiceCost" label="Cost ($)" style="width: 100%" />
            </el-table>
            <br/>

            <!-- Alert to choose an offered service -->
            <el-alert v-if="showErrorEditOfferedService || showErrorDeleteOfferedService" title="Please choose an offered service." type="error" :closable="false" show-icon />
            <br/>

            <!-- Confirmation message for add offered service -->
            <el-alert v-if="showConfirmationAddOfferedService" title="Offered service added." type="success" :closable="true" @close="closeConfirmation" show-icon/>
            <br/>

            <!-- Confirmation message for edit offered service -->
            <el-alert v-if="showConfirmationEditOfferedService" title="Offered service edited." type="success" :closable="true" @close="closeConfirmation" show-icon/>
            <br/>

            <!-- Confirmation message for delete offered service-->
            <el-alert v-if="showConfirmationDeleteOfferedService" title="Offered service deleted." type="success" :closable="true" @close="closeConfirmation" show-icon/>
            <br/>

            <div class="button-container">
                <!-- Edit the offered service button -->
                <el-button type="primary" @click="editOfferedService">Edit Offered Service</el-button>
            
                <!-- Delete the offered service button -->
                <el-button type="danger" :icon="icons.deleteIcon" circle @click="deleteOfferedServiceSelected(deleteOfferedService)"></el-button>
            </div>
            <br />

            <!-- Add an offered service -->
            <el-button type="success" @click="addOfferedService">Add Offered Service</el-button>
        </div>
        <br />

        <!-- Edit Garages -->
        <div class="container" v-if="showGaragesEdit" id="GaragesEdit">
            <!-- Header -->
            <div class="card-header">
                <h2>Garages:</h2> 
            </div> 
            <br />
    
            <!-- Table for the garages -->
            <el-table :data="garages" highlight-current-row height="250" style="width: 100%" @row-click="handleGarageRowClick">
                <el-table-column prop="garageNumber" label="Garage number" style="width: 100%" />
            </el-table>
            <br/>

            <!-- Alert to choose a garage -->
            <el-alert v-if="showErrorEditGarage || showErrorDeleteGarage" title="Please choose a garage." type="error" :closable="false" show-icon />
            
            <br/>
    
            <!-- Confirmation message for add offered service -->
            <el-alert v-if="showConfirmationAddGarage" title="Garage added." type="success" :closable="true" @close="closeConfirmation" show-icon/>
            <br/>

            <!-- Confirmation message for edit offered service -->
            <el-alert v-if="showConfirmationEditGarage" title="Garage edited." type="success" :closable="true" @close="closeConfirmation" show-icon/>
            <br/>

            <!-- Confirmation message for delete offered service-->
            <el-alert v-if="showConfirmationDeleteGarage" title="Garage deleted." type="success" :closable="true" @close="closeConfirmation" show-icon/>
            <br/>

            <div class="button-container">
                <!-- Edit a garage button -->
                <el-button type="primary" @click="editGarage">Edit Garage</el-button>
    
                <!-- Delete a garage button -->
                <el-button type="danger" :icon="icons.deleteIcon" circle @click="deleteGarageSelected(deleteGarage)"></el-button>
            </div>
            <br />
    
            <!-- Add a garage -->
            <el-button type="success" @click="addGarage">Add Garage</el-button>
        </div> 

        <!-- Edit Offered services -->
        <div class="container" v-if="showOfferedServiceEdit" id="OfferedServiceEdit">
            <!-- Header -->
            <div class="card-header">
                <h2>Edit an Offered Service:</h2> <br />
            </div>
            <br />
            
            <!-- Edit the offered service's description -->
            <div>
                <span>Change Description: </span>
                <el-input v-model="editOfferedServiceDescription" placeholder="Enter description"></el-input>
            </div>
            <br />
            
            <!-- Edit the offered service's duration -->
            <div>
                <span>Change Duration (minutes): </span>
                <el-input v-model="editOfferedServiceDuration" placeholder="Enter duration"></el-input>
            </div>
            <br />

            <!-- Edit the offered service's cost -->
            <div>
                <span>Change Cost ($): </span>
                <el-input v-model="editOfferedServiceCost" placeholder="Enter cost"></el-input>
            </div>
            <br />

            <!-- Alert for the offered service edit -->
            <el-alert v-if="showErrorSaveEditOfferedService" :title="`Error: ${errorOfferedService}`" type="error" :closable="false" show-icon />
            <br/>
            
            <div>
                <!-- Go back button -->
                <el-button type="warning" :icon="icons.arrowLeftIcon" @click="goBack">Go back</el-button>

                <!-- Save the edited offered service -->
                <el-button type="success" @click="saveEditOfferedService(editOfferedServiceSelected, editOfferedServiceDescription, editOfferedServiceDuration, editOfferedServiceCost)">Save Offered Service</el-button>
            </div>
        </div>
        <br />

        <!-- Edit garages -->
        <div class="container" v-if="showGarageEdit" id="GarageEdit">
            <!-- Header -->
            <div class="card-header">
                <h2>Edit a Garage:</h2> <br />
            </div>
            <br />
            
            <!-- Edit the garage's number-->
            <div>
                <span>Change Garage Number: </span>
                <el-input v-model="editGarageGarageNumber" placeholder="Enter garage number"></el-input> 
            </div>
            <br />

            <!-- Alert for the offered service edit -->
            <el-alert v-if="showErrorSaveEditGarage" :title="`Error: ${errorGarage}`" type="error" :closable="false" show-icon />
            <br/>
            
            <div>
                <!-- Go back button -->
                <el-button type="warning" :icon="icons.arrowLeftIcon" @click="goBack">Go back</el-button>

                <!-- Save the edited garage -->
                <el-button type="success" @click="saveEditGarage(editGarageSelected, editGarageGarageNumber)">Save Garage</el-button>
            </div>
        </div>
        <br />    

        <!-- Add Offered services -->
        <div class="container" v-if="showOfferedServiceAdd" id="OfferedServiceAdd">
            <!-- Header -->
            <div class="card-header">
                <h2>Add an Offered Service:</h2> <br />
            </div>
            <br />

            <!-- Add the offered service's description -->
            <div>
                <el-row :gutter="20">
                    <span>Add Description: </span>
                    <el-input v-model="newOfferedServiceDescription" placeholder="Enter description"></el-input>
                </el-row>
            </div>
            
            <!-- Add the offered service's duration -->
            <div>
                <span>Add Duration (minutes): </span>
                <el-input v-model="newOfferedServiceDuration" placeholder="Enter duration"></el-input>
            </div>

            <!-- Add the offered service's cost -->
            <div>
                <span>Add Cost ($): </span>
                <el-input v-model="newOfferedServiceCost" placeholder="Enter cost"></el-input>
            </div>
            <br />

            <!-- Alert for the offered service edit -->
            <el-alert v-if="showErrorSaveAddOfferedService" :title="`Error: ${errorOfferedService}`" type="error" :closable="false" show-icon />
            <br/>
            
            <div>
                <!-- Go back button -->
                <el-button type="warning" :icon="icons.arrowLeftIcon" @click="goBack">Go back</el-button>

                <!-- Add the offered service button -->
                <el-button type="success" @click="saveAddOfferedService(newOfferedServiceDescription, newOfferedServiceDuration, newOfferedServiceCost)">Add Offered Service</el-button>
            </div>
        </div>
        <br />

        <!-- Add garages -->
        <div class="container" v-if="showGarageAdd" id="GarageAdd">
            <!-- Header -->
            <div class="card-header">
                <h2>Add a Garage:</h2> <br />
            </div>
            <br />

            <!-- Add the garage's number-->
            <div>
                <span>Add Garage Number: </span>
                <el-input v-model="newGarageGarageNumber" placeholder="Enter garage number"></el-input> 
            </div>
            <br />

            <!-- Alert for the garage edit -->
            <el-alert v-if="showErrorSaveAddGarage" :title="`Error: ${errorGarage}`" type="error" :closable="false" show-icon />
            <br/>
            
            <div>
                <!-- Go back button -->
                <el-button type="warning" :icon="icons.arrowLeftIcon" @click="goBack">Go back</el-button>
            
                <!-- Add the garage button -->
                <el-button type="success" @click="saveAddGarage(newGarageGarageNumber)">Add Garage</el-button>
            </div>
        </div>
    </div>

</template>

<script src="./ServicesAndGarages.js"></script>

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
