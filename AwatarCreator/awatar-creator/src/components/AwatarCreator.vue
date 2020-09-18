<template>
    <div class="button" @click="triggerRequest()">
        Click me
    </div>
</template>

<style scoped>

</style>

<script>
    import RequestService from "../services/RequestService";

    export default {
        name: "AwatarCreator",
        data() {
            return {
                applicationData: null,
            }
        },
        watch: {

        },
        methods: {
            triggerRequest () {
                RequestService.webRequest('https://www.thetopsites.net/article/52483232.shtml')
                //RequestService.webRequest(RequestService.contextPath + '/test')
                    .then(function (res) {
                        console.log(res)
                    }.bind(this))
                    .catch(function (reason) {
                        if (reason.response) {
                            const data = JSON.parse(reason.response.data)
                            console.log(data.errorMsg)
                            //UtilService.showError(data.errorMsg)
                        } else {
                            console.log(reason)
                        }
                    }.bind(this))

            }
        }

    }



</script>

