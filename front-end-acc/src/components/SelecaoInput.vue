    <template>
        <v-card class="card-select"
        width="600"
        >
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
        </v-card>
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
    .card-select{
      margin-left: 430px;
      margin-top: 30px;
    }
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
