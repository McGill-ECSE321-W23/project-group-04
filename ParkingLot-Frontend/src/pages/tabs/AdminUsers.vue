<template>
<div id="heading" style="width:100%; text-align: center; margin-top: 20px; margin-bottom: 20px;"><h1 > Update Reservations </h1></div>
    <div class="container" id="list">
        <el-card class="box-card">
            <el-scrollbar height="70vh">
                <div id="list-item" style="width:100%;text-align: left; padding:0%;">
                <p v-for="item in 20" :key="item" class="scrollbar-demo-item" style="height:200px;"> 
                <div id="res_id" style="font-weight:bold;text-align: left; height: 70%; margin-left: 1%; ">Reservation ID {{ item }}
                </div>
                <div id="person_info" style="height: 30%; width:20%">
                    <div id="name" style="text-align: left;">Name {{ item }}
                    </div>
                    <div id="number" style="text-align: left;">Phone {{ item }}
                    </div>
                    <div id="email" style="text-align: left;">Email {{ item }}
                    </div>
                </div>
                <div id="car_info" style="height: 30%; width:20%;">
                    <div id="car1" style="text-align: left; ">Car 1
                    </div>
                    <div id="car2" style="text-align: left;  ">Car 2
                    </div>
                    <div id="car3" style="text-align: left;">Car 3
                    </div>
                </div>
                    <div id="res_floor" style="text-align: left; height: 30%; width:15%;">
                        <label>Floor </label>
                        <el-select v-model="value" class="m-1" placeholder="Select" size="large">
                        <el-option
                            v-for="item1 in options"
                            :key="item1.value"
                            :label="item1.label"
                            :value="item1.value"
                           />
                        </el-select>
                    </div>
                    <div id="res_spot_no" style="text-align: left; margin-left: 10px; height: 30%; width:15%;"><label>Number </label>
                        <el-select v-model="value2" class="m-2" placeholder="Select" size="large">
                        <el-option
                            v-for="item2 in options2"
                            :key="item2.value2"
                            :label="item2.label"
                            :value="item2.value2"
                         />
                        </el-select>
                        
                    </div>
                    
                    <div id="update-button" style="margin-bottom:100px; margin-left: 10px; margin-right: 10px; ">
                    <el-button type="info">Update Info</el-button>
                    </div>
                    

                 </p>
                </div>
            </el-scrollbar>
        </el-card>
    </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'

const value = ref('')
const value2 = ref('')
const options = [
  {
    value: 'Option1',
    label: 'Option1',
  },
  {
    value: 'Option2',
    label: 'Option2',
  },
  {
    value: 'Option3',
    label: 'Option3',
  },

]
const options2 = [
  {
    value2: 'Option1',
    label: 'Option1',
  },
  {
    value2: 'Option2',
    label: 'Option2',
  },
  {
    value2: 'Option3',
    label: 'Option3',
  },
]
</script>

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