<template>
    <div style="margin-top: 50px ">
        <h2>Testergebnisse:</h2>
        <div class="row">
            <div class="alert fade show row" :class="alertType" role="alert">
                <textarea v-model="resultLog" style="width: 100%; min-width: 100%" rows="25"></textarea>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "TestExecutionResult",
        props: {
            type: String,
            resultLog: String,
        },
        computed: {
            alertType() {
                let boxTypeStyle = ''
                if (this.closeable)          { boxTypeStyle += 'alert-dismissible ' }
                if (this.type === 'success') { boxTypeStyle += 'alert-success ' }
                if (this.type === 'error')   { boxTypeStyle += 'alert-danger ' }
                if (this.type === 'warning') { boxTypeStyle += 'alert-warning ' }
                return boxTypeStyle
            }
        }
    }
</script>

<style scoped>

</style>