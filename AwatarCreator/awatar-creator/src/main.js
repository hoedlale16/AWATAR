import Vue from 'vue'
import App from './App.vue'
import 'jquery/src/jquery.js'
import 'popper.js/dist/umd/popper.min.js'
import 'bootstrap'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app')
