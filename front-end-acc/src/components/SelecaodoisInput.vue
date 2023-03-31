    <template>
        <v-toolbar class="card-select" prominent>
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
        
        
        <v-spacer></v-spacer>
        </v-toolbar>
    </template>
    <script>
    import axios from "axios";
    
    export default {
        data: () => ({
        itens: [],
        }),
        created() {
        this.fetchItens();
        },
        methods: {
        fetchItens() {
            axios
            .get("sua_url_api")
            .then((response) => {
                this.itens = response.data;
            })
            .catch((error) => {
                console.log(error);
            });
        },
        },
    };
    </script>
<style>
    .card-select {
        display: 600px;
        justify-content: center;
        align-items: center;
        
        }
        
        .filtro1 {
        width: 400px;
        display: flex;
        justify-content: center;
        margin-top: 20px;
        margin-right: 20px;
        margin-left: 20px;/* decrease the margin to add space between the select components */
        
        }
        
        .filtro2 {
        width: 100px;
        display: 600px;
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
            background-color: #e2e2ff;
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
        
        .filtro4{
            margin-left: 20px;
            width: 120px;
        
        }
        
        .filtro5{
            margin-left: 20px;
            width: 120px;
        
        }
        
        </style>
    