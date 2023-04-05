<template>
  <div>
    <Header :tabs="tabs"></Header>
    <div class="pageBody">
      <div id="page_home" class="tabBody" active="true">
        home page
      </div>
      <div id="page_services" class="tabBody">
        services page
      </div>
      <div id="page_reservations" class="tabBody">
        <reservations></reservations>
      </div>
      <div id="page_profile" class="tabBody">
        profile page
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
