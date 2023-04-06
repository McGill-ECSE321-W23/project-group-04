<template>

  <el-card class = "page" id = "banner" body-style="padding: 0px; margin: 0px;">
    <div class= "container" id = "headline">
      <img src = "../../assets/parking.png" alt = "Parking Lot" id = "parkingImg">
      <div class ="container" id = "welcomeMsg">
        <p style= "font-size: 120%; bottom: 60px;"> WELCOME TO THE</p>
        <img src = "../../assets/McGill_logo.png" alt = "McGill logo" id = "mcGillLogoImg">
        <span class = "bolded" style = "bottom: 5%; left: -11%"> PARKING LOT </span>
      </div>
    </div>
  </el-card>

  <div class= "container" id = "tabs" style=" box-shadow: 3px 2px 10px grey; margin-bottom: 5%">
      <el-tabs 
        v-model="activeName"
        type="card"
        class="demo-tabs"
        @tab-click="handleClick">
        
        <el-tab-pane label="Get a Ticket" name="first"> 
            <span style="margin: 0%; padding: 0px; align: left; font-size: 60%;"> Select a Ticket </span>
            <div class="container" id="tickets">  
                <div class="container" id="regularTicket" style="margin-top: 1%;"> 
                    <el-row>
                    <el-col
                        :span="30"
                        :offset="index > 0 ? 2 : 0"
                    >
                    <el-card :body-style="{ padding: '0px' }">
                        <img src="../../assets/SmallCar.png" class="image" id ="imgReg"/>
                        <div style="text-align: center;padding: 14px">
                            <span style="font-weight:bold; font-size:55%"> REGULAR CAR</span>
                            <div class="bottom" id = "bottomReg">
                                <el-text class="mx-1"> # Left </el-text>
            
            	                 <!-- ADD NUMBR TO DO--> 
            
                                <div class = "container" id = "regularButton">
                                    <el-button type = "info" round>Reserve</el-button>
                                </div>
                            </div>
                        </div>
                    </el-card>
                    </el-col>
                    </el-row>
                 </div>

 		        <div class="container" id="largeTicket" style="margin-top: 1%; margin-left: 2%;">  
                    <el-row>
                    <el-col
                        :span="11"
                        :offset="index > 0 ? 2 : 0"
                    >
                    <el-card :body-style="{ padding: '0px' }">
                        <div class = "container" id = "jeepImg">
                            <img src="../../assets/LargeCar.png" class="image" id ="imgLarge" />
                        </div>
                        <div style="text-align: center;padding: 14px">
                            <span style="font-weight:bold; font-size:55%"> LARGE CAR</span>
                            <div class="bottom" id = "bottomLarge">
                                <el-text class="mx-1"> # Left </el-text>
            
            	                <!-- ADD NUMBR TO DO--> 
            
                                <div class = "container" id = "largeButton">
                                    <el-button type = "info" round> Reserve </el-button>
                                </div>
                            </div>
                        </div>
                    </el-card>
                    </el-col>
                    </el-row>
                 </div>
    	    </div>
         </el-tab-pane>

        <el-tab-pane label="Pay" name = "second">
            <span id style="margin: 0%; padding: 0px; align: left; font-size: 60%;"> Pay for an Existing Ticket </span>
            <div class="container" id="payments">
                <div class = "container" style = "position:relative; top:-150px;" id="ticketIDInputs">
                    <label style="margin-right: 1%; font-size: 55%; color: #000000" for = "ticketID" >Ticket ID:</label>
                    <el-input v-model="ticketInput" id = "ticketID" name = "ticketID" style="width: 100%;" placeholder="Input Ticket ID" clearable />
                    <el-button type="info" round style="width: 100%; margin-top: 10%"> Pay Now </el-button>
                </div>
                <div class = "container" style=" margin-top: 1%;" id="fakeTicketDisplay">
                    <div class = "container" id = "ticketOutline">
                        <img src="../../assets/Ticket.png" class="image" style="width: 50%; margin-left: 25%"/>
                    </div>
                    <div class = "container" id= "ticketParameters">
                        <p style = "font-size: 40%"> DATE  <br>
                        from: START TIME  <br>
                        to: END TIME <br>
                        $/hr: COST  <br> 
                        AMOUNT DUE: money
                        </p>            
                        <!-- INPUT DATA -->
            
                    </div>
                </div>
            </div>
        </el-tab-pane>
        </el-tabs>
    </div>

</template>

<script lang="ts" setup>
import { ref } from 'vue'
import type { TabsPaneContext } from 'element-plus'
  
const ticketInput = ref('')
const activeName = ref('first')
const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event) 
}
</script>

<style>

.bolded{
    font-weight: 700;
    font-size: 60px;
    
}

#mcGillLogoImg{
    width: 20%;
    left: -13%;
}

#parkingImg {
    width: 100%;
    padding: 0px;
    margin-bottom: -10px;
}


#imgLarge{
    margin-top: 10%;
    margin-left: 5%;
    margin-right: 10%;
    align: center;
}

#banner{
  position: relative;
  color: white;
}

#welcomeMsg{
  position: absolute;
  bottom: 10%;
  left: -10%;
  font-family: monaco;
}

.demo-tabs > .el-tabs__content {
  padding: 5%;
  top: -50px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
 }

.demo-tabs .el-tabs__item {
  min-width: 50vw;
}

#bottomLarge {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#largeButton {
  padding: 0;
  min-height: auto;
}

#imgLarge {
  width: 88%;
  display: block;
  }
  
#bottomReg {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#regularButton {
  padding: 0;
  min-height: auto;
}

#imgReg {
  width: 100%;
  display: block;
}
  
  #regularTicket{
    width: auto;
  }
  #largeTicket{
    width: auto;
  }
  
#tickets{
    display: flex;
    flex-direction: row;
  }
  
 #fakeTicket {
  position: relative;
  text-align: center;
  color: black;
}

#ticketParameters{
  position: absolute;
  top: 50%;
  left: 40%;
  text-align: center;
  color: black;
}
  
#ticketOutline {
  width: 100%;
  display: block;
}

#tabs {
  margin: 2%;
  border-radius: 15px;
}


#fakeTicketDisplay{
    width: 50%;
    display: inline-block;

}

#ticketIDInputs{
    width: 50%;
    display: inline-block;
    top: 10px;
}

</style>