<template>
    <modal v-model="showDialog" v-if="showDialog" classesType="kasse" size="dialog" type="hinweis" customModalMargin="5% auto">
        <div class="modal-body">
            <p>{{bodyText}}</p>
        </div>
        <div class="separator"></div>
        <div class="modal-footer">
            <button class="button button-grey" style="margin-left: 10px;" @click="showDialog = false">Cancel</button>
            <a class="button" style="margin-left: 10px;"  @click="onAcceptClick()">OK</a>
        </div>
    </modal>
</template>

<style scoped>

</style>

<script>
    import Modal from './Modal'

    export default {
        components: { Modal },
        name: "YesNoModal",
        props: {
            value: Boolean,
            bodyText: String,
            acceptFunction: Function,
        },
        data () {
            return {
                showDialog:false
            }
        },
        watch: {
            showDialog () {
                this.$emit('input', this.showDialog)
            },
            value() {
                this.initialize()
            }
        },
        methods: {
            initialize() {
                this.showDialog = this.value
            },

            onAcceptClick() {
                this.showDialog = false
                this.acceptFunction()
            }
        },
        created() {
            this.initialize()
        }
    }
</script>