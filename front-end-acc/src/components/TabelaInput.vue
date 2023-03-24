<template>
  <div class="container">
    <v-card class="mx-auto" max-width="800" style=" height: 80%; text-align: center; margin-top: 70px; margin: 40px; width: 50">
      <v-table width="800"  height="450" style=" margin: 60 auto;  border-spacing: 10px; margin:30px;" >
        <tr style="background-color: #333333; color: white;">
          <th>Itens</th>
          <th>Status</th>
        </tr>
        <tr>
          <td style="border-bottom: 1px solid black;">Dado 1</td>
          <td style="border-bottom: 1px solid black;">Dado 2</td>
        </tr>
        <tr>
          <td style="border-bottom: 1px solid black;">Dado 4</td>
          <td style="border-bottom: 1px solid black;">Dado 5</td>
        </tr>
      </v-table>
      <v-pagination
      v-model="page"
      :length="4"
      prev-icon="mdi-menu-left"
      next-icon="mdi-menu-right"
      style="margin: 20px;"
    ></v-pagination>
    </v-card>
  </div>
</template>


<script>
export default {
  data() {
    return {
      // page pertence a paginacao
      page: 1,
      headers: [
        { text: 'Chassi', value: 'chassi' },
        { text: 'Item', value: 'item' },
        { text: 'Status', value: 'status' },
      ],
      items: [
        { item: 'Item A', status: 'OK' },
        { item: 'Item B', status: 'Pendente' },
        { item: 'Item C', status: 'Problema identificado' },
      ],
    };
  },
  methods: {
    getStatusColor(status) {
      if (status === 'OK') {
        return 'green';
      } else if (status === 'Pendente') {
        return 'yellow';
      } else if (status === 'Problema identificado') {
        return 'red';
      }
    },
  },
  
};
</script>


<style scoped>
@media only screen and (max-width: 600px) {
  .table {
    width: 100%;
  }
  .col-1,
  .col-2,
  .col-3 {
    display: block;
    width: 100%;
    text-align: center;
  }

  .v-card {
  width: 90%;
}
   
  .container {
    font-size: 14px;
  }
  .v-table td, .v-table th {
    padding: 5px;
  }
}
</style>