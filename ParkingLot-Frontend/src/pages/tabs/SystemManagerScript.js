import axios from 'axios';

function ParkingSystemDto(systemID, monthlyFee, feePer15m, maxStay, numRegular, numLarge, numMonthlyFloors, numMonthlySpots, numGarages) {
    this.systemID = systemID;
    this.monthlyFee = monthlyFee;
    this.feePer15m = feePer15m;
    this.maxStay = maxStay;
    this.numRegular = numRegular;
    this.numLarge = numLarge;
    this.numMonthlyFloors = numMonthlyFloors;
    this.numMonthlySpots = numMonthlySpots;
    this.numGarages = numGarages;
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
            system_selected: "",

            parkingspecs_options: [
                {spec_value: "Monthly fee", label: "Monthly fee",},
                {spec_value: "Fee per 15 mins", label: "Fee per 15 mins",},
                {spec_value: "Maximum stay duration", label: "Maximum stay duration",},
                {spec_value: "Number of regular parking spots", label: "Number of regular parking spots",},
                {spec_value: "Number of large parking spots", label: "Number of large parking spots",},
                {spec_value: "Number of floors for monthly users", label: "Number of floors for monthly users",},
                {spec_value: "Number of parking spots for monthly users per floor", label: "Number of parking spots for monthly users per floor",},
                {spec_value: "Number of garages", label: "Number of garages",}
            ],
            parkingspecs_selected: "",
            spec_input: "",

            day_options: [
                {day_value: "Sunday", label: "Sunday",},
                {day_value: "Monday", label: "Monday",},
                {day_value: "Tuesday", label: "Tuesday",},
                {day_value: "Wednesday", label: "Wednesday",},
                {day_value: "Thursday", label: "Thursday",},
                {day_value: "Friday", label: "Friday",},
                {day_value: "Saturday", label: "Saturday",}
            ],
            day_selected: "",
            start_time: "",
            end_time: "",

            create_monthlyfee: "",
            create_feeper15mins: "",
            create_maxstay: "",
            create_numregular: "",
            create_numlarge: "",
            create_nummonthlyfloors: "",
            create_nummonthlyspots: "",
            create_numgarages: "",

            // data
            system_list: [],
            all_openhours: [],
        };
    },
    created() {
        axios.get("http://localhost:8080/api/parkinglotsoftwaresystem/getall", {
            withCredentials: true,
            headers: {
                'Access-Control-Allow-Origin': 'localhost:8080'
            }
        })
        .then(response => {
            this.system_list = [];
            for (var i = 0; i < response.data.length; i++) {
                var system = response.data[i];
                var systemDto = new ParkingSystemDto(system.parkingLotSoftwareSystemID, system.monthlyFee, system.feePer15m, system.maxStay, system.numberOfRegularParkingSpots, system.numberOfLargeParkingSpots, system.numberOfMonthlyFloors, system.numberOfMonthlySpotsPerFloor, system.numberOfGarages);
                this.system_list.push({
                    system: systemDto,
                    systemID: systemDto.systemID,
                    label: "System " + systemDto.systemID,
                })
            }
        })
        .catch(e => {
            console.log(e)
            // alert(e.response.data)
        })

        axios.get("http://localhost:8080/api/timeslot/getOpen", {
            withCredentials: true,
            headers: {
                'Access-Control-Allow-Origin': 'localhost:8080'
            }
        })
        .then(response => {
            this.all_openhours = [];
            for (var i = 0; i < response.data.length; i++) {
                var timeSlot = response.data[i];
                var timeSlotDto = new TimeSlotDto(timeSlot.timeSlotID, timeSlot.dayOfTheWeek, timeSlot.startTime, timeSlot.endTime, timeSlot.systemId, timeSlot.staffAccountId);
                this.all_openhours.push({
                    timeSlot: timeSlotDto,
                    timeSlotID: timeSlotDto.timeSlotID,
                    label: "Time Slot " + timeSlotDto.timeSlotID,
                })
            }
        })
        .catch(e => {
            console.log(e)
            // alert(e.response.data)
        })
    },
    methods: {
        createSystem: function (monthlyFee, feePer15m, maxStay, numRegular, numLarge, numMonthlyFloors, numMonthlySpots, numGarages) {
            if (monthlyFee == "" || feePer15m == "" || maxStay == "" || numRegular == "" || numLarge == "" || numMonthlyFloors == "" || numMonthlySpots == "" || numGarages == "") {
                alert("Please fill in the required fields");
                return;
            }

            axios.post("http://localhost:8080/api/parkinglotsoftwaresystem/create", {}, {
                withCredentials: true,
                headers: {
                    'Access-Control-Allow-Origin': 'localhost:8080'
                },
                params: {
                    monthlyFee: monthlyFee,
                    feePer15m: feePer15m,
                    maxStay: maxStay,
                    numberOfRegularParkingSpots: numRegular,
                    numberOfLargeParkingSpots: numLarge,
                    numberOfMonthlyFloors: numMonthlyFloors,
                    numberOfMonthlySpotsPerFloor: numMonthlySpots,
                    numberOfGarages: numGarages,
                }
            })
            .then(response => {
                var system = response.data;
                var systemDto = new ParkingSystemDto(system.parkingLotSoftwareSystemID, system.monthlyFee, system.feePer15m, system.maxStay, system.numberOfRegularParkingSpots, system.numberOfLargeParkingSpots, system.numberOfMonthlyFloors, system.numberOfMonthlySpotsPerFloor, system.numberOfGarages);
                this.system_list.push({
                    system: systemDto,
                    systemID: systemDto.systemID,
                    label: "System " + systemDto.systemID,
                })
                this.clearCreateSystem();
            })
            .catch(e => {
                console.log(e)
                alert(e.response.data)
            })
        },
        
        clearCreateSystem: function () {
            this.create_monthlyfee = "";
            this.create_feeper15mins = "";
            this.create_maxstay = "";
            this.create_numregular = "";
            this.create_numlarge = "";
            this.create_nummonthlyfloors = "";
            this.create_nummonthlyspots = "";
            this.create_numgarages = "";
        },

        updateSystem: function (specs, value, system) {
            if (specs == "" || value == "" || system == "") {
                alert("Please fill in the required fields");
                return;
            }

            var systemID = system; // system is already an id
            var systemObj = null;
            
            for (var i = 0; i < this.system_list.length; i++) {
                if (this.system_list[i].systemID == systemID) {
                    systemObj = this.system_list[i].system;

                    if (specs == "Monthly fee") {
                        axios.put("http://localhost:8080/api/parkinglotsoftwaresystem/update/".concat(systemID), {}, {
                            withCredentials: true,
                            headers: {
                                'Access-Control-Allow-Origin': 'localhost:8080'
                            },
                            params: {
                                parkingLotSoftwareSystemID: systemID, monthlyFee: value, feePer15m: systemObj.feePer15m, maxStay: systemObj.maxStay,
                                numberOfRegularParkingSpots: systemObj.numRegular, numberOfLargeParkingSpots: systemObj.numLarge, numberOfMonthlyFloors: systemObj.numMonthlyFloors,
                                numberOfMonthlySpotsPerFloor: systemObj.numMonthlySpots, numberOfGarages: systemObj.numGarages,
                            }
                        })
                        .then(response => {
                            this.system_list[i].system.monthlyFee = value;
                            this.refreshTable();
                            this.clearEditSpecs();
                        })
                        .catch(e => {
                            console.log(e)
                            alert(e.response.data)
                        })
                    } else if (specs == "Fee per 15 mins") {
                        axios.put("http://localhost:8080/api/parkinglotsoftwaresystem/update/".concat(systemID), {}, {
                            withCredentials: true,
                            headers: {
                                'Access-Control-Allow-Origin': 'localhost:8080'
                            },
                            params: {
                                parkingLotSoftwareSystemID: systemID, monthlyFee: systemObj.monthlyFee, feePer15m: value, maxStay: systemObj.maxStay,
                                numberOfRegularParkingSpots: systemObj.numRegular, numberOfLargeParkingSpots: systemObj.numLarge, numberOfMonthlyFloors: systemObj.numMonthlyFloors,
                                numberOfMonthlySpotsPerFloor: systemObj.numMonthlySpots, numberOfGarages: systemObj.numGarages,
                            }
                        })
                        .then(response => {
                            this.system_list[i].system.feePer15m = value;
                            this.refreshTable();
                            this.clearEditSpecs();
                        })
                        .catch(e => {
                            console.log(e)
                            alert(e.response.data)
                        })
                    } else if (specs == "Maximum stay duration") {
                        axios.put("http://localhost:8080/api/parkinglotsoftwaresystem/update/".concat(systemID), {}, {
                            withCredentials: true,
                            headers: {
                                'Access-Control-Allow-Origin': 'localhost:8080'
                            },
                            params: {
                                parkingLotSoftwareSystemID: systemID, monthlyFee: systemObj.monthlyFee, feePer15m: systemObj.feePer15m, maxStay: value,
                                numberOfRegularParkingSpots: systemObj.numRegular, numberOfLargeParkingSpots: systemObj.numLarge, numberOfMonthlyFloors: systemObj.numMonthlyFloors,
                                numberOfMonthlySpotsPerFloor: systemObj.numMonthlySpots, numberOfGarages: systemObj.numGarages,
                            }
                        })
                        .then(response => {
                            this.system_list[i].system.maxStay = value;
                            this.refreshTable();
                            this.clearEditSpecs();
                        })
                        .catch(e => {
                            console.log(e)
                            alert(e.response.data)
                        })
                    } else if (specs == "Number of regular parking spots") {
                        axios.put("http://localhost:8080/api/parkinglotsoftwaresystem/update/".concat(systemID), {}, {
                            withCredentials: true,
                            headers: {
                                'Access-Control-Allow-Origin': 'localhost:8080'
                            },
                            params: {
                                parkingLotSoftwareSystemID: systemID, monthlyFee: systemObj.monthlyFee, feePer15m: systemObj.feePer15m, maxStay: systemObj.maxStay,
                                numberOfRegularParkingSpots: value, numberOfLargeParkingSpots: systemObj.numLarge, numberOfMonthlyFloors: systemObj.numMonthlyFloors,
                                numberOfMonthlySpotsPerFloor: systemObj.numMonthlySpots, numberOfGarages: systemObj.numGarages,
                            }
                        })
                        .then(response => {
                            this.system_list[i].system.numRegular = value;
                            this.refreshTable();
                            this.clearEditSpecs();
                        })
                        .catch(e => {
                            console.log(e)
                            alert(e.response.data)
                        })
                    } else if (specs == "Number of large parking spots") {
                        axios.put("http://localhost:8080/api/parkinglotsoftwaresystem/update/".concat(systemID), {}, {
                            withCredentials: true,
                            headers: {
                                'Access-Control-Allow-Origin': 'localhost:8080'
                            },
                            params: {
                                parkingLotSoftwareSystemID: systemID, monthlyFee: systemObj.monthlyFee, feePer15m: systemObj.feePer15m, maxStay: systemObj.maxStay,
                                numberOfRegularParkingSpots: systemObj.numRegular, numberOfLargeParkingSpots: value, numberOfMonthlyFloors: systemObj.numMonthlyFloors,
                                numberOfMonthlySpotsPerFloor: systemObj.numMonthlySpots, numberOfGarages: systemObj.numGarages,
                            }
                        })
                        .then(response => {
                            this.system_list[i].system.numLarge = value;
                            this.refreshTable();
                            this.clearEditSpecs();
                        })
                        .catch(e => {
                            console.log(e)
                            alert(e.response.data)
                        })
                    } else if (specs == "Number of floors for monthly users") {
                        axios.put("http://localhost:8080/api/parkinglotsoftwaresystem/update/".concat(systemID), {}, {
                            withCredentials: true,
                            headers: {
                                'Access-Control-Allow-Origin': 'localhost:8080'
                            },
                            params: {
                                parkingLotSoftwareSystemID: systemID, monthlyFee: systemObj.monthlyFee, feePer15m: systemObj.feePer15m, maxStay: systemObj.maxStay,
                                numberOfRegularParkingSpots: systemObj.numRegular, numberOfLargeParkingSpots: systemObj.numLarge, numberOfMonthlyFloors: value,
                                numberOfMonthlySpotsPerFloor: systemObj.numMonthlySpots, numberOfGarages: systemObj.numGarages,
                            }
                        })
                        .then(response => {
                            this.system_list[i].system.numMonthlyFloors = value;
                            this.refreshTable();
                            this.clearEditSpecs();
                        })
                        .catch(e => {
                            console.log(e)
                            alert(e.response.data)
                        })
                    } else if (specs == "Number of parking spots for monthly users per floor") {
                        axios.put("http://localhost:8080/api/parkinglotsoftwaresystem/update/".concat(systemID), {}, {
                            withCredentials: true,
                            headers: {
                                'Access-Control-Allow-Origin': 'localhost:8080'
                            },
                            params: {
                                parkingLotSoftwareSystemID: systemID, monthlyFee: systemObj.monthlyFee, feePer15m: systemObj.feePer15m, maxStay: systemObj.maxStay,
                                numberOfRegularParkingSpots: systemObj.numRegular, numberOfLargeParkingSpots: systemObj.numLarge, numberOfMonthlyFloors: systemObj.numMonthlyFloors,
                                numberOfMonthlySpotsPerFloor: value, numberOfGarages: systemObj.numGarages,
                            }
                        })
                        .then(response => {
                            this.system_list[i].system.numMonthlySpots = value;
                            this.refreshTable();
                            this.clearEditSpecs();
                        })
                        .catch(e => {
                            console.log(e)
                            alert(e.response.data)
                        })
                    } else if (specs == "Number of garages") {
                        axios.put("http://localhost:8080/api/parkinglotsoftwaresystem/update/".concat(systemID), {}, {
                            withCredentials: true,
                            headers: {
                                'Access-Control-Allow-Origin': 'localhost:8080'
                            },
                            params: {
                                parkingLotSoftwareSystemID: systemID, monthlyFee: systemObj.monthlyFee, feePer15m: systemObj.feePer15m, maxStay: systemObj.maxStay,
                                numberOfRegularParkingSpots: systemObj.numRegular, numberOfLargeParkingSpots: systemObj.numLarge, numberOfMonthlyFloors: systemObj.numMonthlyFloors,
                                numberOfMonthlySpotsPerFloor: systemObj.numMonthlySpots, numberOfGarages: value,
                            }
                        })
                        .then(response => {
                            this.system_list[i].system.numGarages = value;
                            this.refreshTable();
                            this.clearEditSpecs();
                        })
                        .catch(e => {
                            console.log(e)
                            alert(e.response.data)
                        })
                    } else {
                        alert("Invalid system specification");
                        console.log("Invalid system specification");
                        return;
                    }

                    break;
                }
            }

            if (systemObj == null) {
                alert("System not found");
                console.log("System not found");
                return;
            }
            
            
        },

        updateOpenHour: function (day, startTime, endTime, system) {
            if (day == "" || startTime == "" || endTime == "" || system == "") {
                alert("Please fill in the required fields");
                return;
            }

            const date1 = new Date(startTime);
            const starttime = date1.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
            const date2 = new Date(endTime);
            const endtime = date2.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

            var timeSlotObj = null;
            var index = -1;
            for (var i = 0; i < this.all_openhours.length; i++) {
                if (this.all_openhours[i].timeSlot.day == day.toUpperCase() && this.all_openhours[i].timeSlot.systemID == system) {
                    timeSlotObj = this.all_openhours[i].timeSlot;
                    index = i;
                    break;
                }
            }

            if (timeSlotObj == null) { // no existing timeslot for this day
                axios.post("http://localhost:8080/api/timeslot/createOpen", {}, {
                    withCredentials: true,
                    headers: {
                        'Access-Control-Allow-Origin': 'localhost:8080'
                    },
                    params: {
                        dayOfTheWeek: day,
                        startTime: starttime,
                        endTime: endtime,
                        parkingLotSoftwareSystemID: system,
                    }
                })
                .then(response => {
                    var slot = response.data
                    var timeSlotDto = new TimeSlotDto(slot.timeSlotID, slot.dayOfTheWeek, slot.startTime, slot.endTime, slot.systemId, slot.staffAccountId)
                    this.all_openhours.push({
                        timeSlot: timeSlotDto,
                        timeSlotID: timeSlotDto.timeSlotID,
                        label: "Time Slot " + timeSlotDto.timeSlotID,
                    })
                    this.refreshTable();
                    this.clearEditOpenHours();
                })
                .catch(e => {
                    console.log(e)
                    alert(e.response.data)
                })
            } else { // existing timeslot for this day
                axios.put("http://localhost:8080/api/timeslot/update/".concat(timeSlotObj.timeSlotID), {}, {
                    withCredentials: true,
                    headers: {
                        'Access-Control-Allow-Origin': 'localhost:8080'
                    },
                    params: {
                        timeSlotID: timeSlotObj.timeSlotID,
                        dayOfTheWeek: day,
                        startTime: starttime,
                        endTime: endtime,
                    }
                })
                .then(response => {
                    var slot = response.data
                    var timeSlotDto = new TimeSlotDto(slot.timeSlotID, slot.dayOfTheWeek, slot.startTime, slot.endTime, slot.systemId, slot.staffAccountId)
                    this.all_openhours[index].timeSlot = timeSlotDto;
                    this.all_openhours[index].timeSlotID = timeSlotDto.timeSlotID;
                    this.all_openhours[index].label = "Time Slot " + timeSlotDto.timeSlotID;
                    this.refreshTable();
                    this.clearEditOpenHours();
                })
                .catch(e => {
                    console.log(e)
                    alert(e.response.data)
                })
            }
            
            
        },

        deleteOpenHour: function (day, system) {
            if (day == "" || system == "") {
                alert("Please fill in the required fields");
                return;
            }

            var timeSlotObj = null;
            for (var i = 0; i < this.all_openhours.length; i++) {
                if (this.all_openhours[i].timeSlot.day == day.toUpperCase() && this.all_openhours[i].timeSlot.systemID == system) {
                    timeSlotObj = this.all_openhours[i].timeSlot;
                    
                    axios.delete("http://localhost:8080/api/timeslot/delete/".concat(timeSlotObj.timeSlotID), {
                        withCredentials: true,
                        headers: {
                            'Access-Control-Allow-Origin': 'localhost:8080'
                        },
                    })
                    .then(response => {
                        this.all_openhours.splice(i, 1);
                        this.refreshTable();
                        this.clearEditOpenHours();
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
            this.clearEditOpenHours();
        },

        clearEditOpenHours: function () {
            this.day_selected = "";
            this.start_time = "";
            this.end_time = "";
        },
        clearEditSpecs: function () {
            this.parkingspecs_selected = "";
            this.spec_input = "";
        },

        refreshTable: function () {
            if (this.system_selected != "") {
                for (var i = 0; i < this.system_list.length; i++) {
                    if (this.system_list[i].systemID == this.system_selected) {
                        const sys = this.system_list[i].system;
                        document.getElementById("monthlyFeeCell").textContent = sys.monthlyFee;
                        document.getElementById("feePer15MinsCell").textContent = sys.feePer15m;
                        document.getElementById("maxStayCell").textContent = sys.maxStay;
                        document.getElementById("numRegularCell").textContent = sys.numRegular;
                        document.getElementById("numLargeCell").textContent = sys.numLarge;
                        document.getElementById("numMonthlyFloorsCell").textContent = sys.numMonthlyFloors;
                        document.getElementById("numMonthlySpotsCell").textContent = sys.numMonthlySpots;
                        document.getElementById("numGaragesCell").textContent = sys.numGarages;

                        document.getElementById("sunOpen").textContent = "-";
                        document.getElementById("monOpen").textContent = "-";
                        document.getElementById("tueOpen").textContent = "-";
                        document.getElementById("wedOpen").textContent = "-";
                        document.getElementById("thuOpen").textContent = "-";
                        document.getElementById("friOpen").textContent = "-";
                        document.getElementById("satOpen").textContent = "-";

                        for (var j = 0; j < this.all_openhours.length; j++) {
                            if (this.all_openhours[j].timeSlot.systemID == this.system_selected) {
                                const oh = this.all_openhours[j].timeSlot;
                                console.log(oh);

                                var hour = oh.startTime[0];
                                var minute = oh.startTime[1];
                                var ampm = hour >= 12 ? 'PM' : 'AM';
                                hour = hour % 12;
                                hour = hour ? hour : 12; // 12 AM instead of 0 AM
                                minute = minute < 10 ? '0' + minute : minute;
                                const start = hour + ':' + minute + ' ' + ampm;

                                var hour2 = oh.endTime[0];
                                var minute2 = oh.endTime[1];
                                var ampm2 = hour2 >= 12 ? 'PM' : 'AM';
                                hour2 = hour2 % 12;
                                hour2 = hour2 ? hour2 : 12; // 12 AM instead of 0 AM
                                minute2 = minute2 < 10 ? '0' + minute2 : minute2;
                                const end = hour2 + ':' + minute2 + ' ' + ampm2;

                                if (oh.day == "SUNDAY") {
                                    document.getElementById("sunOpen").textContent = start + " - " + end;
                                } else if (oh.day == "MONDAY") {
                                    document.getElementById("monOpen").textContent = start + " - " + end;
                                } else if (oh.day == "TUESDAY") {
                                    document.getElementById("tueOpen").textContent = start + " - " + end;
                                } else if (oh.day == "WEDNESDAY") {
                                    document.getElementById("wedOpen").textContent = start + " - " + end;
                                } else if (oh.day == "THURSDAY") {
                                    document.getElementById("thuOpen").textContent = start + " - " + end;
                                } else if (oh.day == "FRIDAY") {
                                    document.getElementById("friOpen").textContent = start + " - " + end;
                                } else if (oh.day == "SATURDAY") {
                                    document.getElementById("satOpen").textContent = start + " - " + end;
                                } else {
                                    console.log("Error: invalid day occured");
                                }
                            }
                        }

                        break;
                    }
                }
            }
        }
    }
};