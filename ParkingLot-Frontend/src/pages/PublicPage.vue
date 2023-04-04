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
}

div.tabBody {
  display: none;
}

div.tabBody[active="true"] {
  display: block;
}
</style>
