<template>
    <div class="alert alert-info" role="alert">
        <div class="spinner-border" role="status">
            <span class="sr-only">Loading...</span>
        </div>
        <p>{{msg}}</p>
    </div>
</template>

<script>
    export default {
        name: "LoadingModal",
        props: { msg: String, },
    }
</script>

<style scoped>

</style>