import os
import re
import pandas as pd

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

print(final_df)











































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
