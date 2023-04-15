<template>

  <el-card class = "page" id = "banner" body-style="padding: 0px; margin: 0px;">
    <div class= "container" id = "headline">
      <img src = "../../assets/parking.png" alt = "Parking Lot" id = "parkingImg">
      <div class ="container" id = "welcomeMsg" style="margin-right:0%; ">
        <div id = "welcomeTo" style= "width: auto; min-width: 200px; left: 6%">
        <p style= "font-size: 120%; "> WELCOME TO THE</p>
        </div>
        <div id = "logoImg" style="width: 700px;">
        <img src = "../../assets/McGill_logo.png" alt = "McGill logo" id = "mcgillLogoImg">
        </div>
        <div id = "parkingLotBold" style = "left:-35%; bottom: -80px; min-width:500px;">
        <span class = "bolded" > PARKING LOT </span>
        </div>
      </div>
    </div>
  </el-card>

  <div class= "container" id = "tabs" style=" box-shadow: 3px 2px 10px grey; margin-bottom: 5%">
      <el-tabs 
        v-model="activeName"
        type="card"
        class="demo-tabs"
      >
        
        <el-tab-pane label="Get a Ticket" name="first"> 
            <span style="margin: 0%; padding: 0px; align: left; font-size: 60%;margin-left: 3%;"> Select a Ticket </span>
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
                                <el-text class="mx-1"> Tickets left: {{regLeft}} </el-text>            
                                <div class = "container" id = "regularButton">
                                    <el-button id = "regularTicketButton" type = "info" round @click= "createRegTicket()" > Reserve </el-button>
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
                                <el-text class="mx-1"> Tickets left: {{largeLeft}} </el-text>            
                                <div class = "container" id = "largeButton">
                                    <el-button id = "largeTicketButton" type = "info" round @click= "createLargeTicket()"> Reserve </el-button>
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
          <div id="payment-info" style="display:flex; flex-direction: column;">
            <span id style="margin: 0%; padding: 0px; align: left; font-size: 60%; margin-left:5%;"> Pay for an Existing Ticket </span>
            
            <div class="container" id="payments">
                <div class = "container" style = " margin-left:5%;top:-200px;" id="ticketIDInputs">
                    <label style="margin-right: 1%; font-size: 55%; color: #000000" for = "ticketID" >Ticket ID:</label>
                    <el-input v-model="ticketID" id = "ticketID" name = "ticketID" style="width: 100%;" placeholder="Input Ticket ID" clearable />
                    <el-button id = "payTicketButton" type="info" round style="width: 100%; margin-top: 10%"> Pay Now </el-button>
                </div>
                <div class = "container" style=" margin-top: 1%;" id="fakeTicketDisplay">
                    <div class = "container" id = "ticketOutline">
                        <img src="../../assets/Ticket.png" class="image" style="width: 50%; margin-left: 25%"/>
                    </div>
                    <div class = "container" id= "ticketParameters">
                      <p style = "font-size: 50%; margin-left:28px;"> From: {{entryTime}} <br>
                         To: 15  <br>
                         $/15 min: {{pls.feePer15m}} <br>
                         <b>AMOUNT DUE: ${{totalCost}}</b></p>
                    </div>
                </div>
            </div>
          </div>
        </el-tab-pane>
        </el-tabs>
    </div>

</template>

<script src="./HomePageScript.js">
</script>

<style>

.bolded{
    font-weight: 700;
    font-size: 60px;
    
}

#mcgillLogoImg{
  width: 50%;
  margin: -20%;
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
  text-align: left;
  color: white;
}

#welcomeMsg{
  position: absolute;
  font-family: monaco;
}

.demo-tabs > .el-tabs__content {
  padding: 5%;
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
  left: 37%;
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