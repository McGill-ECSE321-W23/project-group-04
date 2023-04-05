<template>
    <div class="container">
        <h1>Staff Schedule</h1>
    </div>
    <br />
    <div class="container">
        <label for="staffSelect" style="padding-right: 10px">Select a staff:</label>
        <el-select v-model="staff_value" placeholder="Select" style="width: 300px">
            <el-option v-for="item in staff_options" :key="item.staff_value" :label="item.label" :value="item.staff_value">
            </el-option>
        </el-select>
    </div>
    <div class="container">
        <table class="schedule_table">
            <tr>
                <th>Sunday</th>
                <th>Monday</th>
                <th>Tuesday</th>
                <th>Wednesday</th>
                <th>Thursday</th>
                <th>Friday</th>
                <th>Saturday</th>
            </tr>
            <tr>
                <td id="sun_schedule">5:00 AM - 9:00 AM</td>
                <td id="mon_schedule">5:00 AM - 9:00 AM</td>
                <td id="tue_schedule">5:00 AM - 9:00 AM</td>
                <td id="wed_schedule">5:00 AM - 9:00 AM</td>
                <td id="thu_schedule">5:00 AM - 9:00 AM</td>
                <td id="fri_schedule">5:00 AM - 9:00 AM</td>
                <td id="sat_schedule">5:00 AM - 9:00 AM</td>
            </tr>
        </table>
    </div>
    <div class="container">
        <table>
            <tr>
                <td id="card_column">
                    <el-card class="card" shadow="hover">
                        <div class="card_div">
                            <label style="font-size: 20px;">Create time slot</label>
                            <br><br>
                            <table class="table_in_card">
                                <tr>
                                    <td style="padding-right: 50px;">Set day:</td>
                                    <td>
                                        <el-select v-model="day_value1" placeholder="Select day" style="width: 240px">
                                            <el-option v-for="item in day_options" :key="item.day_value" :label="item.label"
                                                :value="item.day_value">
                                            </el-option>
                                        </el-select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Set start time:</td>
                                    <td>
                                        <el-time-picker v-model="start_time1" :picker-options="{
                                            selectableRange: '00:00 - 23:59'
                                        }" placeholder="Select time" format="h:mm A" style="width: 240px"></el-time-picker>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Set end time:</td>
                                    <td>
                                        <el-time-picker v-model="end_time1" :picker-options="{
                                            selectableRange: '00:00 - 23:59'
                                        }" placeholder="Select time" format="h:mm A" style="width: 240px"></el-time-picker>
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <el-button>Create</el-button>
                            <el-button>Cancel</el-button>
                        </div>
                    </el-card>
                </td>
                <td>
                    <el-card class="card" shadow="hover">
                        <div class="card_div">
                            <label style="font-size: 20px;">Edit time slot</label>
                            <br><br>
                            <table class="table_in_card">
                                <tr>
                                    <td>Time slot:</td>
                                    <td>
                                        <el-select v-model="timeslot_selected" placeholder="Select time slot"
                                            style="width: 240px">
                                            <el-option v-for="item in timeslot_options" :key="item.timeslot_value"
                                                :label="item.label" :value="item.timeslot_value">
                                            </el-option>
                                        </el-select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-right: 50px;">Set day:</td>
                                    <td>
                                        <el-select v-model="day_value2" placeholder="Select day" style="width: 240px">
                                            <el-option v-for="item in day_options" :key="item.day_value" :label="item.label"
                                                :value="item.day_value">
                                            </el-option>
                                        </el-select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Set start time:</td>
                                    <td>
                                        <el-time-picker v-model="start_time2" :picker-options="{
                                            selectableRange: '00:00 - 23:59'
                                        }" placeholder="Select time" format="h:mm A" style="width: 240px"></el-time-picker>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Set end time:</td>
                                    <td>
                                        <el-time-picker v-model="end_time2" :picker-options="{
                                            selectableRange: '00:00 - 23:59'
                                        }" placeholder="Select time" format="h:mm A" style="width: 240px"></el-time-picker>
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <el-button>Update</el-button>
                            <el-button>Cancel</el-button>
                        </div>
                    </el-card>
                </td>
            </tr>
        </table>
    </div>
</template>

<script src="./StaffScheduleScript.js"></script>

<style scoped>
.container {
    display: flex;
    align-items: center;
    justify-content: center;
}

.schedule_table {
    margin: 30px;
    width: 60%;
    background-color: #ffffff;
    border-collapse: collapse;
}

.schedule_table:hover {
    box-shadow: 0 0 10px rgba(255, 255, 255, 0.5);
}

.schedule_table th {
    background-color: #4f4f4f;
    color: #ffffff;
    font-weight: bold;
    font-size: 16px;
    text-align: center;
    width: 14.28%;
    min-width: 90px;
    padding: 10px 0px 10px 0px;
}

.schedule_table td {
    color: #4f4f4f;
    font-size: 16px;
    text-align: center;
    width: 14.28%;
    min-width: 90px;
    padding: 10px 0px 10px 0px;
}

.table_in_card td {
    padding: 0px 0px 8px 0px;
}

#card_column {
    padding-right: 25px;
}

.card {
    width: 100%;
    height: auto;
}

.card_div {
    margin: 0 6px 6px 6px;
}
</style>
