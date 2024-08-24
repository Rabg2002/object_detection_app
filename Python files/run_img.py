import cv2
import matplotlib.pyplot as plt
import base_model
from datab import datab
from pathlib import Path
import os
import mysql.connector as mycon
import numpy as np

class run_img(datab) :

        def __init__(self):
                self.RU_IMG()
                super().__init__()
                self.dato_ins()
                self.ins_data()
                
        k=base_model.ask()
        print(k)#absolute file path
        img =  cv2.imread(k) #reads 
        fname=Path(k).stem #reads only the file name
        file_size=os.path.getsize(k) #gets file size in bytes
        fsize=file_size/1024.0

        model= base_model.model

        model.setInputSize(320,320)
        model.setInputScale(1.0/127.5)
        model.setInputMean((127.5,127,5,127.5))
        model.setInputSwapRB(True)
        ClassIndex, confidece, bbox= model.detect(img, confThreshold= 0.5)

        def RU_IMG(self) :

                font_scale = 3
                font = cv2.FONT_HERSHEY_PLAIN
                print(type(self.ClassIndex))
                for ClassInd, conf, boxes in zip(self.ClassIndex.flatten(), self.confidece.flatten(), self.bbox):
                        print(ClassInd)
                        cv2.rectangle(self.img, boxes, (255,0,0),2)
                        cv2.putText(self.img, base_model.classLabels[ClassInd-1], (boxes[0]+10, boxes[1]+40), font, fontScale = font_scale, color=(0,255,0), thickness=3)
                plt.imshow(cv2.cvtColor(self.img, cv2.COLOR_BGR2RGB))
                plt.show()

                

        def dato_ins(self) :
                list_res=[]
                res={}
                for i in self.ClassIndex :
                        if i not in list_res :
                                list_res.append(i)
                                res[i]=np.count_nonzero(self.ClassIndex == i)
                                #list_res.append(i-1)
                Total_ele = len(list_res)
                return Total_ele,res




        #k=datab.start_con()
        # for table_name in table_ins:
        #     table_description2 = table_ins[table_name]
        #     try:
        #         print("Creating table {}: ".format(table_name), end='')
        #         mycursor.execute(table_description2)
        #     except datab.mycon.Error as err:
        #             print(err)
        #     else :
        #         print("OK")
                #inserting the data into the database 

        def ins_data(self) :
                table_ins={}
                krip=self.dato_ins()
                class_counts=krip[1]

                #adding into the main session window 
                table_ins['insq']="insert into image_data(Name,Length,Total_objects) values('{naamfile}','{size}',{obj_no});".format(naamfile=self.fname,size=self.fsize,obj_no=krip[0])
                self.mycursor.execute(table_ins['insq'])
                print("inserted")
                

                self.mycursor.execute("SELECT MAX(Session_id) from image_data;")
                for row in self.mycursor :
                        print(row)
                        numo=row[0]
                
                cre_tab="CREATE TABLE `{numb}_localimg_inside` ( `Name` varchar(255) NOT NULL,`frequency` bigint(20) NOT NULL);".format(numb=numo)
                try :
                        self.mycursor.execute(cre_tab)
                        
                except mycon.Error as err:
                        print(err)
                
                print(numo)
                for class_label, count in class_counts.items():                        
                        inse_tab="insert into {numbo}_localimg_inside values( '{name_of_ele}' , {frequency} );".format(numbo=numo,name_of_ele=base_model.classLabels[class_label-1],frequency=count)
                        print(numo,class_label,count)
                        try :
                                self.mycursor.execute(inse_tab)
                                print("should be")
                        except mycon.Error as erro:
                               print(erro)
                        print("here")

                self.mydb.commit()

                self.mydb.close()

                # for table_name in table_ins:
                #                 table_description = table_ins[table_name]
                #                 try:
                #                         print("Creating table {}: ".format(table_name), end='')
                #                         self.mycursor.execute(table_description)
                #                 except self.mycon.Error as err:
                #                         print(err)
                #                 else :
                #                         print("OK")

if __name__ == "__main__":
    obj=run_img()

    
    





