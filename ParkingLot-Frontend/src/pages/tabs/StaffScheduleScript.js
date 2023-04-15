import axios from 'axios';

function StaffAccountDto(accountID, email) {
    this.accountID = accountID;
    this.email = email;
}

function TimeSlotDto(timeSlotID, day, startTime, endTime, systemID, staffID) {
    this.timeSlotID = timeSlotID;
    this.day = day;
    this.startTime = startTime;
    this.endTime = endTime;
    this.systemID = systemID;
    this.staffID = staffID;
}

export default {
    data() {
        return {
            staff_selected: "",

            day_options: [
                {day_value: "Sunday", label: "Sunday",},
                {day_value: "Monday", label: "Monday",},
                {day_value: "Tuesday", label: "Tuesday",},
                {day_value: "Wednesday", label: "Wednesday",},
                {day_value: "Thursday", label: "Thursday",},
                {day_value: "Friday", label: "Friday",},
                {day_value: "Saturday", label: "Saturday",}
            ],

            day_value2: "", // update
            start_time2: "",
            end_time2: "",

            // data
            staff_list: [],
            all_timeslots: [],
            
        };
    },
    created: function() {
        axios.get('http://localhost:8080/api/staff/all')
        .then(response => {
            this.staff_list = [];
            for (var i = 0; i < response.data.length; i++) {
                var staff = response.data[i];
                var staffDto = new StaffAccountDto(staff.accountID, staff.email);
                this.staff_list.push({
                    staff: staffDto,
                    staffID: staffDto.accountID,
                    label: staffDto.email,
                });
            }
        })
        .catch(e => {
            console.log(e)
            alert(e)
        })

        axios.get('http://localhost:8080/api/timeslot/getAll')
        .then(response => {
            this.all_timeslots = [];
            for (var i = 0; i < response.data.length; i++) {
                var slot = response.data[i];
                if (slot.systemId == null) {
                    var slotDto = new TimeSlotDto(slot.timeSlotID, slot.dayOfTheWeek, slot.startTime, slot.endTime, slot.systemId, slot.staffAccountId);
                    this.all_timeslots.push({
                        timeslot: slotDto,
                        timeslotID: slotDto.timeSlotID,
                        label: slotDto.day + " " + slotDto.startTime + " - " + slotDto.endTime,
                    });
                }
            }
        })
        .catch(e => {
            console.log(e)
            alert(e)
        })
    },
    methods: {
        updateTimeslot: function (day, startTime, endTime, staffId) {
            if (day == "" || startTime == "" || endTime == "" || staffId == "") {
                alert("Please fill in the required fields");
                return;
            }

            const date1 = new Date(startTime);
            const starttime = date1.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
            const date2 = new Date(endTime);
            const endtime = date2.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
            
            var timeSlotObj = null;
            var index = -1;
            for (var i = 0; i < this.all_timeslots.length; i++) {
                if (this.all_timeslots[i].timeslot.staffID == staffId && this.all_timeslots[i].timeslot.day == day) {
                    timeSlotObj = this.all_timeslots[i].timeslot;
                    index = i;
                    break;
                }
            }

            if (timeSlotObj == null) { // create new timeslot
                axios.post('http://localhost:8080/api/timeslot/create', {}, {
                    params: {
                        dayOfTheWeek: day,
                        startTime: starttime,
                        endTime: endtime,
                        accountID: staffId,
                    }
                })
                .then(response => {
                    var slot = response.data;
                    var slotDto = new TimeSlotDto(slot.timeSlotID, slot.dayOfTheWeek, slot.startTime, slot.endTime, slot.systemId, slot.staffAccountId);
                    this.all_timeslots.push({
                        timeslot: slotDto,
                        timeslotID: slotDto.timeSlotID,
                        label: slotDto.day + " " + slotDto.startTime + " - " + slotDto.endTime,
                    })
                })
                .catch(e => {
                    console.log(e)
                    alert(e)
                })
            } else { // update existing timeslot
                axios.put('http://localhost:8080/api/timeslot/update/'.concat(timeSlotObj.timeSlotID), {}, {
                    params: {
                        timeSlotID: timeSlotObj.timeSlotID,
                        dayOfTheWeek: day,
                        startTime: starttime,
                        endTime: endtime,
                    }
                })
                .then(response => {
                    var slot = response.data;
                    var slotDto = new TimeSlotDto(slot.timeSlotID, slot.dayOfTheWeek, slot.startTime, slot.endTime, slot.systemId, slot.staffAccountId);
                    this.all_timeslots[index].timeslot = slotDto;
                    this.all_timeslots[index].timeslotID = slotDto.timeSlotID;
                    this.all_timeslots[index].label = slotDto.day + " " + slotDto.startTime + " - " + slotDto.endTime;
                })
                .catch(e => {
                    console.log(e)
                    alert(e)
                })
            }

            this.cancelUpdateTimeSlot();
            this.refreshTable();
        },

        deleteTimeslot: function (day, staffId) {
            if (day == "" || staffId == "") {
                alert("Please fill in the required fields");
                return;
            }

            var timeSlotObj = null;
            for (var i = 0; i < this.all_timeslots.length; i++) {
                if (this.all_timeslots[i].timeslot.staffID == staffId && this.all_timeslots[i].timeslot.day == day) {
                    timeSlotObj = this.all_timeslots[i].timeslot;

                    axios.delete('http://localhost:8080/api/timeslot/delete/'.concat(timeSlotObj.timeSlotID))
                    .then(response => {
                        this.all_timeslots.splice(i, 1);
                    })
                    .catch(e => {
                        console.log(e)
                        alert(e)
                    })

                    break;
                }
            }

            if (timeSlotObj == null) {
                alert("Time slot not found");
                console.log("Time slot not found");
                return;
            }

            this.cancelUpdateTimeSlot();
            this.refreshTable();
        },

        cancelUpdateTimeSlot: function () {
            this.day_value2 = "";
            this.start_time2 = "";
            this.end_time2 = "";
        },

        refreshTable: function () {
            if (this.staff_selected != "") {
                var staffschedule = []
                for (var i = 0; i < this.all_timeslots.length; i++) {
                    if (this.all_timeslots[i].timeslot.staffID == this.staff_selected) {
                        staffschedule.push(this.all_timeslots[i].timeslot);
                    }
                }
                
                const sun = document.getElementById("sun_schedule");
                const mon = document.getElementById("mon_schedule");
                const tue = document.getElementById("tue_schedule");
                const wed = document.getElementById("wed_schedule");
                const thu = document.getElementById("thu_schedule");
                const fri = document.getElementById("fri_schedule");
                const sat = document.getElementById("sat_schedule");
                sun.textContent = "-";
                mon.textContent = "-";
                tue.textContent = "-";
                wed.textContent = "-";
                thu.textContent = "-";
                fri.textContent = "-";
                sat.textContent = "-";

                for (var i = 0; i < staffschedule.length; i++) {
                    var slot = staffschedule[i];
                    if (slot.day == "Sunday") {
                        sun.textContent = slot.startTime + " - " + slot.endTime;
                    } else if (slot.day == "Monday") {
                        mon.textContent = slot.startTime + " - " + slot.endTime;
                    } else if (slot.day == "Tuesday") {
                        tue.textContent = slot.startTime + " - " + slot.endTime;
                    } else if (slot.day == "Wednesday") {
                        wed.textContent = slot.startTime + " - " + slot.endTime;
                    } else if (slot.day == "Thursday") {
                        thu.textContent = slot.startTime + " - " + slot.endTime;
                    } else if (slot.day == "Friday") {
                        fri.textContent = slot.startTime + " - " + slot.endTime;
                    } else {
                        sat.textContent = slot.startTime + " - " + slot.endTime;
                    }
                }
            }
        }
        
    }
};
