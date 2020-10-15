<template>
  <div>
    <!-- Add new Step Definition Modal -->
    <div class="modal" id="modalDialog" v-if="addNewStepDefinitionModalShow">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">{{addNewStepDefinitionModalTitle}}</h4>
                    <button type="button" class="close" data-dismiss="modal" @click="cancelStepDefinitionModal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-group">
                        <select v-model="addNewStepDefinitionModalSelectedObject" class="form-control" style="margin-bottom: 10px ">
                            <option disabled="disabled" :value="null">Verfügbaren Schritt wählen</option>
                            <option v-for="(sd) in addNewStepDefinitionModalAvailableStepDefinitions" :key="sd.stepDefinition" :value="sd">
                                {{sd.stepDefinition}}
                            </option>
                        </select>


                        <!-- Dynamic parameter fields -->
                        <div v-if="addNewStepDefinitionModalSelectedObject">
                            <div class="input-group mb-3" v-for="(param, index) in addNewStepDefinitionModalSelectedObject.parameters" :key="index">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"> <span>Parameter {{index +1 }}</span></div>
                                </div>

                                <!--TODO: Bind parameters https://vuejs.org/v2/guide/list.html -->
                                <input type="text" class="form-control"  :placeholder="param" autocomplete="off">
                            </div>
                        </div>
                    </div>

                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-danger" data-dismiss="modal" @click="cancelStepDefinitionModal">Abort</button>
                    <button type="button" class="btn btn-outline-secondary" data-dismiss="modal"
                            @click="addNewStepDefinitionModal" :disabled="! addNewStepDefinitionModalSelectedObject">Confirm</button>
                </div>
            </div>
        </div>
    </div>
  <!-- END Step Definition Modal -->


    <div class="form-group">
        <div class="row">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <div class="input-group-text">
                        <span>Funktion</span>
                        <div style="margin-left: 5px" data-toggle="tooltip" data-placement="bottom" title="Bezeichnung des Testfallgruppierung">
                            <svg xmlns="http://www.w3.org/2000/svg" enable-background="new 0 0 24 24" viewBox="0 0 24 24" fill="black" width="18px" height="18px">
                                <g>
                                    <rect fill="none" height="24" width="24"/>
                                    <path d="M19,3H5C3.9,3,3,3.9,3,5v14c0,1.1,0.9,2,2,2h14c1.1,0,2-0.9,2-2V5C21,3.9,20.1,3,19,3z M12.01,18 c-0.7,0-1.26-0.56-1.26-1.26c0-0.71,0.56-1.25,1.26-1.25c0.71,0,1.25,0.54,1.25,1.25C13.25,17.43,12.72,18,12.01,18z M15.02,10.6 c-0.76,1.11-1.48,1.46-1.87,2.17c-0.16,0.29-0.22,0.48-0.22,1.41h-1.82c0-0.49-0.08-1.29,0.31-1.98c0.49-0.87,1.42-1.39,1.96-2.16 c0.57-0.81,0.25-2.33-1.37-2.33c-1.06,0-1.58,0.8-1.8,1.48L8.56,8.49C9.01,7.15,10.22,6,11.99,6c1.48,0,2.49,0.67,3.01,1.52 C15.44,8.24,15.7,9.59,15.02,10.6z"/>
                                </g>
                            </svg>
                        </div>
                    </div>
                </div>
                <input type="text" style="margin-left: 1em;" class="form-control"  placeholder="Test-Feature" autocomplete="off" v-model="testFeature.feature">
            </div>
        </div>


        <!-- Scenario-->
        <div class="row">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <div class="input-group-text">
                        <span>Szenario</span>
                        <div style="margin-left: 5px" data-toggle="tooltip" data-placement="bottom" title="Bezeichnung des zugehörigen Testfallszenarios">
                            <svg xmlns="http://www.w3.org/2000/svg" enable-background="new 0 0 24 24" viewBox="0 0 24 24" fill="black" width="18px" height="18px">
                                <g>
                                    <rect fill="none" height="24" width="24"/>
                                    <path d="M19,3H5C3.9,3,3,3.9,3,5v14c0,1.1,0.9,2,2,2h14c1.1,0,2-0.9,2-2V5C21,3.9,20.1,3,19,3z M12.01,18 c-0.7,0-1.26-0.56-1.26-1.26c0-0.71,0.56-1.25,1.26-1.25c0.71,0,1.25,0.54,1.25,1.25C13.25,17.43,12.72,18,12.01,18z M15.02,10.6 c-0.76,1.11-1.48,1.46-1.87,2.17c-0.16,0.29-0.22,0.48-0.22,1.41h-1.82c0-0.49-0.08-1.29,0.31-1.98c0.49-0.87,1.42-1.39,1.96-2.16 c0.57-0.81,0.25-2.33-1.37-2.33c-1.06,0-1.58,0.8-1.8,1.48L8.56,8.49C9.01,7.15,10.22,6,11.99,6c1.48,0,2.49,0.67,3.01,1.52 C15.44,8.24,15.7,9.59,15.02,10.6z"/>
                                </g>
                            </svg>
                        </div>
                    </div>
                </div>

                <input type="text" style="margin-left: 1em;" class="form-control"  placeholder="Testfallszenario" autocomplete="off" v-model="testFeature.scenario">
            </div>
        </div>

        <!-- Given-->
        <div class="row">
            <div class="input-group mb-3">
                <!-- Headline -->
                <div class="input-group-prepend">
                    <div class="input-group-text" style="height: fit-content;">
                        <span>Vorbedingungen</span>
                        <div style="margin-left: 5px" data-toggle="tooltip" data-placement="bottom" title="Liste der Vorbedingungen (GIVEN)">
                            <svg xmlns="http://www.w3.org/2000/svg" enable-background="new 0 0 24 24" viewBox="0 0 24 24" fill="black" width="18px" height="18px">
                                <g>
                                    <rect fill="none" height="24" width="24"/>
                                    <path d="M19,3H5C3.9,3,3,3.9,3,5v14c0,1.1,0.9,2,2,2h14c1.1,0,2-0.9,2-2V5C21,3.9,20.1,3,19,3z M12.01,18 c-0.7,0-1.26-0.56-1.26-1.26c0-0.71,0.56-1.25,1.26-1.25c0.71,0,1.25,0.54,1.25,1.25C13.25,17.43,12.72,18,12.01,18z M15.02,10.6 c-0.76,1.11-1.48,1.46-1.87,2.17c-0.16,0.29-0.22,0.48-0.22,1.41h-1.82c0-0.49-0.08-1.29,0.31-1.98c0.49-0.87,1.42-1.39,1.96-2.16 c0.57-0.81,0.25-2.33-1.37-2.33c-1.06,0-1.58,0.8-1.8,1.48L8.56,8.49C9.01,7.15,10.22,6,11.99,6c1.48,0,2.49,0.67,3.01,1.52 C15.44,8.24,15.7,9.59,15.02,10.6z"/>
                                </g>
                            </svg>
                        </div>
                    </div>
                </div>

                <!--List of Step definitions -->
                <ul class="list-group" :class="{'ul-testFeature-actions' : testFeature.given && testFeature.given.length > 0} ">
                    <li style="margin-left: 1em;" class="list-group-item d-flex justify-content-between align-items-center" v-for="(given, index) in testFeature.given" :key="given.stepDefinition" :value="given">
                        {{given.filledStepDefinition}}
                        <a href="#"  class="badge badge-secondary badge-pill" @click="removeStepDefinition(testFeature.given, index)">&times;</a>
                    </li>
                </ul>



                <!--Add new step defintion button -->
                <ul class="list-group">
                    <li style="margin-left: 1em; list-style-type:none;">
                        <button type="button" class="btn btn-outline-secondary"
                                data-toggle="modal" data-target="#modalDialog" data-backdrop="static" data-keyboard="false"
                                @click="showAddNewStepDefinitionModal('Neue Vorbedingung hinzufügen:', 'GIVEN')">
                            <span>
                                <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
                                    <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/><path d="M0 0h24v24H0z" fill="none"/>
                                </svg>
                            </span>
                        </button>
                    </li>
                </ul>
            </div>
        </div>

        <!-- WHEN-->
        <div class="row">
            <div class="input-group mb-3">
                    <!-- Headline -->
                <div class="input-group-prepend">
                    <div class="input-group-text" style="height: fit-content;">
                        <span>Aktionen</span>
                        <div style="margin-left: 5px" data-toggle="tooltip" data-placement="bottom" title="Liste der auszuführenden Aktionen (WHEN)">
                            <svg xmlns="http://www.w3.org/2000/svg" enable-background="new 0 0 24 24" viewBox="0 0 24 24" fill="black" width="18px" height="18px">
                                <g>
                                    <rect fill="none" height="24" width="24"/>
                                    <path d="M19,3H5C3.9,3,3,3.9,3,5v14c0,1.1,0.9,2,2,2h14c1.1,0,2-0.9,2-2V5C21,3.9,20.1,3,19,3z M12.01,18 c-0.7,0-1.26-0.56-1.26-1.26c0-0.71,0.56-1.25,1.26-1.25c0.71,0,1.25,0.54,1.25,1.25C13.25,17.43,12.72,18,12.01,18z M15.02,10.6 c-0.76,1.11-1.48,1.46-1.87,2.17c-0.16,0.29-0.22,0.48-0.22,1.41h-1.82c0-0.49-0.08-1.29,0.31-1.98c0.49-0.87,1.42-1.39,1.96-2.16 c0.57-0.81,0.25-2.33-1.37-2.33c-1.06,0-1.58,0.8-1.8,1.48L8.56,8.49C9.01,7.15,10.22,6,11.99,6c1.48,0,2.49,0.67,3.01,1.52 C15.44,8.24,15.7,9.59,15.02,10.6z"/>
                                </g>
                            </svg>
                        </div>
                    </div>
                </div>

                <!--List of Step definitions -->
                <ul class="list-group" :class="{'ul-testFeature-actions' : testFeature.when && testFeature.when.length > 0} ">
                    <li style="margin-left: 1em;" class="list-group-item d-flex justify-content-between align-items-center" v-for="(when, index) in testFeature.when" :key="when.stepDefinition" :value="when">
                        {{when.filledStepDefinition}}
                        <a href="#"  class="badge badge-secondary badge-pill" @click="removeStepDefinition(testFeature.given, index)">&times;</a>
                    </li>
                </ul>

                <!--Add new step defintion button -->
                <ul class="list-group">
                    <li style="margin-left: 1em; list-style-type:none;">
                        <button type="button" class="btn btn-outline-secondary"
                                data-toggle="modal" data-target="#modalDialog" data-backdrop="static" data-keyboard="false"
                                @click="showAddNewStepDefinitionModal('Neue Aktion hinzufügen:', 'WHEN')">
                            <span>
                                <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
                                    <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/><path d="M0 0h24v24H0z" fill="none"/>
                                </svg>
                            </span>
                        </button>
                    </li>
                </ul>
            </div>
        </div>

        <!-- THEN-->
        <div class="row">
            <div class="input-group mb-3">

                <!-- Headline -->
                <div class="input-group-prepend">
                    <div class="input-group-text" style="height: fit-content;">
                        <span>Erw. Ergebnis</span>
                        <div style="margin-left: 5px" data-toggle="tooltip" data-placement="bottom" title="Liste der erwarteten Ergebnisse (THEN)">
                            <svg xmlns="http://www.w3.org/2000/svg" enable-background="new 0 0 24 24" viewBox="0 0 24 24" fill="black" width="18px" height="18px">
                                <g>
                                    <rect fill="none" height="24" width="24"/>
                                    <path d="M19,3H5C3.9,3,3,3.9,3,5v14c0,1.1,0.9,2,2,2h14c1.1,0,2-0.9,2-2V5C21,3.9,20.1,3,19,3z M12.01,18 c-0.7,0-1.26-0.56-1.26-1.26c0-0.71,0.56-1.25,1.26-1.25c0.71,0,1.25,0.54,1.25,1.25C13.25,17.43,12.72,18,12.01,18z M15.02,10.6 c-0.76,1.11-1.48,1.46-1.87,2.17c-0.16,0.29-0.22,0.48-0.22,1.41h-1.82c0-0.49-0.08-1.29,0.31-1.98c0.49-0.87,1.42-1.39,1.96-2.16 c0.57-0.81,0.25-2.33-1.37-2.33c-1.06,0-1.58,0.8-1.8,1.48L8.56,8.49C9.01,7.15,10.22,6,11.99,6c1.48,0,2.49,0.67,3.01,1.52 C15.44,8.24,15.7,9.59,15.02,10.6z"/>
                                </g>
                            </svg>
                        </div>
                    </div>
                </div>

                <!--List of Step definitions -->
                <ul class="list-group" :class="{'ul-testFeature-actions' : testFeature.then && testFeature.then.length > 0} ">
                    <li style="margin-left: 1em;" class="list-group-item d-flex justify-content-between align-items-center" v-for="(then, index) in testFeature.then" :key="then.stepDefinition" :value="then">
                        {{then.filledStepDefinition}}
                        <a href="#"  class="badge badge-secondary badge-pill" @click="removeStepDefinition(testFeature.given, index)">&times;</a>
                    </li>
                </ul>

                <!--Add new step defintion button -->
                <ul class="list-group">
                    <li style="margin-left: 1em; list-style-type:none;">
                        <button type="button" class="btn btn-outline-secondary"
                                data-toggle="modal" data-target="#modalDialog" data-backdrop="static" data-keyboard="false"
                                @click="showAddNewStepDefinitionModal('Neues erw. Ergebnis hinzufügen:', 'THEN')">
                            <span>
                                <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
                                    <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/><path d="M0 0h24v24H0z" fill="none"/>
                                </svg>
                            </span>
                        </button>
                    </li>
                </ul>
            </div>
        </div>

    </div>

    <div class="form-group">
        <a class="btn btn-primary float-right" :class="{'disabled' : ! validTestFeature}" @click="executeTest()">Test ausführen</a>
    </div>


  </div>
</template>

<style scoped>
    .input-group>.input-group-prepend {
        flex: 0 0 20%;
    }
    .input-group .input-group-text {
        width: 100%;
    }

    .ul-testFeature-actions {
        width: 70%;
        flex: 1 1 auto;
    }

    .btn-add-stepDefinitions {
        flex: 1 1 auto;
        height: max-content;
    }
</style>

<script>
    export default {
        name: "CreateTestFeature",
        props: {
            value: Object,
            stepDefinitions: Object
        },
        data() {
            return {
                testFeature: {},

                addNewStepDefinitionModalShow: false,
                addNewStepDefinitionModalTitle: '',
                addNewStepDefinitionModalAvailableStepDefinitions: [],
                addNewStepDefinitionModalAction: '',
                addNewStepDefinitionModalSelectedObject: null,
            }
        },
        computed: {
            validTestFeature() {
                return (this.testFeature &&
                        this.testFeature.feature &&
                        this.testFeature.scenario &&
                        (this.testFeature.given && this.testFeature.given.length >0) &&
                        (this.testFeature.when && this.testFeature.when.length >0) &&
                        (this.testFeature.then && this.testFeature.then.length >0)) ? true : false
            }
        },
        methods: {
            initialize() {
                this.testFeature = this.value
            },


            removeStepDefinition: function(stepDefintions, index) {
                this.$delete(stepDefintions, index);
            },

            showAddNewStepDefinitionModal(title, action) {
                this.addNewStepDefinitionModalTitle = title
                this.addNewStepDefinitionModalAction = action
                this.addNewStepDefinitionModalSelectedObject = null
                this.addNewStepDefinitionModalAvailableStepDefinitions =
                    this.stepDefinitions[this.addNewStepDefinitionModalAction]


                console.log(this.addNewStepDefinitionModalAvailableStepDefinitions)
                //Show dialog
                this.addNewStepDefinitionModalShow = true;
            },

            cancelStepDefinitionModal() {
                this.addNewStepDefinitionModalShow = false;
                this.addNewStepDefinitionModalSelectedObject = null
                this.addNewStepDefinitionModalAction = null
                this.addNewStepDefinitionModalAvailableStepDefinitions= []

            },

            addNewStepDefinitionModal: function()  {
                if(this.addNewStepDefinitionModalSelectedObject) {
                    console.log("addNewStepDefinitionModal")

                    this.addNewStepDefinitionModalSelectedObject.filledStepDefinition = this.addNewStepDefinitionModalSelectedObject.stepDefinition

                    switch (this.addNewStepDefinitionModalAction)
                    {
                        case "GIVEN": {
                            this.testFeature.given.push(this.addNewStepDefinitionModalSelectedObject)
                            break;
                        }
                        case "WHEN": {
                            this.testFeature.when.push(this.addNewStepDefinitionModalSelectedObject)
                            break;
                        }
                        case "THEN": {
                            this.testFeature.then.push(this.addNewStepDefinitionModalSelectedObject)
                            break;
                        }
                        default: { console.log("STEP DEFINITON ACTION NOT SUPPORTED!"); }
                    }



                    //Reset Modal
                    this.cancelStepDefinitionModal();

                }
            },

            executeTest() {

            }
        },
        created() {
            this.initialize()
        }
    }
</script>

