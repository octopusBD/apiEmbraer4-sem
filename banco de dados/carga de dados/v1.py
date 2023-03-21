import pandas as pd
import numpy as np
import oracledb as oracledb

# Crie um dataframe com os dados que deseja inserir na tabela
df = pd.DataFrame({'BOLETIM': ['Teste Python 1', 'Teste Python 2', 'Teste Python 3'], 'ITEM': ['ITEM 1', 'ITEM 2', 'ITEM 3']})

# Converta o dataframe em um array numpy para poder ser inserido na tabela
# data = np.array(df)

# converter o dataframe em uma lista de tuplas
values = [tuple(x) for x in df.values]

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
sql = "INSERT INTO ADMIN.BOLETIM (BOLETIM, ITEM) VALUES (:1, :2)"

# Execute a instrução SQL utilizando o método executemany() do cursor e passe o array como parâmetro
cursor.executemany(sql, values)

# Confirme a transação
connection.commit()

# fechar a conexão
connection.close()