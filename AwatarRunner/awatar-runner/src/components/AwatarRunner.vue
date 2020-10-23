<template>
    <div>

        <!-- ========= SPIELWIESE  =========== -->
        <!-- <a class="btn btn-primary pull-left" @click="showDevSpielwiese = !showDevSpielwiese">Spielwiese anzeigen</a> -->
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
        <!-- ENDE SPIELWIESE -->

        <!-- Loading Screen -->
        <template v-if="showLoadingScreen">
            <LoadingScreen msg="Please wait..."></LoadingScreen>
        </template>

        <!--Info Box-->
        <template v-if="showInfoBox">
            <InfoBox :msg="infoBox.msg" v-bind:closeable="infoBox.closable" :type="infoBox.type"></InfoBox>
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

        <!-- Load test case -->
        <template v-if="createLoadTestFeatureModal">
            <load-test-feature v-model="loadedTestFeature"></load-test-feature>
        </template>


        <div style="width: 50%; margin: 0 auto;">

        <div style="text-align: center">
            <h1>AWATAR Runner</h1>
            <p>Ein Tool zur Ausführung von automatisierten Tests nach Behavior Test Driven Approach mit AWATAR. Die Tests werden via REST-Schnittstelle fur Ausführung übertragen und können daher von jedweger externen Software angebunden werden. </p>
        </div>

        <div class="row">
            <span @click="showLoadTestFeature()" class="btn btn-light btn-block" data-toggle="modal" data-target="#loadTestFeatureDialog" data-backdrop="static" data-keyboard="false">Testfall laden</span>
        </div>

        <div class="row" style="margin-top:10px">
            <div class="form-group">
                <div class="input-group" style="margin-bottom: 10px">
                    <div class="input-group-prepend ">
                        <div class="input-group-text" style="height: fit-content; width: 120px">
                            <span>HTTP-URL: </span>
                        </div>
                    </div>
                    <div class="input-group-append">
                        <input class="form-control executeUrl" v-model="executeUrl"/>
                    </div>
                </div>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <div class="input-group-text" style="height: fit-content; width: 120px">
                            <span>HTTP-Body: </span>
                        </div>
                    </div>
                    <div class="input-group-append">
                        <p class="form-control testFeatureJson"> {{testFeatureJson}}</p>
                    </div>
                </div>
            </div>


        </div>


        <!-- Execute test -->
        <div class="row">
            <span @click="executeTest()" class="btn btn-secondary btn-block"  :class="{'disabled' : ! validTestFeature}"
                  data-toggle="modal" data-target="#loadTestFeatureDialog" data-backdrop="static" data-keyboard="false">Test ausführen</span>
        </div>


        <!--Test execution log -->
        <template v-if="showTestExecutionInfoBox">
                <test-execution-result :type="infoBox.type" :result-log="infoBox.msg"></test-execution-result>
        </template>

        </div>

    </div>
</template>

<style scoped>
 .devSpielwiese {
     background-color: grey;
 }

 .executeUrl {
     width: 795px;
     max-height: 500px;
     overflow: auto;
 }

 .testFeatureJson {
     width: 795px;
     height: fit-content;
     min-height: calc(1.5em + 0.75rem + 2px);
     max-height: 500px;
     overflow: auto;
 }
</style>

<script>
    import RequestService from "../services/RequestService";
    import LoadingScreen from "./LoadingScreen";
    import InfoBox from "./InfoBox";
    import Modal from "./Modal";
    import LoadTestFeature from "./LoadTestFeature";
    import TestExecutionResult from "./TestExecutionResult";


    export default {
        name: "AwatarRunner",
        components: {TestExecutionResult, LoadTestFeature, Modal, LoadingScreen, InfoBox},
        data() {
            return {
                applicationData: null,
                noBackendAvailable: false,
                availableStepDefinitions: null,
                showLoadingScreen: false,

                showTestExecutionInfoBox: false,
                showInfoBox:false,
                infoBox: {
                    msg: '???',
                    closable: false,
                    type:'error',
                },

                createModal: false, //Flag to create new modal dialog
                modalTitle: '',
                modalText: '',
                showModalFooter: false,
                modalConfirmed: null, //Returning Result of modal


                showDevSpielwiese: false,
                testFeature: null,


                createLoadTestFeatureModal: false,
                loadedTestFeature: null,
                executeUrl: RequestService.contextPath + '/runner/execute',
            }
        },
        computed: {
            validTestFeature() {
                return (this.testFeature &&
                    this.testFeature.feature &&
                    this.testFeature.scenario &&
                    (this.testFeature.given && this.testFeature.given.length > 0) &&
                    (this.testFeature.when && this.testFeature.when.length > 0) &&
                    (this.testFeature.then && this.testFeature.then.length > 0)) ? true : false
            },

            testFeatureJson() {
                return (this.testFeature) ? JSON.stringify(this.testFeature) : ''
            }
        },
        watch: {
            modalConfirmed(newVal) {
                if(newVal) {
                    this.createModal = false //Destroy currently modal
                }
            },
            loadedTestFeature(newVal) {
                if(newVal) {
                    this.testFeature = this.loadedTestFeature
                    this.createLoadTestFeatureModal = false
                }
            }
        },
        methods: {
             openModalDialog(title, text, closable,showModalFooter) {
                this.modalTitle=title
                this.modalText=text
                this.modalClosable=closable
                this.showModalFooter=showModalFooter

                 //Create new Modal and reset previous confirm result
                this.createModal = true
                this.modalConfirmed = null

            },

            showLoadTestFeature() {
              this.createLoadTestFeatureModal = true
            },

            buildExecutableTest() {
                let executeTestFeature = new Object({
                    feature: this.testFeature.feature,
                    scenario: this.testFeature.scenario,
                    given: [],
                    when: [],
                    then: []
                });

                this.testFeature.given.forEach(function (sd) {
                    executeTestFeature.given.push(sd.filledstepDefinition)
                })
                this.testFeature.when.forEach(function (sd) {
                    executeTestFeature.when.push(sd.filledstepDefinition)
                })
                this.testFeature.then.forEach(function (sd) {
                    executeTestFeature.then.push(sd.filledstepDefinition)
                })

                return executeTestFeature;
            },

            executeTest() {
                if (this.validTestFeature) {
                    this.showLoadingScreen = true

                    let executableTestFeature = this.buildExecutableTest()
                    RequestService.postRequest.post(this.executeUrl, executableTestFeature)
                        .then(function (res) {
                            var respone = res.data
                            if (respone) {
                                this.infoBox = {
                                    msg: respone.result,
                                    closable: true,
                                    type: (respone.exitState == 0) ? 'success' : 'error'
                                }
                                this.showTestExecutionInfoBox = true
                            }
                            this.showLoadingScreen = false
                        }.bind(this))
                        .catch(function (error) {
                            this.showLoadingScreen = false
                            //Read error message
                            let errMsg = error.message;
                            if (error.response) {
                                errMsg = error.response.data.result
                            }

                            //Show error
                            this.infoBox = {
                                msg: errMsg,
                                closable: true,
                                type: 'error'
                            }
                            this.showTestExecutionInfoBox = true


                        }.bind(this))
                }
            }
        }
    }



</script>

