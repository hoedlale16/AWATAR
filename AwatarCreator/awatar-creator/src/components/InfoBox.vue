<template>
    <div class="alert fade show" :class="alertType" role="alert">
        {{msg}}
        <template v-if="closeable">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </template>
    </div>
</template>

<script>
    export default {
        name: "InfoBox",
        props: {
            type: String,
            msg: String,
            closeable: Boolean,

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