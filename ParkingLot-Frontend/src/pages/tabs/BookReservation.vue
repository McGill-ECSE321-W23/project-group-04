<template>
  <div id="heading" style="width:100%; text-align: center; margin-top: 20px;"><h1 > Client Bookings </h1></div>
    <div id="input">
      <div class="container" id="person_info" style="width:40%; " >
          <label for="person_name">Name: </label>
              <input type="text" v-model="person_name" style="align-self: flex-end; width: 60%;">
              <br>
              <label for="person_number">Phone: </label>
              
              <input type="tel" v-model="person_number" name="person_number " 
              placeholder="123-456-7890" pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}" required style="width: 60%;">       
      </div>
      <div class="container" id="car_info" style="width:41%; ">
          <label for="license_plate">License Plate:</label>
              <input type="text" name="license_plate" v-model="license_plate" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"  style="width: 60%;">
              <br>
              <label for="car_make">Make: </label>
              <input type="text" v-model="car_make" name="car_make" style="width: 60%;">       
              <br>
              <label for="car_model">Model: </label>
              <input type="text" v-model="car_model" name="car_model" style="width: 60%;">  
          </div>
      </div>
      <div class="container" id="tabs">
          <el-card class="box-card">
   
      <el-tabs :tab-position="tabPosition" style="height: 400px" class="demo-tabs">
      <el-tab-pane label="Reservations" >
          <h1 style="font-weight:bold; font-size: 25px;">Book a Reservation</h1>
          
      <div id="reservation-info">
          <div id="parkingspot-info" style="margin-top:40px; ">
              <div id="floor" >
          <label for="floor" >Floor:</label>
      <el-select v-model="selectedFloor" class="m-2" placeholder="Select"  id="floor"  >
        <el-option
           v-for="f in floors"
           :key="f.value"
            :label="f.label"
            :value="f.value"
            />
    </el-select>
  </div>
  <br>
  <div id="number" style="margin-top:30px; "  >
    <label for="number" >Number:</label>
    <el-select v-model="selectedSpot" class="m-2" placeholder="Select" id ="number" >
      <el-option
           v-for="p in spots"
           :key="p.value"
            :label="p.label"
            :value="p.value"
            />
    </el-select>
  </div>
      </div>
      <div id="reservation-dates" style ="margin-top: 40px; width:70%;">
          <label >Select Dates:</label>
  
            <div class="demo-date-picker">
      <div class="block">
        
        <el-date-picker
          v-model="value1"
          type="daterange"
          range-separator="To"
          start-placeholder="Start date"
          end-placeholder="End date"
          :size="size"
        />
      </div>
  
    </div>
      </div>
      
  </div>
  <div id="book-button" style ="width:100%;margin-top: 100px;">
  <el-button type="info" @click="createReservation(person_number, person_name, license_plate, car_make, car_model, 
          selectedFloor, selectedSpot, value1, value1)" plain style="margin-top:20px; width: 50%; display: block;margin: 0 auto; margin-top: 20px;">Book</el-button>
  </div>
      </el-tab-pane>
      
      <el-tab-pane label="Services" > <h1 style="font-weight:bold; font-size: 25px;">Book a Service</h1>
          
          <div id="service-info" >
              <div id="make-selections" style="margin-top:20px;width:50%;">
                  <div id="service"  style ="margin-top: 20px;" >
              <label for="service" style="width:40%;" >Select a Service:</label>
          <el-select v-model="selectedService" class="m-2" placeholder="Select"  id="service"   >
           <el-option
           v-for="s in services"
           :key="s.value"
            :label="s.label"
            :value="s.value"
            />
          </el-select>
      </div>
      <div id="garage" style ="margin-top: 20px;"  >
              <label for="garage" style="width:40%;" >Select a Garage:</label>
          <el-select v-model="selectedGarage" class="m-2" placeholder="Select"  id="garage"   >
            <el-option
           v-for="g in garages"
           :key="g.value"
            :label="g.label"
            :value="g.value"
            />
          </el-select>
      </div>
      <div id="timeslot"  style ="margin-top: 20px;">
              <label for="timeslot" style="width:40%;">Select a Timeslot:</label>
              <el-time-select
      v-model="value4"
      start="08:30"
      step="00:45"
      end="18:30"
      placeholder="Select time"
      style ="width:35%"
    />
      </div>
      <div id="book-button" style ="width:100%;margin-top: 0px; margin-left: 10%;">
      <el-button type="info" @click="createOfferedService(person_number, person_name, license_plate, car_make, car_model, 
            selectedService, selectedGarage, value4, value3)" plain style=" width: 70%; display: block; margin-top: 100px;">Book</el-button>
      </div>
  
  
      </div>
      <div id="calendar"  style="width:50%;">
          <el-calendar v-model="value3" />
      </div>    
      </div>
      
      </el-tab-pane>
  
    </el-tabs>
  </el-card>
  </div>
  
    </template>
  
  <script src="./BookReservationScript.js">
  </script>
  
    <style scoped>
  
    #input {
      width: 100%;
      display:flex;
      flex-direction: row;
      align-items: center;
      justify-content: center;
      padding: 50px 40px;
      border-radius: 10px;
    }
    label {
      width:20%;
      display: inline-block;
    }
    #person_info {
      width:40%;
      display:inline-block;
  
    }
    #car_info {
      width:40%;
      display:inline-block;
  
    }
    .demo-tabs > .el-tabs__content {
    padding: 32px;
    color: #6b778c;
    font-size: 32px;
    font-weight: 600;
  }
  
  .el-tabs--right .el-tabs__content,
  .el-tabs--left .el-tabs__content {
    height: 100%;
  }
  .box-card {
  align-self: center;
  width:80%;
  
  
  }
  #tabs {
  display: flex;
  align-items: center;
  width:100%;
  justify-content: center;
  }
  #reservation-info{
  display: flex;
  flex-direction: row;
      width:100%;
  }
  #parkingspot-info {
      width: 50%;
  }
  #reservation-dates {
      width:50%;
  }
  
  #service-info{
  display: flex;
  flex-direction: row;
  width:100%;
  }
  
  #calendar .el-calendar {
    transform: scale(0.6) translate(-40%, -40%);
    width: 800px;
  }
    </style>
    