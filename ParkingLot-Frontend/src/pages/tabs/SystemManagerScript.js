function ParkingSystemDto(monthlyFee, feePer15Mins, maxStay, numRegular, numLarge, numMonthlyFloors, numMonthlySpots, numGarages) {
    this.monthlyFee = monthlyFee;
    this.feePer15Mins = feePer15Mins;
    this.maxStay = maxStay;
    this.numRegular = numRegular;
    this.numLarge = numLarge;
    this.numMonthlyFloors = numMonthlyFloors;
    this.numMonthlySpots = numMonthlySpots;
    this.numGarages = numGarages;
}

function OpenHourDto(day, startTime, endTime, system) {
    this.day = day;
    this.startTime = startTime;
    this.endTime = endTime;
    this.system = system;
}

export default {
    data() {
        return {
            system_list: [],
            system_selected: "",

            all_openhours: [],

            parkingspecs_options: [
                {
                    spec_value: "Monthly fee",
                    label: "Monthly fee",
                },
                {
                    spec_value: "Fee per 15 mins",
                    label: "Fee per 15 mins",
                },
                {
                    spec_value: "Maximum stay duration",
                    label: "Maximum stay duration",
                },
                {
                    spec_value: "Number of regular parking spots",
                    label: "Number of regular parking spots",
                },
                {
                    spec_value: "Number of large parking spots",
                    label: "Number of large parking spots",
                },
                {
                    spec_value: "Number of floors for monthly users",
                    label: "Number of floors for monthly users",
                },
                {
                    spec_value: "Number of parking spots for monthly users per floor",
                    label: "Number of parking spots for monthly users per floor",
                },
                {
                    spec_value: "Number of garages",
                    label: "Number of garages",
                }
            ],
            parkingspecs_selected: "",
            spec_input: "",

            day_options: [
                {
                    day_value: "Sunday",
                    label: "Sunday",
                },
                {
                    day_value: "Monday",
                    label: "Monday",
                },
                {
                    day_value: "Tuesday",
                    label: "Tuesday",
                },
                {
                    day_value: "Wednesday",
                    label: "Wednesday",
                },
                {
                    day_value: "Thursday",
                    label: "Thursday",
                },
                {
                    day_value: "Friday",
                    label: "Friday",
                },
                {
                    day_value: "Saturday",
                    label: "Saturday",
                }
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
        };
    },
    created() {
        const sys1 = new ParkingSystemDto(100, 2, 3, 4, 5, 6, 7, 8);
        const sys2 = new ParkingSystemDto(200, 3, 4, 5, 6, 7, 8, 9);
        this.system_list = [
            {
                system_obj: sys1,
                system_value: "System 1",
                label: "System 1",
            },
            {
                system_obj: sys2,
                system_value: "System 2",
                label: "System 2",
            }
        ]
    },
    methods: {
        createSystem: function (monthlyFee, feePer15Mins, maxStay, numRegular, numLarge, numMonthlyFloors, numMonthlySpots, numGarages) {
            if (monthlyFee == "" || feePer15Mins == "" || maxStay == "" || numRegular == "" || numLarge == "" || numMonthlyFloors == "" || numMonthlySpots == "" || numGarages == "") {
                alert("Please fill in the required fields");
                return;
            }
            var system = new ParkingSystemDto(monthlyFee, feePer15Mins, maxStay, numRegular, numLarge, numMonthlyFloors, numMonthlySpots, numGarages);
            this.system_list.push({
                system_obj: system,
                system_value: "System " + (this.system_list.length + 1),
                label: "System " + (this.system_list.length + 1),
            });
            this.clearCreateSystem();
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
            for (var i = 0; i < this.system_list.length; i++) {
                if (this.system_list[i].system_value == system) {
                    if (specs == "Monthly fee") {
                        this.system_list[i].system_obj.monthlyFee = value;
                    } else if (specs == "Fee per 15 mins") {
                        this.system_list[i].system_obj.feePer15Mins = value;
                    } else if (specs == "Maximum stay duration") {
                        this.system_list[i].system_obj.maxStay = value;
                    } else if (specs == "Number of regular parking spots") {
                        this.system_list[i].system_obj.numRegular = value;
                    } else if (specs == "Number of large parking spots") {
                        this.system_list[i].system_obj.numLarge = value;
                    } else if (specs == "Number of floors for monthly users") {
                        this.system_list[i].system_obj.numMonthlyFloors = value;
                    } else if (specs == "Number of parking spots for monthly users per floor") {
                        this.system_list[i].system_obj.numMonthlySpots = value;
                    } else { // number of garages
                        this.system_list[i].system_obj.numGarages = value;
                    }
                    break;
                }
            }
            this.clearEditSpecs();
            this.refresh();
        },
        updateOpenHour: function (day, startTime, endTime, system) {
            if (day == "" || startTime == "" || endTime == "" || system == "") {
                alert("Please fill in the required fields");
                return;
            }
            this.clearEditOpenHours();
        },
        deleteOpenHour: function (day, system) {
            if (day == "" || system == "") {
                alert("Please fill in the required fields");
                return;
            }
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
        refresh: function () {
            if (this.system_selected != "") {
                for (var i = 0; i < this.system_list.length; i++) {
                    if (this.system_list[i].system_value == this.system_selected) {
                        const sys = this.system_list[i].system_obj;
                        document.getElementById("monthlyFeeCell").textContent = sys.monthlyFee;
                        document.getElementById("feePer15MinsCell").textContent = sys.feePer15Mins;
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
                            if (this.all_openhours[j].system == sys) {
                                const oh = this.all_openhours[j];
                                if (oh.day == "Sunday") {
                                    document.getElementById("sunOpen").textContent = oh.startTime + " - " + oh.endTime;
                                } else if (oh.day == "Monday") {
                                    document.getElementById("monOpen").textContent = oh.startTime + " - " + oh.endTime;
                                } else if (oh.day == "Tuesday") {
                                    document.getElementById("tueOpen").textContent = oh.startTime + " - " + oh.endTime;
                                } else if (oh.day == "Wednesday") {
                                    document.getElementById("wedOpen").textContent = oh.startTime + " - " + oh.endTime;
                                } else if (oh.day == "Thursday") {
                                    document.getElementById("thuOpen").textContent = oh.startTime + " - " + oh.endTime;
                                } else if (oh.day == "Friday") {
                                    document.getElementById("friOpen").textContent = oh.startTime + " - " + oh.endTime;
                                } else {
                                    document.getElementById("satOpen").textContent = oh.startTime + " - " + oh.endTime;
                                }
                            }
                        }

                        this.clearEditOpenHours();
                        this.clearEditSpecs();

                        break;
                    }
                }
            }
        }
    }
};