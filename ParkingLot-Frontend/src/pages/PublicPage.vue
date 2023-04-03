<template>
    <div class="page">
        <CustomHeader :tabs="tabs" />
        <div class="tabBody" v-bind:id="'tabBody_' + tab.id" v-for="tab in tabs">
            {{ tab.title }}
        </div>
    </div>
</template>

<script src="./PublicPageScript.js">
</script>

<style scoped>
.tabBody {
    display: none;
    border: 1px solid black;
    margin: 40px;
}

.tabBody[active] {
    display: block;
}
</style>
