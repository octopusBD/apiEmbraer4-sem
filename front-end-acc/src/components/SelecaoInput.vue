<template>
    <v-toolbar class="card-select" prominent >
        <v-spacer></v-spacer>
        <div class="filtro1">
                <v-select
                label=""
                :items="itens"
                density="compact"
                bg-color="white"
                hide-selected

                ></v-select>
            </div>
                <div class="filtro2">
                <v-select
                label=""
                :items="itens"
                density="compact"
                bg-color="white"
                hide-selected
                ></v-select>
            </div>
            <div class="filtro3">
                <v-select
                label=""
                :items="itens"
                density="compact"
                bg-color="white"
                hide-selected
                ></v-select>
            </div>
        <v-spacer></v-spacer>
    </v-toolbar>
</template>

<script>
export default {
    data: () => ({
    itens: ['', 'Teste1', 'Teste2', 'Teste3', 'Teste4', 'Teste5', 'Teste6']
    })
}
</script>

<style>

.card-select {
display: flex;
justify-content: center;
align-items: center;

}

.filtro1 {
width: 100px;
display: flex;
justify-content: center;
margin-top: 20px;
margin-right: 20px;
margin-left: 20px;/* decrease the margin to add space between the select components */


}

.filtro2 {
width: 100px;
display: flex;
justify-content: center;
margin-top: 20px;
margin-right: 20px;
margin-left: 20px;

}

.filtro3 {
width: 300px;
display: flex;
justify-content: center;
margin-top: 20px;
margin-right: 20px;
margin-left: 20px;

}
@media only screen and (max-width: 600px) { /* adjust as needed */
.select-container {
    flex-direction: column;
    align-items: center;
    margin-right: 0; /* remove the margin on smaller screens */
    padding: 10px;
    
}
.select-small {
    margin-top: 10px; /* add margin between the select components on smaller screens */
}
}
.v-toolbar {
    background-color: #F8F8FF;
}

.filtro1{
    margin-left: 20px;
    width: 120px;
}
.filtro2{
    margin-left: 20px;
    width: 120px;

}
.filtro3{
    margin-left: 20px;
    width: 120px;

}

</style>

