<template>
<div>
    <header-input></header-input>
    <content-input> </content-input>
    <br>
    <footer-input> </footer-input> 
    
</div>
</template>

<script>
import ContentInput from '@/components/template/ContentInput.vue'
import HeaderInput from '@/components/template/HeaderInput.vue'
import FooterInput from './components/template/FooterInput.vue'


export default {
    name:"App",
    components:{HeaderInput, ContentInput ,FooterInput}
    
}
</script>

<style>
    *{
        font-family:Helvetica,Arial,sans-serif;
    }

    body {
        margin: 0;
    }

    #app{
        -webkit-front-smoothing:antialiased;
        -moz-osx-font-smoothing:grayscale;

        height: 100vh;
        display: grid;
        grid-template-rows: 60px 1fr 40px;
        grid-template-columns: 300px 1fr;
        grid-template-areas:
            "header header" 
            "content content"
             "footer footer"; 


    }

</style>