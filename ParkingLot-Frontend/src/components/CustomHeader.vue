<template>
    <header style="display: flex; align-items: center;">
        <!--img src="../assets/PLS_logo.png" alt="Logo" height="40px" width="auto"-->
        <div v-for="tab in tabs">
            <TabHeader v-bind:id="'tabHeader_' + tab.id" :title="tab.title" />
        </div>
        <div id="connection">Login</div>
    </header>
</template>

<script>
import Button from './Button.vue'
import TabHeader from './TabHeader.vue'

export default {
    name: "customHeader",
    components: {
        Button,
        TabHeader
    },
    props: {
        tabs: Array
    }
}
</script>

<style scoped>
header {
    display: block;
    width: 100%;
    background-color: rgb(41, 41, 46);
    font-size: 24px;
    color: rgb(233, 231, 227);
}

#connection {
    margin-left: auto;
    padding: 20px 20px;
}

#connection:hover {
    cursor: pointer;
    background-color: rgb(53, 53, 58);
}
</style>
