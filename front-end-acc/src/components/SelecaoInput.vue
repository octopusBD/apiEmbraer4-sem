    <template>
        <div class="select-container">
        <v-select
            label=""
            :items="itens"
            density="compact"
            bg-color="white"
            hide-selected
            class="select-small"
        ></v-select>
        <v-select
            label=""
            :items="itens"
            density="compact"
            bg-color="white"
            hide-selected
            class="select-small"
        ></v-select>
        </div>
    </template>
    
    <script>
    export default {
        data: () => ({
        itens: [
            '','Teste1', 'Teste2', 'Teste3', 'Teste4', 'Teste5', 'Teste6'
        ],
        })
    }
    </script>
    
    <style>
    .select-container {
        display: flex;
        justify-content: center;
        margin-top: 10px;
        color: #253381;
    }
    
    .select-small {
        width: 100px;
        height: 30px;
        margin-right: 10px;
        color:#253381;
    }
    </style>
