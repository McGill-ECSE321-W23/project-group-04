<template>
    <div class="tabHeader">
        {{ text }}
    </div>
</template>

<script>
export default {
    name: "TabHeader",
    props: {
        text: String
    }
}
</script>

<style scoped>
.tabHeader {
    margin: 0px 20px;
}
</style>
