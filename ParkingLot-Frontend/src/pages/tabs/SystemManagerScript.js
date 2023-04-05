export default {
    data() {
        return {
            system_options: [
                {
                    system_value: "System1",
                    label: "System1",
                },
                {
                    system_value: "System2",
                    label: "System2",
                },
            ],
            system_value: "",

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
            day_value: "",

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
                    spec_value: "Max stay",
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
                    spec_value: "Number of monthly floors",
                    label: "Number of floors for monthly users",
                },
                {
                    spec_value: "Number of monthly spots per floor",
                    label: "Number of parking spots for monthly users per floor",
                },
                {
                    spec_value: "Number of garages",
                    label: "Number of garages",
                }
            ],
            parkingspecs_value: "",

            spec_input: "",
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
};