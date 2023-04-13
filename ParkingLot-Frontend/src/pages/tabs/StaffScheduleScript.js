import axios from 'axios';

function Staff(email) {
    this.email = email;
}

function staffTimeslot(day, startTime, endTime, staff) {
    this.day = day;
    this.startTime = startTime;
    this.endTime = endTime;
    this.staff = staff;
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
            day_value1: "", // create
            start_time1: "",
            end_time1: "",

            day_value2: "", // update
            start_time2: "",
            end_time2: "",

            // data
            staff_list: [], // list of staffs
            all_timeslots: [], // list of all timeslots
            
        };
    },
    created: function() {
        
    },
    methods: {
        createTimeSlot: function (day, startTime, endTime, staff) { // create a new timeslot
            if (day == "" || startTime == "" || endTime == "" || staff == "") {
                alert("Please fill in the required fields");
                return;
            }
            const startHour = startTime.getHours().toString().padStart(2, '0');
            const startMin = startTime.getMinutes().toString().padStart(2, '0');
            const startAMPM = startTime.getHours() >= 12 ? 'PM' : 'AM';
            const startString = startHour + ":" + startMin + " " + startAMPM; // format time
            const endHour = endTime.getHours().toString().padStart(2, '0');
            const endMin = endTime.getMinutes().toString().padStart(2, '0');
            const endAMPM = endTime.getHours() >= 12 ? 'PM' : 'AM';
            const endString = endHour + ":" + endMin + " " + endAMPM;

            var slot = new staffTimeslot(day, startString, endString, staff);
            this.all_timeslots.push(slot);
            this.cancelCreateTimeSlot();
            this.refresh();
        },
        cancelCreateTimeSlot: function () { // clear values
            this.day_value1 = "";
            this.start_time1 = "";
            this.end_time1 = "";
        },
        updateTimeslot: function (timeSlot, startTime, endTime, staff) { // update a timeslot
            if (timeSlot == "" || startTime == "" || endTime == "" || staff == "") {
                alert("Please fill in the required fields");
                return;
            }
            


            this.cancelUpdateTimeSlot();
            this.refresh();
        },
        deleteTimeslot: function (timeSlot, staff) { // delete a timeslot
            if (timeSlot == "" || staff == "") {
                alert("Please fill in the required fields");
                return;
            }

            this.cancelUpdateTimeSlot();
            this.refresh();
        },
        cancelUpdateTimeSlot: function () { // clear values
            this.day_value2 = "";
            this.start_time2 = "";
            this.end_time2 = "";
        },

        refreshTable: function() {
            if (this.staff_selected != "") {
                this.timeslot_list = [];
                for (var i = 0; i < this.all_timeslots.length; i++) {
                    if (this.all_timeslots[i].staff == this.staff_selected) {
                        const slot = this.all_timeslots[i];
                        this.timeslot_list.push({
                            timeslot_obj: slot,
                            timeslot_value: slot.day + " " + slot.startTime + " - " + slot.endTime,
                            label: slot.day + " " + slot.startTime + " - " + slot.endTime,
                        });
                    }
                }
                // prepare update table
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

                // format timeslot list
                for (var i = 0; i < this.timeslot_list.length; i++) {
                    const slot = this.timeslot_list[i].timeslot_obj;
                    const schedule = slot.startTime + " - " + slot.endTime;

                    if (slot.day == "Sunday") {
                        sun.textContent = schedule;
                    } else if (slot.day == "Monday") {
                        mon.textContent = schedule;
                    } else if (slot.day == "Tuesday") {
                        tue.textContent = schedule;
                    } else if (slot.day == "Wednesday") {
                        wed.textContent = schedule;
                    } else if (slot.day == "Thursday") {
                        thu.textContent = schedule;
                    } else if (slot.day == "Friday") {
                        fri.textContent = schedule;
                    } else if (slot.day == "Saturday") {
                        sat.textContent = schedule;
                    } else {
                        alert("Error: an invalid day has been found in refresh()");
                    }
                }
                this.timeslot_selected = "";
            }
        }
        
    }
};
