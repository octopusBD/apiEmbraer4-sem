from flask import Flask, request, render_template
import os
import re
import pandas as pd

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
    #final_df.to_csv('output.csv', index=False)
    return 'Arquivos processados com sucesso!'

if __name__ == '__main__':
    app.run(debug=True)