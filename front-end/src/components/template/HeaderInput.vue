<template>
    <header class="header">
    </header>
  
</template>

<script>
export default {
    name: "HeaderInput",
    props:{
        title: String,
        hideToggle: Boolean

    }

}
</script>

<style>
    .header {
        grid-area: header;
        background-color:  #333333;
        color: white;
        display: flex;
        justify-content: center;
        align-items:center ;

    }

</style>