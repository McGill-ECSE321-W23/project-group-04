<template>
    <div class="container">
        <h1>System</h1>
    </div>
    <br />
    <div class="container">
        <label style="padding-right: 10px">Select a system:</label>
        <el-select v-model="system_selected" placeholder="Select" style="width: 300px" @change="refreshTable()">
            <el-option v-for="item in system_list" :key="item.systemID" :label="item.label"
                :value="item.systemID">
            </el-option>
        </el-select>
    </div>
    <br />
    <div class="container">
        <table>
            <tr>
                <td style="padding-right: 10px">
                    <el-card style="min-width: 400px;" shadow="hover">
                        <label>Current parking lot specifications</label>
                        <table style="padding-top: 5px;">
                            <tr>
                                <td>Monthly fee</td>
                                <td id="monthlyFeeCell">-</td>
                            </tr>
                            <tr>
                                <td>Fee per 15 mins</td>
                                <td id="feePer15MinsCell">-</td>
                            </tr>
                            <tr>
                                <td>Maximum stay duration</td>
                                <td id="maxStayCell">-</td>
                            </tr>
                            <tr>
                                <td>Number of regular parking spots</td>
                                <td id="numRegularCell">-</td>
                            </tr>
                            <tr>
                                <td>Number of large parking spots</td>
                                <td id="numLargeCell">-</td>
                            </tr>
                            <tr>
                                <td>Number of floors for monthly users</td>
                                <td id="numMonthlyFloorsCell">-</td>
                            </tr>
                            <tr>
                                <td style="padding-right: 10px">
                                    Number of parking spots for monthly users per floor
                                </td>
                                <td id="numMonthlySpotsCell">-</td>
                            </tr>
                            <tr>
                                <td>Number of garages</td>
                                <td id="numGaragesCell">-</td>
                            </tr>
                        </table>
                    </el-card>
                </td>
                <td>
                    <el-card shadow="hover">
                        <label>Current open hours</label>
                        <table style="padding-top: 5px;">
                            <tr>
                                <td>Sunday</td>
                                <td style="text-align: center" id="sunOpen">
                                    -
                                </td>
                            </tr>
                            <tr>
                                <td>Monday</td>
                                <td style="text-align: center" id="monOpen">
                                    -
                                </td>
                            </tr>
                            <tr>
                                <td>Tuesday</td>
                                <td style="text-align: center" id="tueOpen">
                                    -
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-right: 30px">Wednesday</td>
                                <td style="text-align: center" id="wedOpen">
                                    -
                                </td>
                            </tr>
                            <tr>
                                <td>Thursday</td>
                                <td style="text-align: center" id="thuOpen">
                                    -
                                </td>
                            </tr>
                            <tr>
                                <td>Friday</td>
                                <td style="text-align: center" id="friOpen">
                                    -
                                </td>
                            </tr>
                            <tr>
                                <td>Saturday</td>
                                <td style="text-align: center" id="satOpen">
                                    -
                                </td>
                            </tr>
                        </table>
                    </el-card>
                </td>
            </tr>
        </table>
    </div>
    <div class="container" style="padding-top: 20px">
        <el-card shadow="hover">
            <label>Edit</label>
            <table style="padding-top: 10px;">
                <tr>
                    <td>
                        <label style="padding-right: 15px;">Parking lot specifications:</label>
                    </td>
                    <td>
                        <el-select v-model="parkingspecs_selected" placeholder="Select an option" style="width: 280px">
                            <el-option v-for="item in parkingspecs_options" :key="item.spec_value" :label="item.label"
                                :value="item.spec_value">
                            </el-option>
                        </el-select>
                    </td>
                    <td>
                        <el-input placeholder="Enter value" v-model="spec_input" style="width: 165px;" clearable></el-input>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td style="padding-bottom: 10px;">
                        <el-button @click="updateSystem(parkingspecs_selected,spec_input,system_selected)">Update</el-button>
                        <el-button @click="clearEditSpecs()">Cancel</el-button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Open hours:</label>
                    </td>
                    <td>
                        <el-select v-model="day_selected" placeholder="Select a day" style="width: 280px">
                            <el-option v-for="item in day_options" :key="item.day_value" :label="item.label"
                                :value="item.day_value">
                            </el-option>
                        </el-select>
                    </td>
                    <td>
                        <el-time-picker v-model="start_time" :picker-options="{
                            selectableRange: '00:00 - 23:59'
                        }" placeholder="Select start time" format="h:mm A"
                            style="width: 165px; padding-right: 4px;"></el-time-picker>
                        <el-time-picker v-model="end_time" :picker-options="{
                            selectableRange: '00:00 - 23:59'
                        }" placeholder="Select end time" format="h:mm A"
                            style="width: 165px"></el-time-picker>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <el-button @click="updateOpenHour(day_selected,start_time,end_time,system_selected)">Update</el-button>
                        <el-button @click="deleteOpenHour(day_selected,system_selected)">Delete</el-button>
                        <el-button @click="clearEditOpenHours()">Cancel</el-button>
                    </td>
                </tr>
            </table>
        </el-card>
    </div>
    <div class="container" style="padding-top: 20px; padding-bottom: 50px;">
        <el-card shadow="hover">
            <label>Create new system</label>
            <table style="padding-top: 10px;">
                <tr>
                    <td>
                        <label style="padding-right: 15px;">Monthly fee:</label>
                    </td>
                    <td>
                        <el-input placeholder="Enter value" v-model="create_monthlyfee" style="width: 280px;" clearable></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="padding-right: 15px;">Fee per 15 mins:</label>
                    </td>
                    <td>
                        <el-input placeholder="Enter value" v-model="create_feeper15mins" style="width: 280px;" clearable></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="padding-right: 15px;">Maximum stay duration:</label>
                    </td>
                    <td>
                        <el-input placeholder="Enter value" v-model="create_maxstay" style="width: 280px;" clearable></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="padding-right: 15px;">Number of regular parking spots</label>
                    </td>
                    <td>
                        <el-input placeholder="Enter value" v-model="create_numregular" style="width: 280px;" clearable></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="padding-right: 15px;">Number of large parking spots</label>
                    </td>
                    <td>
                        <el-input placeholder="Enter value" v-model="create_numlarge" style="width: 280px;" clearable></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="padding-right: 15px;">Number of floors for monthly users</label>
                    </td>
                    <td>
                        <el-input placeholder="Enter value" v-model="create_nummonthlyfloors" style="width: 280px;" clearable></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="padding-right: 15px;">Number of parking spots for monthly users</label>
                    </td>
                    <td>
                        <el-input placeholder="Enter value" v-model="create_nummonthlyspots" style="width: 280px;" clearable></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="padding-right: 15px;">Number of garages</label>
                    </td>
                    <td>
                        <el-input placeholder="Enter value" v-model="create_numgarages" style="width: 280px;" clearable></el-input>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td style="padding-top: 10px;">
                        <el-button @click="createSystem(create_monthlyfee,create_feeper15mins,create_maxstay,create_numregular,create_numlarge,create_nummonthlyfloors,create_nummonthlyspots,create_numgarages)">Create</el-button>
                        <el-button @click="clearCreateSystem()">Cancel</el-button>
                    </td>
                </tr>
            </table>
        </el-card>
    </div>
</template>

<script src="./SystemManagerScript.js"></script>

<style>
.container {
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>