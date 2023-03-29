from flask import Flask, request, render_template
import os
import re
import pandas as pd
import oracledb as oracledb

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/upload', methods=['POST'])
def upload():

    def insert(dataframe, query, table):
        #list_processado = dataframe['Boletim de serviço'].unique().tolist()
        # Crie uma conexão com o banco de dados
        connection = oracledb.connect(
            user="ADMIN",
            password="4k24Vy9pd7A66mG",
            dsn="lzll6xuk2zk7xkbj_low",
            config_dir="/Users/Wallet_LZLL6XUK2ZK7XKBJ",
            wallet_location="/Users/Wallet_LZLL6XUK2ZK7XKBJ",
            wallet_password="4k24Vy9pd7A66mG")

        if table == 'boletim':
            cursor1 = connection.cursor()
            cursor1.execute("SELECT * FROM ADMIN.BOLETIM")
            df_boletim = pd.DataFrame(list(cursor1))
            list_processado = df_boletim[1].unique().tolist()
            dataframe = dataframe.drop(dataframe[dataframe['Boletim de serviço'].isin(list_processado)].index)

        if table == 'chassi':
            cursor2 = connection.cursor()
            cursor2.execute("SELECT * FROM ADMIN.CHASSI")
            df_chassi = pd.DataFrame(list(cursor2))
            list_processado = df_chassi[1].unique().tolist()
            dataframe = dataframe.drop(dataframe[dataframe['chassi'].isin(list_processado)].index)

        # converter o dataframe em uma lista de tuplas
        values = [tuple(x) for x in dataframe.values]

        # Crie um cursor para executar comandos SQL
        cursor3 = connection.cursor()

        # Crie uma instrução SQL INSERT para inserir os dados do array na tabela
        sql = query

        if not dataframe.empty:
            # Execute a instrução SQL utilizando o método executemany() do cursor e passe o array como parâmetro
            cursor3.executemany(sql, values)
            # Confirme a transação
            connection.commit()
        else:
            print("Dataframe vazio")

        # fechar a conexão
        connection.close()

    dadosxls = []
    for f in request.files.getlist('file'):
        filename = f.filename
        f.save(filename)
        dadosxls.append(filename)

    dfs = []
    regex = r'(\b\d+\b)'
    for file in dadosxls:
        df = pd.read_excel(file, header=0, usecols='A:B')
        match = re.search(regex, file)
        df["chassi"] = match.group(1)
        dfs.append(df)
    final_df = pd.concat(dfs)


    # Replace
    final_df['Status'] = final_df['Status'].replace({'INCOPORATED': 'INCORPORATED'})
    final_df['Status'] = final_df['Status'].replace({'INCORP': 'INCORPORATED'})

    df_boletim = final_df[['Boletim de serviço']]
    df_boletim = df_boletim.drop_duplicates(subset='Boletim de serviço', keep='first')
    df_boletim['item'] = 'N/A'
    query_boletim = "INSERT INTO ADMIN.BOLETIM (BOLETIM, ITEM) VALUES (:1, :2)"

    df_chassi = final_df[['chassi']]
    df_chassi = df_chassi.drop_duplicates(subset='chassi', keep='first')
    query_chassi = "INSERT INTO ADMIN.CHASSI (CHASSI) VALUES (:1)"

    insert(df_boletim, query_boletim, 'boletim')
    insert(df_chassi, query_chassi, 'chassi')
    return 'Arquivos processados com sucesso!'

if __name__ == '__main__':
    app.run(debug=True)