<template>
    <div class="container">
        <h1>Staff Schedule</h1>
    </div>
    <br />
    <div class="container">
        <label for="staffSelect" style="padding-right: 10px">Select a staff:</label>
        <el-select v-model="staff_selected" placeholder="Select" style="width: 300px" @change="refreshTable()">
            <el-option v-for="item in staff_list" :key="item.staffID" :label="item.label" :value="item.staffID">
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
                <td id="sun_schedule">-</td>
                <td id="mon_schedule">-</td>
                <td id="tue_schedule">-</td>
                <td id="wed_schedule">-</td>
                <td id="thu_schedule">-</td>
                <td id="fri_schedule">-</td>
                <td id="sat_schedule">-</td>
            </tr>
        </table>
    </div>
    <div class="container">
        <el-card class="card" shadow="hover" style="max-width: 420px;">
            <div class="card_div">
                <label style="font-size: 20px;">Edit schedule</label>
                <br><br>
                <table class="table_in_card">
                    <tr>
                        <td>Day:</td>
                        <td>
                            <el-select v-model="day_value2" placeholder="Select day"
                                style="width: 240px">
                                <el-option v-for="item in day_options" :key="item.day_value"
                                    :label="item.label" :value="item.day_value">
                                </el-option>
                            </el-select>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-right: 20px;">Set start time:</td>
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
                <el-button @click="updateTimeslot(day_value2,start_time2,end_time2,staff_selected)">Update</el-button>
                <el-button @click="deleteTimeslot(day_value2,staff_selected)">Delete</el-button>
                <el-button @click="cancelUpdateTimeSlot()">Cancel</el-button>
            </div>
        </el-card>
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
    box-shadow: 0px 0px 10px 0px #ffffff;
    transition: box-shadow 0.3s ease-in-out;
}

.schedule_table:hover {
    box-shadow: 0px 0px 10px 0px #ccc;
    transition: box-shadow 0.3s ease-in-out;
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
    min-width: 110px;
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
