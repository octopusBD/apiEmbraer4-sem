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

    # converter o dataframe em uma lista de tuplas
    values = [tuple(x) for x in final_df.values]

    # Crie uma conexão com o banco de dados
    connection = oracledb.connect(
         user="ADMIN",
         password="4k24Vy9pd7A66mG",
         dsn="lzll6xuk2zk7xkbj_low",
         config_dir="/Users/Wallet_LZLL6XUK2ZK7XKBJ",
         wallet_location="/Users/Wallet_LZLL6XUK2ZK7XKBJ",
         wallet_password="4k24Vy9pd7A66mG")

    # Crie um cursor para executar comandos SQL
    cursor = connection.cursor()

    # Crie uma instrução SQL INSERT para inserir os dados do array na tabela
    sql = "INSERT INTO ADMIN.TESTE_PYTHON (BOLETIM, STATUS, CHASSI) VALUES (:1, :2, :3)"

    # Execute a instrução SQL utilizando o método executemany() do cursor e passe o array como parâmetro
    cursor.executemany(sql, values)

    # Confirme a transação
    connection.commit()

    # fechar a conexão
    connection.close()

    return 'Arquivos processados com sucesso!'

if __name__ == '__main__':
    app.run(debug=True)