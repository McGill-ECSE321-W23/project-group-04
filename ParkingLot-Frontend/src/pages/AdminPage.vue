<template>
  <div>
    <Header :tabs="tabs" :is-logged-in="true" :is-staff="isStaff"></Header>
    <div class="pageBody">
      <div id="page_system" class="tabBody" active="true">
        <SystemManager />
      </div>
      <div id="page_servicesAndGarages" class="tabBody">
        <ServicesAndGaragesTab />
      </div>
      <div id="page_users" class="tabBody">
        <AdminUsers />
      </div>
      <div id="page_schedules" class="tabBody">
        <StaffSchedule />
      </div>
      <div id="page_bookReservation" class="tabBody">
        <BookReservation />
      </div>
    </div>
  </div>
</template>

<script src="./AdminPageScript.js">
</script>

<style scoped>
div.pageBody {
  display: block;
  min-height: calc(100vh - 59px);
  max-width: 100vw;
  padding: 60px 80px;
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