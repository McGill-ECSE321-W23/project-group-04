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
                {day_value: "SUNDAY", label: "Sunday",},
                {day_value: "MONDAY", label: "Monday",},
                {day_value: "TUESDAY", label: "Tuesday",},
                {day_value: "WEDNESDAY", label: "Wednesday",},
                {day_value: "THURSDAY", label: "Thursday",},
                {day_value: "FRIDAY", label: "Friday",},
                {day_value: "SATURDAY", label: "Saturday",}
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
        axios.get('http://localhost:8080/api/staff/all', {
            withCredentials: true,
            headers: {
                "Access-Control-Allow-Origin": 'localhost:8080',
            }
        })
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
            // alert(e.response.data)
        })

        // axios.get('http://localhost:8080/api/timeslot/getAll', {
        //     withCredentials: true,
        //     headers: {
        //         "Access-Control-Allow-Origin": 'localhost:8080',
        //     }
        // })
        // .then(response => {
        //     this.all_timeslots = [];
        //     for (var i = 0; i < response.data.length; i++) {
        //         var slot = response.data[i];
        //         var slotDto = new TimeSlotDto(slot.timeSlotID, slot.dayOfTheWeek, slot.startTime, slot.endTime, slot.systemId, slot.staffAccountId);
        //         console.log(slotDto);
        //         this.all_timeslots.push({
        //             timeslot: slotDto,
        //             timeslotID: slotDto.timeSlotID,
        //             label: slotDto.day + " " + slotDto.startTime + " - " + slotDto.endTime,
        //         });
                
        //     }
            
        // })
        // .catch(e => {
        //     console.log(e)
        //     // alert(e.response.data)
        // })
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
                if (this.all_timeslots[i].timeslot.staffID == staffId && this.all_timeslots[i].timeslot.day == day.toUpperCase()) {
                    timeSlotObj = this.all_timeslots[i].timeslot;
                    index = i;
                    break;
                }
            }

            if (timeSlotObj == null) { // create new timeslot
                axios.post('http://localhost:8080/api/timeslot/create', {}, {
                    withCredentials: true,
                    headers: {
                        "Access-Control-Allow-Origin": 'localhost:8080',
                    },
                    params: {
                        dayOfTheWeek: day,
                        startTime: starttime,
                        endTime: endtime,
                        accountID: staffId,
                    }
                })
                .then(response => {
                    var slot = response.data;
                    console.log("created : ");
                    console.log(slot);
                    var slotDto = new TimeSlotDto(slot.timeSlotID, slot.dayOfTheWeek, slot.startTime, slot.endTime, slot.systemId, slot.staffAccount);
                    console.log(slotDto.toString());
                    this.all_timeslots.push({
                        timeslot: slotDto,
                        timeslotID: slotDto.timeSlotID,
                        label: slotDto.day + " " + slotDto.startTime + " - " + slotDto.endTime,
                    })
                    
                    this.refreshTable();
                    this.cancelUpdateTimeSlot();
                })
                .catch(e => {
                    console.log(e)
                    alert(e.response.data)
                })
            } else { // update existing timeslot
                axios.put('http://localhost:8080/api/timeslot/update/'.concat(timeSlotObj.timeSlotID), {}, {
                    withCredentials: true,
                    headers: {
                        "Access-Control-Allow-Origin": 'localhost:8080',
                    },
                    params: {
                        timeSlotID: timeSlotObj.timeSlotID,
                        dayOfTheWeek: day,
                        startTime: starttime,
                        endTime: endtime,
                    }
                })
                .then(response => {
                    var slot = response.data;
                    var slotDto = new TimeSlotDto(slot.timeSlotID, slot.dayOfTheWeek, slot.startTime, slot.endTime, slot.systemId, slot.staffAccount);
                    this.all_timeslots[index].timeslot = slotDto;
                    this.all_timeslots[index].timeslotID = slotDto.timeSlotID;
                    this.all_timeslots[index].label = slotDto.day + " " + slotDto.startTime + " - " + slotDto.endTime;
                    
                    this.refreshTable();
                    this.cancelUpdateTimeSlot();
                })
                .catch(e => {
                    console.log(e)
                    alert(e.response.data)
                })
            }

            
        },

        deleteTimeslot: function (day, staffId) {
            if (day == "" || staffId == "") {
                alert("Please fill in the required fields");
                return;
            }

            var timeSlotObj = null;
            for (var i = 0; i < this.all_timeslots.length; i++) {
                if (this.all_timeslots[i].timeslot.staffID == staffId && this.all_timeslots[i].timeslot.day == day.toUpperCase()) {
                    timeSlotObj = this.all_timeslots[i].timeslot;

                    axios.delete('http://localhost:8080/api/timeslot/delete/'.concat(timeSlotObj.timeSlotID), {
                        withCredentials: true,
                        headers: {
                            "Access-Control-Allow-Origin": 'localhost:8080',
                        }
                    })
                    .then(response => {
                        this.all_timeslots.splice(i, 1);
                        this.refreshTable();
                        this.cancelUpdateTimeSlot();
                    })
                    .catch(e => {
                        console.log(e)
                        alert(e.response.data)
                    })

                    break;
                }
            }

            if (timeSlotObj == null) {
                alert("Time slot not found");
                console.log("Time slot not found");
                return;
            }
            this.refreshTable();
            this.cancelUpdateTimeSlot();
            
        },

        cancelUpdateTimeSlot: function () {
            this.day_value2 = "";
            this.start_time2 = "";
            this.end_time2 = "";
        },

        refreshTable: function () {
            //if (this.staff_selected != "") {
                console.log("staff id:" + this.staff_selected);

                axios.get('http://localhost:8080/api/timeslot/getAll/'.concat(this.staff_selected), {
                    withCredentials: true,
                    headers: {
                        "Access-Control-Allow-Origin": 'localhost:8080',
                    }
                })
                .then(response => {
                    console.log("fetch staff timeslots:");
                    this.all_timeslots = [];
                    for (var i = 0; i < response.data.length; i++) {
                        var slot = response.data[i];
                        var slotDto = new TimeSlotDto(slot.timeSlotID, slot.dayOfTheWeek, slot.startTime, slot.endTime, slot.systemId, slot.staffAccount);
                        this.all_timeslots.push({
                            timeslot: slotDto,
                            timeslotID: slotDto.timeSlotID,
                            label: slotDto.day + " " + slotDto.startTime + " - " + slotDto.endTime,
                        })
                    }
                    console.log(this.all_timeslots);

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

                    for (var i = 0; i < this.all_timeslots.length; i++) {
                        var slot = this.all_timeslots[i].timeslot;
                        if (slot.day == "SUNDAY") {
                            sun.textContent = slot.startTime + " - " + slot.endTime;
                        } else if (slot.day == "MONDAY") {
                            mon.textContent = slot.startTime + " - " + slot.endTime;
                        } else if (slot.day == "TUESDAY") {
                            tue.textContent = slot.startTime + " - " + slot.endTime;
                        } else if (slot.day == "WEDNESDAY") {
                            wed.textContent = slot.startTime + " - " + slot.endTime;
                        } else if (slot.day == "THURSDAY") {
                            thu.textContent = slot.startTime + " - " + slot.endTime;
                        } else if (slot.day == "FRIDAY") {
                            fri.textContent = slot.startTime + " - " + slot.endTime;
                        } else {
                            sat.textContent = slot.startTime + " - " + slot.endTime;
                        }
                    }

                })
                .catch(e => {
                    console.log(e)
                })

            //}
        }
        
    }
};
