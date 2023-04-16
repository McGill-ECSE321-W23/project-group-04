<template>
  <div>
    <Header :tabs="tabs" :is-logged-in="loggedIn" :is-staff="isStaff"></Header>
    <div class="pageBody">
      <div id="page_home" class="tabBody" active="true">
      <HomePage />
      </div>
      <div id="page_services" style= "padding: 60px 80px" class="tabBody">
        <ServiceAppointmentTab />
      </div>
      <div id="page_profile" style= "padding: 60px 80px" class="tabBody" v-if="loggedIn">
        <user-profile></user-profile>
      </div>
    </div>
  </div>
</template>

<script src="./PublicPageScript.js">
</script>

<style scoped>

div.pageBody {
  display: block;
  min-height: calc(100vh - 59px);
  max-width: 100vw;
  background-color: #f5f5f5;
  color: #333;
}

div.tabBody {
  display: none;
}

div.tabBody[active="true"] {
  display: block;
}
</style>
