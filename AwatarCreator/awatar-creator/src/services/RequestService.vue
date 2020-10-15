<script>
    import axios from 'axios'
    import { cacheAdapterEnhancer } from 'axios-extensions';
    const qs = require('querystring')

    axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

    export default {
        contextPath: 'http://localhost:8081/awatar',
        webRequest: axios.create({
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Cache-Control': 'no-cache'
            },
            // cache will be enabled by default
            adapter: cacheAdapterEnhancer(axios.defaults.adapter, { enabledByDefault: false})
        }),
        postRequest: axios.create({
            headers: {}
        }),
        postRequestFormEncode: axios.create({
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function (data) {
                return qs.stringify(data);
            },
        }),
        postRequestPDF: axios.create({
            headers: {'Access-Control-Allow-Origin': '*'},
            responseType: 'blob',
        }),



    }
</script>