import pymysql
db= pymysql.connect(host='localhost',
                     port=3306,
                     user='root',
                     passwd='0cN1KvzzVeQuO7xc7fmt',
                     db='test',
                     charset='utf8')


db.cursor().execute("show tables")
sql = """INSERT INTO test_table(name)
         VALUES('test_name')"""
 
db.cursor().execute(sql)
db.commit()
 
sql= """SELECT name FROM test_table"""

db.close()
