import colorama
from colorama import Fore, Style
import PyPDF2
import re
import pandas as pd
import hashlib
from hashlib import md5
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
def createHash(dataframe, listofColumns, colname="hashID"):
        """
        Função que cria um hash a partir de de determinadas colunas
        Este hash é utilizado para identificar registros duplicados na base
        :param dataframe: Dataframe onde a regra será aplicada
        :param listofColumns: Lista de colunas utilizadas para criar o hash
        :param colname: Nome da nova coluna onde o hash será inserido
        :return: dataframe com a coluna hashID
        """
        dataframe = dataframe.copy()
        hash = lambda strval: hashlib.md5(strval.encode('utf-8)')).hexdigest()
        try:
            dataframe[colname] = dataframe[listofColumns].apply(lambda row: '_'.join(row.values.astype(str)), axis=1)
            dataframe[colname] = dataframe[colname].apply(hash)
            return dataframe
        except KeyError as err:
            raise Exception(f'Coluna especificada não existe no dataframe atual para geração do hash! \nColumn: {err}')



## FUNÇÃO PARA EXTRAIR UM PDF PARA TEXTO ##
def extract_text_from_pdf(pdf_path):
    with open(pdf_path, 'rb') as f:
        pdf_reader = PyPDF2.PdfReader(f)
        num_pages = len(pdf_reader.pages)

        text = ''
        for i in range(num_pages):
            page = pdf_reader.pages[i]
            text += page.extract_text().replace("SERVICES & SUPPORT", '').replace("RELAÇÃO ENTRE ITENS E SB'S", '').replace('This information belongs to Embraer and cannot be used or reproduced without written permission from the Company. RESERVED INFORMATION', '') + '///////'

        # Remove extra spaces
        text = ' '.join(text.split())
        text = text.replace("///////", '\n')

        return text

## COMEÇO DO CODIGO ##
colorama.init()
Style.font = "Arial"

pdf_path = 'Item to BE logic - FATEC.pdf'
pdf_text = extract_text_from_pdf(pdf_path)

## COLOCA O TEXTO EM UMA LISTA ##
linhas = pdf_text.split('\n')  # separa o texto em linhas
lista_sem_espaco = [item.strip() for item in linhas]

## VARRE A LISTA EM UM FOR, CASO O ITEM BATA COM O REGEX INSERE NUM DF ##
# regex1 = r'^(AND)\s+(SB FAT\s+-\d+-\w+)\s+(.*?)(?=\s+SB FAT\s+-\d+-\w+|$)'
# regex2 = r'^(AND)\s+(Chassis\s*>\s*\d+)\s+(.*)$'
# regex3 = r'^(OR)\s+(.*)(SB FAT\s+-\d+-\w+)\s+(SB FAT\s+-\d+-\w+)'
# regex4 = r'^(AND)\s+(SB FAT\s+-\d+-\w+)\s+(.*)(SB FAT\s+-\d+-\w+)\s+(SB FAT\s+-\d+-\w+)\s+(OR)'
regex1 = r'^(AND)\s+(SB FAT\s+-\d+-\w+)\s+(.*?)(?=\s+SB FAT\s+-\d+-\w+|$)'
regex2 = r'^(AND)\s+(Chassis\s*>\s*\d+)\s+(.*)$'
regex3 = r'^(OR)\s+(.*)(SB FAT\s+-\d+-\w+)\s+(SB FAT\s+-\d+-\w+)'
regex4 = r'^(AND)\s+(SB FAT\s+-\d+-\w+)\s+(.*)(SB FAT\s+-\d+-\w+)\s+(SB FAT\s+-\d+-\w+)\s+(OR)'
regex5 = r'(AND)\s+(.*?)\s+(SB FAT\s+-\d+-\w+)\s+(SB FAT\s+-\d+-\w+)(?=\s+SB FAT\s+-\d+-\w+|$)'
regex6 = r'(AND)\s+(.*?)\s+(SB FAT\s+-\d+-\w+)$'
regex7 = r'(AND)\s+(.*?)\s+(Chassis\s*>\s*\d+)$'

df = pd.DataFrame(columns=["tipo", "boletim", "item"])
for item in lista_sem_espaco:
    if re.match(regex1, item):
        match = re.search(regex1, item)
        df = df.append({"tipo": match.group(1), "boletim": match.group(2), "item": match.group(3)}, ignore_index=True)
    if re.match(regex2, item):
        match = re.search(regex2, item)
        df = df.append({"tipo": match.group(1), "boletim": match.group(2), "item": match.group(3)}, ignore_index=True)

    if re.match(regex4, item):
        match = re.search(regex4, item)
        df = df.append({"tipo": match.group(6), "boletim": match.group(2), "item": match.group(3)}, ignore_index=True)
        df = df.append({"tipo": match.group(6), "boletim": match.group(5), "item": match.group(3)}, ignore_index=True)
        df = df.append({"tipo": match.group(1), "boletim": match.group(4), "item": match.group(3)}, ignore_index=True)

    if re.match(regex3, item):
        match = re.search(regex3, item)
        df = df.append({"tipo": match.group(1), "boletim": match.group(3), "item": match.group(2)}, ignore_index=True)
        df = df.append({"tipo": match.group(1), "boletim": match.group(4), "item": match.group(2)}, ignore_index=True)

    if re.match(regex5, item):
        match = re.search(regex5, item)
        df = df.append({"tipo": match.group(1), "boletim": match.group(3), "item": match.group(2)}, ignore_index=True)
        df = df.append({"tipo": match.group(1), "boletim": match.group(4), "item": match.group(2)}, ignore_index=True)

    if re.match(regex6, item):
        match = re.search(regex6, item)
        df = df.append({"tipo": match.group(1), "boletim": match.group(3), "item": match.group(2)}, ignore_index=True)

    if re.match(regex7, item):
        match = re.search(regex7, item)
        df = df.append({"tipo": match.group(1), "boletim": match.group(3), "item": match.group(2)}, ignore_index=True)

df
query_pdf = "INSERT INTO ADMIN.PDF (BOLETIM, ITEM, TIPO) VALUES (:1, :2, :3)"

insert(df, query_pdf)

#list = ['tipo', 'boletim', 'item']
#df = createHash(df, list, 'hashID')
#print(df)
#print(Fore.WHITE + text)













