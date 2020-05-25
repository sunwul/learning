<template>
	<div id="app">
		<router-view></router-view>
	</div>
</template>

<script>
	export default {

	}
</script>

<style>
  /*全局滚动条*/
  @import url("assets/css/AllScrollbar.css");
	#app{
		padding-top: 0px;
		text-align: center;
		position: fixed;
		width: 100%;
		height: 100%;
		top: 0px;
		left: 0px;
		background: url(./assets/image/background/home_2.jpg) no-repeat;
		color: bisque;
	}
</style>
