<template>
    <div>
        <CustomHeader />
        <div class="pageBody">
            <div class="fieldset" style="width: 50%;">
                <span class="fieldsetHeader">Current Capacity</span>
                <div class="fieldsetBody">
                    Regular Cars: 47/250
                    <br />
                    Large Cars: 4/20
                </div>
            </div>

            <div class="fieldset" style="width: 50%;">
                <span class="fieldsetHeader">Tickets</span>
                <div class="fieldsetBody">
                    <Button text="Enter" color="grey" />
                    <Button text="Exit" color="grey" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import CustomHeader from '../components/CustomHeader.vue'
import Button from '../components/Button.vue'

export default {
    name: "HomePage",
    components: {
        CustomHeader,
        Button
    }
}
</script>

<style>
.pageBody {
    margin: 40px;
}

.fieldset {
    color: rgb(233, 231, 227);
    margin-left: 100px;
    margin-bottom: 40px;
}

.fieldsetHeader {
    border: 1px solid rgb(14, 14, 14);
    padding: 5px;
    background-color: rgb(79, 86, 94);
}

.fieldsetBody {
    border: 1px solid rgb(14, 14, 14);
    border-radius: 0px 10px 0px 10px;
    padding: 20px;
    background-color: rgb(79, 86, 94);
    min-height: 120px;
}
</style>
