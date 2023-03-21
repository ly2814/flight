<template>
  <div id="app">
    <div class="divs"><h1>航班信息管理系统</h1></div>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  /* color: #2c3e50; */
}
.divs{
  width: 100%;
  background-color: papayawhip;
  color: midnightblue;
}
</style>
