import os
import re
import pandas as pd
import oracledb as oracledb


def insert(dataframe, query):
    # converter o dataframe em uma lista de tuplas
    values = [tuple(x) for x in dataframe.values]

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
    sql = query

    # Execute a instrução SQL utilizando o método executemany() do cursor e passe o array como parâmetro
    cursor.executemany(sql, values)

    # Confirme a transação
    connection.commit()

    # fechar a conexão
    connection.close()

# obtendo o caminho do diretório atual
path = os.getcwd()
path = path + '\\dadosExcel'

# listando os arquivos e diretórios no caminho atual
files_and_dirs = os.listdir(path)

# lista vazia para armazenar os DataFrames lidos
dfs = []

# expressão regular para extrair o número de chassi do nome do arquivo
regex = r'(\b\d+\b)'

# para cada arquivo na lista 'dadosxls'
for file in files_and_dirs:
    file = 'dadosExcel/' + file

    df = pd.read_excel(file,  header=0, usecols='A:B')

    # busca o número de chassi do arquivo usando a expressão regular 'regex'
    match = re.search(regex, file)

    # adiciona uma nova coluna chamada 'chassi' com o número de chassi correspondente ao arquivo
    df["chassi"] = match.group(1)

    # adiciona o DataFrame 'df' à lista de DataFrames 'dfs'
    dfs.append(df)

# concatena os DataFrames da lista 'dfs' em um único DataFrame
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

insert(df_boletim, query_boletim)
insert(df_chassi, query_chassi)
































# import os
# import re
# import pandas as pd
#
# path = os.getcwd()
# files_and_dirs = os.listdir(path)
# dadosxls = ['Sample - Chassis 10000076.xlsx', 'Sample - Chassis 10000153.xlsx', 'Sample - Chassis 10000218.xlsx', 'Sample - Chassis 10000270.xlsx', 'Sample - Chassis 10000348.xlsx', 'Sample - Chassis 10000432.xlsx', 'Sample - Chassis 10000437.xlsx', 'Sample - Chassis 10000470.xlsx']
# dfs = []
# regex = r'(\b\d+\b)'
# for file in dadosxls:
#     df = pd.read_excel(file,  header=0, usecols='A:B')
#     match = re.search(regex, file)
#     df["chassi"] = match.group(1)
#     dfs.append(df)
# final_df = pd.concat(dfs)
#
# #final_df.to_csv("base.csv")
# # listBoletim  = final_df['Boletim de serviço'].unique().tolist()
# # listStatus  = final_df['Status'].unique().tolist()
#
