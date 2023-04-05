export default {
    data() {
        return {
            staff_options: [
                {
                    staff_value: "Staff1",
                    label: "Staff1",
                },
                {
                    staff_value: "Staff2",
                    label: "Staff2",
                },
            ],
            staff_value: "",

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

            day_value1: "",
            day_value2: "",
            start_time1: "",
            end_time1: "",
            start_time2: "",
            end_time2: "",

            timeslot_options: [
                {
                    timeslot_value: "MON 8:00 AM - 12:00 PM",
                    label: "MON 8:00 AM - 12:00 PM",
                },
                {
                    timeslot_value: "TUE 8:00 AM - 12:00 PM",
                    label: "TUE 8:00 AM - 12:00 PM",
                }
            ],
            timeslot_selected: "",
        };
    },
};
