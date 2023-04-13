import oci
import cx_Oracle

class OracleDataIngestion:
    def __init__(self, config_file_path, object_storage_namespace, object_storage_bucket, db_username, db_password, db_dsn):
        self.config = oci.config.from_file(config_file_path)
        self.object_storage_namespace = object_storage_namespace
        self.object_storage_bucket = object_storage_bucket
        self.db_username = db_username
        self.db_password = db_password
        self.db_dsn = db_dsn

    def read_data(self):
        object_storage = oci.object_storage.ObjectStorageClient(self.config)
        object_response = object_storage.get_object(self.object_storage_namespace, self.object_storage_bucket)
        data = object_response.data.decode('utf-8').splitlines()
        return data

    def insert_data(self, data):
        db_connection = cx_Oracle.connect(self.db_username, self.db_password, self.db_dsn)
        cursor = db_connection.cursor()
        for line in data:
            columns = line.split(',')
            cursor.execute('INSERT INTO chashi (chave, valor1, valor2, valor3) VALUES (:1, :2, :3, :4)', columns[:4])
            cursor.execute('INSERT INTO boletim (id_chashi, data, titulo, conteudo) VALUES (:1, :2, :3, :4)', columns[4:])
        db_connection.commit()
        cursor.close()
        db_connection.close()

if __name__ == '__main__':
    config_file_path = 'ocid1.bucket.oc1.sa-saopaulo-1.aaaaaaaamkuaavk6o6h2x7ntkr52nnokrofpywsydvzh43mfwpdnc6bx7fga'
    object_storage_namespace = 'grk5iqubfa0x'
    object_storage_bucket = 'api-embraer'
    db_username = 'aldrik.alvaro@fatec.sp.gov.br'
    db_password = 'Kr54Vy9rf7A45mG'
    db_dsn = 'lzll6xuk2zk7xkbj_medium'

    ingestion = OracleDataIngestion(config_file_path, object_storage_namespace, object_storage_bucket, db_username, db_password, db_dsn)
    data = ingestion.read_data()
    ingestion.insert_data(data)
