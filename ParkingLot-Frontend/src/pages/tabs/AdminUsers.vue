<template>
    <div id="heading" style="width:100%; text-align: center; margin-top: 20px; margin-bottom: 20px;"></div>
        <div class="container" id="list">
            <el-card class="box-card" style="height:700px;">
                <el-tabs :tab-position="tabPosition" style="height: 400px;" class="demo-tabs">
                <el-tab-pane label="Create A User" style="width: auto;" >
                    <div id="user-input" style="width:100%;text-align: center; padding:0%;font-size:18px; margin-top: 5%;">
                        <div id="name">
                        <label for="person_name" >Name:  </label>
              <el-input type="text" v-model="person_name" style="font-size:15px;margin-left:50px; align-self: flex-end; width: 40%;" clearable />
            </div>
              <br>
              <div id="phone" style="margin-top:30px;">
              <label for="person_number">Phone:  </label>
              
              <el-input clearable type="tel" v-model="person_number" name="person_number" 
              placeholder="123-456-7890" style="font-size:15px;width: 40%; margin-left: 50px;"/>
              </div>
             
              <div id="create-button" style="margin-top:100px; margin-left: 10px; margin-right: 10px; ">
                        <el-button type="info" style="width:100px;" @click="createPerson(person_name, person_number)">Create</el-button>
                        </div>
                        
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="Update User Info"  style="width: auto;">
                        <div id="user-input" style="width:100%;text-align: center; padding:0%;font-size:18px; margin-top: 5%;">
                        
              <div id="id" style="margin-top:30px;">
              <label for="person_id">ID:  </label>
              <el-input clearable type="text" v-model="person_id" name="person_id" 
                style="font-size:18px;width: 40%; margin-left: 50px;"/>
              </div>
                        <div id="find-button" style="margin-top:40px; margin-left: 10px; margin-right: 10px; ">
                        <el-button type="info" @click="findPerson(person_id)" style="width:100px;">Find By ID</el-button>
                        </div>
                        <el-card v-show="found" class="box-card" style="height:250px; margin-top:20px; width:100%; 
                        justify-content: center;">
                        <div id ="update_info" style="display:flex; flex-direction: row;">
                        <p  style="text-align: center; width:50%">
                        <div id="person_info" style="height: 30%; width:100%">
                        <div id="customer" style="text-align: left;font-weight:bold;">Found User: 
                        </div>
                        <div id="name" style="text-align: left;">{{ found_user.name }}
                        </div>
                        <div id="number" style="text-align: left;"> {{ found_user.phoneNumber }}
                        </div>
                        
                        </div>
                        </p>   
                        <p  style="text-align: center; width:50%; ">
                        <div id="person_info" style="width:100%;  " >
                        <label for="new_name">Update Name: </label>
                        <el-input clearable type="text" v-model="new_name" style="align-self: flex-end; width: 60%;"/>
                        <br><br>
                        <label for="new_phone">Update Phone: </label>
                        <el-input clearable type="tel" v-model="new_phone" name="new_phone " 
                         placeholder="123-456-7890" pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}" required style="width: 60%;"/>   
                         <br><br>
                         
                         </div>
                         <div id="update-button" style=" margin-top:10px; margin-left: 10px; margin-right: 10px; ">
                        <el-button type="info" style="width:100px;" @click="updatePerson(found_user.personID, new_name, new_phone)">
                            Update </el-button>
                        </div>
                        </p>  
                    </div> 
                    </el-card>
                    
                        
                    

            </div>
                    </el-tab-pane>
                    <el-tab-pane label="View All Users "  style="width: auto;">
                       <div id="header" style="display:flex; flex-direction: row; font-size: 20px;"> <p style="margin-left:1%;"> ID#</p> <p style="margin-left:27%;">Name</p> 
                         <p style="margin-left:23%;">Phone</p></div>
                        <el-scrollbar height="70vh">
                    <div id="list-item" style="width:100%;text-align: center;font-size:15px;">
                    <p v-for="user in persons" :key="user.id" class="scrollbar-demo-item" style="height:50px;"> 
                   
                        <div id="person_info" style="height: 40%; width:40%; font-size: 20px;">
                        <div id="person_id" style="text-align: left;">{{ user.personID }}
                        </div> </div>
                    
                    <div id="person_info" style="height: 40%; width:40%; font-size: 20px;">
                        <div id="person_name" style="text-align: left;">{{ user.name }}
                        </div> </div>

                        <!-- <div id="person_info" style="height: 40%; width:40%; font-size: 20px;">
                        <div id="name" style="text-align: left;">{{ user.personEmail }}
                        </div> </div> -->

                        <div id="person_info" style="height: 40%; width:40%; font-size: 20px;">
                        <div id="person_phone" style="text-align: left;">{{ user.phoneNumber }}
                        </div> </div>
                    
                     </p>
                    </div>
                </el-scrollbar>
                    </el-tab-pane>
                    
                    <el-tab-pane label="Update Reservations"  style="width: auto;">
                <el-scrollbar height="70vh">
                    <div id="list-item" style="width:100%;text-align: left; padding:0%;font-size:14px;">
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
            </el-tab-pane>
            </el-tabs>
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
      background-color: rgb(255, 255, 255);
      box-shadow: 0px 0px 5px black;
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
      box-shadow: 0px 0px 5px black;
    }
    .person_info {
        display: flex;
        flex-direction: column;
    }
    </style>