<template>
  <div id="app">
    <AwatarCreator></AwatarCreator>
  </div>
</template>

<script>

import AwatarCreator from "./components/AwatarCreator";
export default {
  name: 'App',
  components: {AwatarCreator }
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
