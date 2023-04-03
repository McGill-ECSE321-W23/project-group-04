<template>
    <div class="tabHeader">
        {{ title }}
    </div>
</template>

<script>
export default {
    name: "TabHeader",
    props: {
        title: String
    }
}
</script>

<style scoped>
.tabHeader {
    padding: 20px 20px
}

.tabHeader:hover {
    cursor: pointer;
    background-color: rgb(53, 53, 58);
}

.tabHeader[active] {
    background-color: rgb(53, 53, 58);
}
</style>
