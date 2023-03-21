import oracledb as oracledb

# Cria uma conexão com o banco de dados
connection= oracledb.connect(
     user="ADMIN",
     password="4k24Vy9pd7A66mG",
     dsn="lzll6xuk2zk7xkbj_low",
     config_dir="/Users/Wallet_LZLL6XUK2ZK7XKBJ",
     wallet_location="/Users/Wallet_LZLL6XUK2ZK7XKBJ",
     wallet_password="4k24Vy9pd7A66mG")

# Criar um cursor para executar comandos SQL
cursor = connection.cursor()

# Executar uma consulta SQL
cursor.execute("SELECT * FROM ADMIN.BOLETIM")

# Fetch resultados e exibi-los
result = cursor.fetchall()
print(result)


























# import cx_Oracle
#
# # Configurações da conexão
# username = "ADMIN"
# password = "4k24Vy9pd7A66mG"
# service = "g9a54ba44f55130_lzll6xuk2zk7xkbj_medium.adb.oraclecloud.com"
# #description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.sa-saopaulo-1.oraclecloud.com))(connect_data=(service_name=g9a54ba44f55130_lzll6xuk2zk7xkbj_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))
# region = "adb.sa-saopaulo-1.oraclecloud.com"
#
# # Conexão com Autonomous Database
# dsn = cx_Oracle.makedsn(region, service, "ADB")
# connection = cx_Oracle.connect(username, password, dsn)
#
# # Criar um cursor para executar comandos SQL
# cursor = connection.cursor()
# print("oi")
# # Executar uma consulta SQL
# cursor.execute("SELECT * FROM minha_tabela")
#
# # Fetch resultados e exibi-los
# result = cursor.fetchall()
# print(result)
#
