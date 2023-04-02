<template>
    <header style="display: flex; align-items: center;">
        <img src="../assets/PLS_logo.png" alt="Logo" height="40px" width="auto">
        <TabHeader text="Home" />
        <TabHeader text="Services" />
        <TabHeader text="My Account" />
        <Button text="Login" color="grey" />
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
    }
}
</script>

<style scoped>
header {
    display: block;
    width: auto;
    padding: 20px 40px;
    background-color: rgb(41, 41, 46);
    font-size: 32px;
    color: rgb(233, 231, 227);
}

#btn_Connection {
    float: right;
    position: relative;
    top: 50%;
}

Button {
    margin-left: auto;
}
</style>
