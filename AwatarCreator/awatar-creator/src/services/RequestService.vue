<script>
    import axios from 'axios'
    import { cacheAdapterEnhancer } from 'axios-extensions';
    const qs = require('querystring')

    axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

    export default {
        //Enter locale Vue-App IP
        contextPath: 'http://localhost:4001/awatar',
        webRequest: axios.create({
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Cache-Control': 'no-cache'
            },
            // cache will be enabled by default
            adapter: cacheAdapterEnhancer(axios.defaults.adapter, { enabledByDefault: false}),
            timeout: 2000,
        }),
        postRequest: axios.create({
            headers: {},
            timeout: 2000,
        }),
        postRequestFormEncode: axios.create({
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function (data) {
                return qs.stringify(data);
            },
            timeout: 2000,

        }),
        postRequestPDF: axios.create({
            headers: {'Access-Control-Allow-Origin': '*'},
            responseType: 'blob',
        }),



    }
</script>