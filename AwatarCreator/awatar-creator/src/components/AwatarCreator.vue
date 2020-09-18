<template>
    <div>
        <!-- Loading Screen -->
        <template v-if="showLoadingScreen">
            <LoadingScreen msg="Please wait..."></LoadingScreen>
        </template>

        <!--Exmample Info Box -->
        <!-- <InfoBox msg="My Infobox" v-bind:closeable="true" type="error"></InfoBox>-->

        <Modal v-model="modalConfirmed"
               :title="modalTitle"
               :text="modalText"
               :closeable="modalClosable"
               :show-footer="showModalFooter">
        </Modal>
        {{modalConfirmed}}


        <div>
            <div class="btn" @click="triggerRequest()"> Click me</div>
            <button class="btn btn-primary" @click="openModal()" data-toggle="modal" data-target="#modalDialog" data-keyboard="true">Open modal (true)</button>
            <button class="btn btn-primary" @click="openModal2()" data-toggle="modal" data-target="#modalDialog" data-keyboard="false">Open modal (false)</button>


        </div>
    </div>
</template>

<style scoped>

</style>

<script>
    import RequestService from "../services/RequestService";
    import LoadingScreen from "./LoadingScreen";
    //import InfoBox from "./InfoBox";
    import Modal from "./Modal";

    export default {
        name: "AwatarCreator",
        components: {Modal, LoadingScreen},
        data() {
            return {
                applicationData: null,
                showLoadingScreen: false,


                modalTitle: '',
                modalText: '',
                modalClosable: true,
                showModalFooter: false,
                modalConfirmed:false, //Returning Result of modal

            }
        },
        watch: {

        },
        methods: {
            triggerRequest () {
                this.showLoadingScreen = true
                RequestService.webRequest('https://www.thetopsites.net/article/52483232.shtml')
                //RequestService.webRequest(RequestService.contextPath + '/test')
                    .then(function (res) {
                        this.showLoadingScreen = false
                        console.log(res)
                    }.bind(this))
                    .catch(function (reason) {
                        this.showLoadingScreen = false
                        if (reason.response) {
                            const data = JSON.parse(reason.response.data)
                            console.log(data.errorMsg)
                            //UtilService.showError(data.errorMsg)
                        } else {
                            console.log(reason)
                        }
                    }.bind(this))

            },

            openModal() {
                this.modalTitle="My first modal"
                this.modalText="Das ist der Modaltext"
                this.modalClosable=true
                this.showModalFooter=true
            },

            openModal2() {
                this.modalTitle="asdfasdf"
                this.modalText="asdasdasdasdas"
                this.modalClosable=false
                this.showModalFooter=false

            }

        }

    }



</script>

