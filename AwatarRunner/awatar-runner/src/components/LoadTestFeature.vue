<template>
    <div class="modal" id="loadTestFeatureDialog">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Testfall laden</h4>
                    <button type="button" class="close" data-dismiss="modal" @click="cancel">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-group">
                        <!-- <v-file-input></v-file-input> -->

                           <file-reader @load="loadFile($event)"></file-reader>
                           <div class="input-group-text">Vorschau: </div>
                           <div class="row" style="margin: 0 auto;">
                               <textarea rows="10" style="width: 100%;" class="form-control"
                                         v-model="testFeatureAsText"></textarea>
                           </div>
                    </div>

                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-danger" data-dismiss="modal" @click="cancel">Abort</button>
                    <button type="button" class="btn btn-outline-secondary" data-dismiss="modal"
                            @click="selected" :disabled="! selectedTestFeature">Confirm</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import FileReader from "./FileReader";


    export default {
        name: "LoadTestFeature",
        components: {FileReader},

        props: {
            value: Object,
        },
        data() {
            return {
                selectedTestFeature: null,
                testFeatureAsText: null,
            }
        },
        computed: {
        },
        methods: {
            cancel() {
                this.selectedTestFeature = null;
                this.$emit('input', null)
            },

            selected() {
                this.$emit('input', this.selectedTestFeature)
            },
            loadFile(content) {
                this.testFeatureAsText = content
                if(this.testFeatureAsText) {
                    console.log(this.testFeatureAsText)
                    let parsedObject = {
                        feature: '',
                        scenario: '',
                        given: [],
                        when: [],
                        then: []
                    }

                    //Parse content
                    let lines = this.testFeatureAsText.split("\n");
                    if(lines) {
                        let stepDefinitionType = ''
                        for (var i = 0; i < lines.length; i++) {
                            let line = lines[i]
                            switch(line) {
                                case line.startsWith("Feature: ") ? line : '' : { parsedObject.feature = line.replaceAll("Feature: ", ""); break; }
                                case line.startsWith("Scenario: ")? line : '' : { parsedObject.scenario = line.replaceAll("Scenario: ", ""); break; }
                                case line.startsWith("Given") ? line : ''     : {
                                    parsedObject.given.push(this.buildStepDefinition(line.replaceAll("Given ", "")));
                                    stepDefinitionType = 'GIVEN';
                                    break; }
                                case line.startsWith("When") ? line : ''      : {
                                    parsedObject.when.push(this.buildStepDefinition(line.replaceAll("When ", "")));
                                    stepDefinitionType = 'WHEN';
                                    break; }
                                case line.startsWith("Then") ? line : ''      : {
                                    parsedObject.then.push(this.buildStepDefinition(line.replaceAll("Then ", "")));
                                    stepDefinitionType = 'THEN';
                                    break; }
                                case line.startsWith("And") ? line : ''       : {
                                    switch (stepDefinitionType) {
                                        case "GIVEN": { parsedObject.given.push(this.buildStepDefinition(line.substr(4))); break; }
                                        case "WHEN":  { parsedObject.when.push(this.buildStepDefinition(line.substr(4))); break; }
                                        case "THEN:": { parsedObject.then.push(this.buildStepDefinition(line.substr(4))); break; }
                                    }
                                    break;
                                }
                                default: {
                                    console.log("Ignore Line <" + line + ">")
                                }
                            }
                        }
                    }
                    this.selectedTestFeature = parsedObject
                }
            },

            buildStepDefinition(stepDefinition) {
                return {
                    filledstepDefinition: stepDefinition,
                    formattedFilledStepDefinition: stepDefinition
                }
            }



        },
        created() {
            this.selectedTestFeature = this.value
        },
        destroyed() {
        }
    }
</script>

<style scoped>

</style>