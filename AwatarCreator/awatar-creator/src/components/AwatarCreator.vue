<template>
    <div v-if="applicationData">
        <!-- Loading Screen -->
        <template v-if="showLoadingScreen">
            <LoadingScreen msg="Please wait..."></LoadingScreen>
        </template>

        <template v-if="showInfoBox">
            <InfoBox v-if="showInfoBox" :msg="infoBox.msg" v-bind:closeable="infoBox.closable" :type="infoBox.type"></InfoBox>
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
        {{testFeature}}

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

        <div style="text-align: center;">
            <h1>AWATAR Creator</h1>
            <p>Ein Tool zur Erstellung von automatisierten Tests nach Behavior Test Driven Approach mit AWATAR</p>
        </div>


        <div class="row">
            <div style="width: 50%; margin: 0 auto;">
                <create-test-feature v-model="testFeature" :step-definitions="availableStepDefinitions"></create-test-feature>
            </div>
        </div>

        <div class="row">
            <div class="form-group" style="width: 50%; margin: 0 auto;">
                <a class="btn btn-primary float-right mr-3" :class="{'disabled' : ! validTestFeature}" @click="executeTest()">Test ausführen</a>
                <a class="btn btn-primary float-right mr-3" :class="{'disabled' : ! validTestFeature}" @click="saveAsFile()">Testfall speichern</a>
                <v-fi
            </div>
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
    import CreateTestFeature from "./CreateTestFeature";

    export default {
        name: "AwatarCreator",
        components: {CreateTestFeature, Modal, LoadingScreen, InfoBox},
        data() {
            return {
                applicationData: null,
                noBackendAvailable: false,
                availableStepDefinitions: null,
                showLoadingScreen: false,

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
                testFeature: {
                    feature: 'feature',
                    scenario: 'scenario',
                    given: [],
                    when: [],
                    then: []
                }
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
             openModalDialog(title, text, closable,showModalFooter) {
                this.modalTitle=title
                this.modalText=text
                this.modalClosable=closable
                this.showModalFooter=showModalFooter

                 //Create new Modal and reset previous confirm result
                this.createModal = true
                this.modalConfirmed = null

            },

            saveAsFile() {
                if(this.testFeature) {
                    this.showLoadingScreen = true

                    var fileLink = document.createElement('a')

                    //Generate Gherkin file Format for cucumber
                    var data = [];
                    data.push("Feature: " + this.testFeature.feature)
                    data.push("Scenario: " + this.testFeature.scenario)
                    data.push(this.formatStepDefinitions(this.testFeature.given, 'Given'))
                    data.push(this.formatStepDefinitions(this.testFeature.when, 'When'))
                    data.push(this.formatStepDefinitions(this.testFeature.then, 'Then'))
                    data = data.join("\n")

                    //Trigger File-Download
                    let fileName = this.testFeature.feature + ".feature"
                    fileLink.href = window.URL.createObjectURL(new Blob([data], {type: 'text/plain'}))
                    fileLink.setAttribute('download', fileName)
                    document.body.appendChild(fileLink)
                    fileLink.click()
                    fileLink.parentNode.removeChild(fileLink)

                    this.showLoadingScreen = false
                    this.infoBox = {
                        msg: "Testfall unter Filename <" + fileName + "> erfolgreich gespeichert!",
                        closable: true,
                        type: 'success'
                    }
                    this.showInfoBox = true

                }
            },

            formatStepDefinitions(stepDefinitions, identifier) {
                let formattedStepDefinition = []
                if(stepDefinitions) {
                    stepDefinitions.forEach( function(sd, index) {
                        let lineStr = ((index >0 ) ? 'And' : identifier) + ' ' + sd.filledstepDefinition
                        formattedStepDefinition.push(lineStr);
                    })
                }
                return formattedStepDefinition.join("\n")
            },

            executeTest() {
                this.showLoadingScreen = true

                let executeTestFeature = new Object({
                    feature: this.testFeature.feature,
                    scenario: this.testFeature.scenario,
                    given: [],
                    when: [],
                    then: []
                });

                this.testFeature.given.forEach(function (sd) { executeTestFeature.given.push(sd.filledstepDefinition)})
                this.testFeature.when.forEach(function (sd) { executeTestFeature.when.push(sd.filledstepDefinition)})
                this.testFeature.then.forEach(function (sd) { executeTestFeature.then.push(sd.filledstepDefinition)})

                console.log(executeTestFeature)

                RequestService.postRequest.post(RequestService.contextPath + '/runner/execute',executeTestFeature)
                    .then(function (res) {
                        console.log(res)
                        this.showLoadingScreen = false
                    }.bind(this))
                    .catch(function (error) {
                        this.showLoadingScreen = false
                        if (error.response) {
                            console.log(error.response.data.result)
                            //const data = JSON.parse(error.response.data)
                        }
                        else if (error.request) {
                            console.log(error.request);
                        }
                        else {
                            console.log(error.message);
                        }
                    }.bind(this))
            },


            initializeAppData () {
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
                        const data = JSON.parse(error.response.data)
                        console.log(data.errorMsg)
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

