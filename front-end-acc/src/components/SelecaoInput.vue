<template>
    <!-- #ajustar label -->
    <div class="select">
        <v-select
        label=""
        :items=itens
        density="compact"
        bg-color="white"
        hide-selected

        ></v-select>
    </div>
</template>

<script>
export default {
    data: () => ({
        itens: [
        'California', 'Colorado', 'Florida', 'Georgia', 'Texas', 'Wyoming'
        ],
    }),
    }
    
</script>

<style>
    .select{
        height: 30px;
        width: 200px;

    }

</style>