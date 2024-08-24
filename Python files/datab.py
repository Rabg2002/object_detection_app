
import os
import mysql.connector as mycon


config = {
  'user': 'root',      #change ur root one too
  'password': 'H2R@Rahul2002',   #change ur password here ( i have changed mine [this is a dummy one] )
  'raise_on_warnings': True,
  'database' : 'obdep'
   }

class datab:
    def __init__(self) :
         self.start_con()

    
    def start_con(self) :   
        try :
            self.mydb = mycon.connect(**config)
            self.mycursor=self.mydb.cursor(buffered=True)
            print("connected")
        except :
            print("here")
            config.pop('database')
            print(config)
            self.mydb = mycon.connect(**config)
            self.mycursor=self.mydb.cursor()
            print("Making db and table")
            self.crea_dab(self.mycursor)
            print("made db and connected")

    def crea_dab(self,mycursor) :       
            db='obdep'

            def create_database(mycursor):
                try:
                    mycursor.execute(
                    "CREATE DATABASE {} ".format(db))
                except :
                    print("database not created")

            try:
                mycursor.execute("USE {}".format(db))
                print("it's good if u seeing this")
            except :
                print("Database {} does not exists.".format(db))
                create_database(mycursor)
                print("Database {} created successfully.".format(db))
                self.mydb.database = db

            self.crea_tab(self.mycursor)

    def crea_tab(self,mycursor) :
        TABLES = {}

        TABLES['Image_data'] = (
        "CREATE TABLE `Image_data` ("
        "  `Session_id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
        "  `Name` varchar(255) NOT NULL,"
        "  `Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
        "  `Length` int(4) NOT NULL,"
        "  `Total_objects` int(20) NOT NULL DEFAULT 0"
        ")")

        TABLES['LocalVideo_data'] = (
        "CREATE TABLE `LocalVideo_data` ("
        "  `Session_id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
        "  `Name` varchar(255) NOT NULL,"
        "  `Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
        "  `Length` FLOAT(5,2) NOT NULL,"
        "  `Total_objects` int(20) NOT NULL DEFAULT 0"
        ");")

        TABLES['LiveVideo_data'] = (
        "CREATE TABLE `LiveVideo_data` ("
        "  `Session_id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
        "  `Name` varchar(255) NOT NULL,"
        "  `Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
        "  `Length` varchar(255) NOT NULL DEFAULT 'VARIES',"
        "  `Total_objects` int(20) NOT NULL DEFAULT 0"
        ");")



        for table_name in TABLES:
            table_description = TABLES[table_name]
            try:
                print("Creating table {}: ".format(table_name), end='')
                self.mycursor.execute(table_description)
            except mycon.Error as err:
                    print(err)
            else :
                print("OK")

#don't worry if it shows 'integer display width is deprecated and will be removed in a future release'

# def ima_exe() :

#     ins_data(glo_cur)   
#     siro=glo_cur.execute(ins_data(glo_cur).insq)
#     ins_data.num=siro
#     for table_namea in dicto:
#         table_description2 = dicto[table_name]
#         try:
#             print("Creating table and inserting data ".format(table_namea), end='')
#             glo_cur.execute(table_description2)
#         except mycon.Error as err:
#                 print(err)
#         else :
#             print("OK")



if __name__ == "__main__":
    os.system('python -m pip install mysql-connector-python')
    con=datab()





