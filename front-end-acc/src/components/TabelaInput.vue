<template>
  <div class="container">
    <v-card class="mx-auto" max-width="800" style="width: 60%; height: 80%; text-align: center; margin-top: 70px;">
      <v-table width="800"  height="450" style="margin: 60 auto;  border-spacing: 10px; margin:30px;" >
        <tr style="background-color: #253381; color: white;">
          <th>Coluna 1</th>
          <th>Coluna 2</th>
          <th>Coluna 3</th>
        </tr>
        <tr>
          <td style="border-bottom: 1px solid black;">Dado 1</td>
          <td style="border-bottom: 1px solid black;">Dado 2</td>
          <td style="border-bottom: 1px solid black;">Dado 3</td>
        </tr>
        <tr>
          <td style="border-bottom: 1px solid black;">Dado 4</td>
          <td style="border-bottom: 1px solid black;">Dado 5</td>
          <td style="border-bottom: 1px solid black;">Dado 6</td>
        </tr>
      </v-table>
    </v-card>
  </div>
</template>


<script>
export default {
  data() {
    return {
      headers: [
        { text: 'Chassi', value: 'chassi' },
        { text: 'Item', value: 'item' },
        { text: 'Status', value: 'status' },
      ],
      items: [
        { chassi: 'CH-123', item: 'Item A', status: 'OK' },
        { chassi: 'CH-456', item: 'Item B', status: 'Pendente' },
        { chassi: 'CH-789', item: 'Item C', status: 'Problema identificado' },
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
