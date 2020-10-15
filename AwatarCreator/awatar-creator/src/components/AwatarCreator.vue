<template>
    <div v-if="applicationData">
        <!-- Loading Screen -->
        <template v-if="showLoadingScreen">
            <LoadingScreen msg="Please wait..."></LoadingScreen>
        </template>

        <!--Modal dialog-->
        <template v-if="createModal">
            <Modal v-model="modalConfirmed"
                   :title="modalTitle"
                   :text="modalText"
                   :closeable="modalClosable"
                   :show-footer="showModalFooter">
            </Modal>
        </template>

        <a class="btn btn-primary pull-left" @click="showDevSpielwiese = !showDevSpielwiese">Spielwiese anzeigen</a>
        <div class="devSpielwiese" v-if="showDevSpielwiese">
            <h1>Available Step Definitions:</h1>
            <ul>
                <li>GIVEN: {{this.availableStepDefinitions.GIVEN}}</li>
                <li>WHEN: {{this.availableStepDefinitions.WHEN}}</li>
                <li>THEN: {{this.availableStepDefinitions.THEN}}</li>
            </ul>

            <div style="text-align: center">
                <div class="btn btn-primary mr-3" @click="initializeAppData()"> Reinit Application</div>

                <button class="btn btn-primary mr-3" @click="openModalDialog('Modal dialog', 'This is a modal dialog', false,true)" data-toggle="modal" data-target="#modalDialog" data-backdrop="static" data-keyboard="false">Open static modal</button>
                <button class="btn btn-primary mr-3" @click="openModalDialog('Dialog', 'This is a closable dialog', true,false)"    data-toggle="modal" data-target="#modalDialog" data-backdrop="static" data-keyboard="false">Open closable dialog</button>
                <div class="btn btn-primary mr-3" @click="openModalDialog('Dialog', 'This is a Dialog from DIV',false,true)"        data-toggle="modal" data-target="#modalDialog" data-backdrop="static" data-keyboard="false">Div-Buton</div>
            </div>
        </div>

        <div style="text-align: center;">
            <h1>TODO: Hier entsteht der AWATAR Creator</h1>
            <p>Ein Tool zur Erstellung von automatisierten Tests mit AWATAR</p>
        </div>


    </div>
    <div v-else>
        <InfoBox v-if="noBackendAvailable" msg="AWATAR Backend Server not reachable" v-bind:closeable="false" type="error"></InfoBox>
    </div>
</template>

<style scoped>
 .devSpielwiese {
     background-color: grey;
 }
</style>

<script>
    import RequestService from "../services/RequestService";
    import LoadingScreen from "./LoadingScreen";
    import InfoBox from "./InfoBox";
    import Modal from "./Modal";

    export default {
        name: "AwatarCreator",
        components: {Modal, LoadingScreen, InfoBox},
        data() {
            return {
                applicationData: null,
                noBackendAvailable: false,
                availableStepDefinitions: null,
                showLoadingScreen: false,


                createModal: false, //Flag to create new modal dialog
                modalTitle: '',
                modalText: '',
                showModalFooter: false,
                modalConfirmed: null, //Returning Result of modal


                showDevSpielwiese: false
            }
        },
        watch: {
            modalConfirmed(newVal) {
                if(newVal) {
                    console.log("modalConfirmed by User")
                    this.createModal = false //Destroy currently modal
                }
            }
        },
        methods: {
            triggerRequest () {

                this.showLoadingScreen = true
                RequestService.webRequest('https://www.google.at/')
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

             openModalDialog(title, text, closable,showModalFooter) {
                this.modalTitle=title
                this.modalText=text
                this.modalClosable=closable
                this.showModalFooter=showModalFooter

                 //Create new Modal and reset previous confirm result
                this.createModal = true
                this.modalConfirmed = null

            },
            initializeAppData () {
                console.log("initializeAppData")

                this.showLoadingScreen = true
                RequestService.webRequest(RequestService.contextPath + '/creator/stepDefinitions')
                .then(function (res) {
                    this.showLoadingScreen = false
                    this.applicationData = res.data
                    if(this.applicationData) {
                        this.availableStepDefinitions = (this.applicationData.stepDefinition) ? this.applicationData.stepDefinition : []
                    } else {
                        this.noBackendAvailable = true
                    }
                }.bind(this))
                .catch(function (error) {
                    this.showLoadingScreen = false
                    if (error.response) {
                        //const data = JSON.parse(error.response.data)
                        //console.log(data.errorMsg)
                        console.log(error.response.data);

                    }
                    else if (error.request) {
                        console.log(error.request);
                    }
                    else {
                        console.log(error.message);
                    }
                }.bind(this))
            }
        },
        created() {
            this.initializeAppData()
        }


    }



</script>

