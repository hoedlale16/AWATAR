<template>
  <div id="app">
    <AwatarRunner></AwatarRunner>
  </div>
</template>

<script>

import AwatarRunner from "./components/AwatarRunner";
export default {
  name: 'App',
  components: {AwatarRunner }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
  margin-left: 60px;
  margin-right: 60px;

}

@import'~bootstrap/dist/css/bootstrap.css'
</style>
