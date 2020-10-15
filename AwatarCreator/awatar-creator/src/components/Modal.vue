<template>
    <div class="modal" id="modalDialog">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">{{title}}</h4>
                    <template v-if="closeable">
                        <button type="button" class="close" data-dismiss="modal" @click="cancelModal">&times;</button>
                    </template>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    {{text}}
                </div>

                <!-- Modal footer -->
                <template v-if="showFooter">
                    <div class="modal-footer" v-if="showFooter">
                        <button type="button" class="btn btn-danger" data-dismiss="modal" @click="cancelModal">Abort</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" @click="confirmModal">Confirm</button>
                    </div>
                </template>
            </div>
        </div>
    </div>
</template>

<script>
    //import JQuery from 'jquery'
    //let $ = JQuery

    export default {
        name: 'Modal',
        props: {
            value: Boolean,
            title: String,
            text: String,
            closeable: Boolean,
            showFooter: Boolean,
        },
        data() {
            return {
            }
        },
        methods: {
            cancelModal() {
                this.$emit('input', false)
            },
            confirmModal() {
                console.log("Modal-Method: confirmModal")
                this.$emit('input', true)
            }
        },
        watch: {
        },
        created() {
        },
        destroyed() {
            console.log("Component Modal destroyed")
        }
    }
</script>