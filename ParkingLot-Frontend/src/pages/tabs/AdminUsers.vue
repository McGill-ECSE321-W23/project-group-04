<template>
    <div id="heading" style="width:100%; text-align: center; margin-top: 20px; margin-bottom: 20px;"><h1 > Update Reservations </h1></div>
        <div class="container" id="list">
            <el-card class="box-card">
                <el-scrollbar height="70vh">
                    <div id="list-item" style="width:100%;text-align: left; padding:0%;">
                    <p v-for="res in reservations" :key="res.id" class="scrollbar-demo-item" style="height:200px;"> 
                    <div id="res_id" style="font-size: large; text-align: left; height: 85%; margin-left: 1%; width:20%; ">
                        <div id="label_id" style="font-weight:bold; font-size: large; text-align: left; margin-left: 1%; width:20%; ">Reservation ID# </div>
                        <div id=id_no style="font-size:30px; height: 100%" >{{res.id}}</div>
                    </div>
                    <div id="person_info" style="height: 30%; width:20%">
                        <div id="customer" style="text-align: left;font-weight:bold;">Customer: 
                        </div>
                        <div id="name" style="text-align: left;">{{ res.personName }}
                        </div>
                        <div id="number" style="text-align: left;"> {{ res.phoneNumber }}
                        </div>
                        <div id="email" style="text-align: left;">Email: {{ res.personEmail }}
                        </div>
                    </div>
                    <div id="car_info" style="height: 30%; width:20%;">
                        <div id="car-label" style="text-align: left; font-weight:bold;">Cars: 
                        </div>
                        <div id="car1" style="text-align: left; ">{{res.licensePlate1}}
                        </div>
                        <div id="car2" style="text-align: left;  ">{{res.licensePlate2}}
                        </div>
                        
                    </div>
                        <div id="res_floor" style="text-align: left; height: 30%; width:15%;">
                            <label>Floor </label>
                            <el-select v-model="res.floor" class="m-1" placeholder="Select" size="large">
                                <el-option
                                v-for="f in floors"
                                :key="f.value"
                                :label="f.label"
                                :value="f.value"
                                />
                            </el-select>
                        </div>
                        <div id="res_spot_no" style="text-align: left; margin-left: 10px; height: 30%; width:15%;"><label>Number </label>
                            <el-select v-model="res.spotNumber" class="m-2" placeholder="Select" size="large">
                                <el-option
                                v-for="p in spots"
                                :key="p.value"
                                :label="p.label"
                                :value="p.value"
                                />
                            </el-select>
                            
                        </div>
                        
                        <div id="update-button" style="margin-bottom:100px; margin-left: 10px; margin-right: 10px; ">
                        <el-button type="info" @click="updateReservation(res.id, res.floor, res.spotNumber)">Update Info</el-button>
                        </div>
                        
    
                     </p>
                    </div>
                </el-scrollbar>
            </el-card>
        </div>
    </template>
    
    <script src="./AdminUsersScript.js" lang="js"></script>
    
    <style scoped>
    #list {
        width: 100%;
        height: 100%;
        align-items: center;
    }
    
    .scrollbar-demo-item {
      display: flex;
      align-items: center;
      justify-content: left;
      height: 50px;
      margin: 10px;
      text-align: center;
      border-radius: 4px;
      background-color: lightgray;
      color:black;
    }
    .container {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    .list-item {
        display: flex;
        
        flex-direction: row;
    }
    
    .box-card {
      width: 80%;
      max-width: 1000px; /* add this line to limit the maximum width of the card */
    }
    .person_info {
        display: flex;
        flex-direction: column;
    }
    </style>