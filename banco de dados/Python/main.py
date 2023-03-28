import pandas as pd
import openpyxl
import os
from pdfreader import PDFDocument, SimplePDFViewer
import colorama
from colorama import Fore, Style
import PyPDF2
import re
import pandas as pd

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
regex1 = r'^(AND)\s+(SB FAT\s+-\d+-\w+)\s+(.*?)(?=\s+SB FAT\s+-\d+-\w+|$)'
regex2 = r'^(AND)\s+(Chassis\s*>\s*\d+)\s+(.*)$'
regex3 = r'^(OR)\s+(.*)(SB FAT\s+-\d+-\w+)\s+(SB FAT\s+-\d+-\w+)'
regex4 = r'^(AND)\s+(SB FAT\s+-\d+-\w+)\s+(.*)(SB FAT\s+-\d+-\w+)\s+(SB FAT\s+-\d+-\w+)\s+(OR)'

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
print(df)
#print(Fore.WHITE + text)


path = "C:/Users/Aldrik/PycharmProjects/pythonProject3"
files_and_dirs = os.listdir(path)
print(files_and_dirs)

dadosxls = ['Sample - Chassis 10000076.xlsx', 'Sample - Chassis 10000153.xlsx', 'Sample - Chassis 10000218.xlsx', 'Sample - Chassis 10000270.xlsx', 'Sample - Chassis 10000348.xlsx', 'Sample - Chassis 10000432.xlsx', 'Sample - Chassis 10000437.xlsx', 'Sample - Chassis 10000470.xlsx']
dfs = []
regex = r'(\b\d+\b)'
for file in dadosxls:
    df = pd.read_excel(file,  header=0, usecols='A:B')
    match = re.search(regex, file)
    df["chassi"] = match.group(1)
    dfs.append(df)
final_df = pd.concat(dfs)
final_df.to_csv("base.csv")
listBoletim  = final_df['Boletim de serviço'].unique().tolist()
listStatus  = final_df['Status'].unique().tolist()
print(listBoletim)
print(listStatus)
print("Oi")














