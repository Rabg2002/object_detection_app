import cv2
import matplotlib.pyplot as plt
import base_model
from datab import datab
from pathlib import Path
import os
import mysql.connector as mycon
import numpy as np

class run_live(datab) :
         
         def __init__(self):
             super().__init__()
             self.kimp=self.macr_data()
             self.RU_LIV()

         #k=base_model.ask()
         cap = cv2.VideoCapture('1')

         model= base_model.model

         model.setInputSize(320,320)
         model.setInputScale(1.0/127.5)
         model.setInputMean((127.5,127,5,127.5))
         model.setInputSwapRB(True)

         if not cap.isOpened():
            cap = cv2.VideoCapture(0)
         if not cap.isOpened():
            raise IOError("Can't open the video")
         
         list_res=[]

         def RU_LIV(self) :
               font_scale = 3
               font = cv2.FONT_HERSHEY_PLAIN

               while True:
                  ret, frame = self.cap.read()
                  
                  ClassIndex, confidece, bbox = self.model.detect(frame, confThreshold= 0.55)
                  #SClassIndex=ClassIndex[:80]
                  
                  print(ClassIndex)
                  
                  if(len(ClassIndex)!=0):
                     for ClassInd, conf, boxes in zip(ClassIndex.flatten(), confidece.flatten(), bbox):
                           if(ClassInd<=80):
                              cv2.rectangle(frame, boxes, (255,0,0),2)
                              cv2.putText(frame, base_model.classLabels[ClassInd-1], (boxes[0]+10, boxes[1]+40), font, fontScale = font_scale, color=(0,255,0), thickness=3)
                  cv2.imshow( 'Obj detection', frame)
                  plt.show()
                  self.dato_ins(self.kimp,ClassIndex)
                  
                  if cv2.waitKey(2) & 0xff == ord('k'):
                     break

               self.cap.release()
               cv2.destroyAllWindows()


         def dato_ins(self,num,ClassInde) :
               
               unique,counts= np.unique(ClassInde,return_counts=True)
               res=dict(zip((unique-1), counts))
               print(res)

               for ele in res :
                     if ele not in self.list_res :
                        self.list_res.append(ele)
                        inse_tab="insert into {numbo}_livevideo_inside values( '{name_of_ele}' , {frequency} );".format(numbo=num,name_of_ele=base_model.classLabels[ele],frequency=(res[ele]))
                        self.mycursor.execute(inse_tab)
                     
                     else : 
                        tq="select frequency from {numb}_livevideo_inside where Name='{ele_name}';".format(numb=num,ele_name=base_model.classLabels[ele])
                        self.mycursor.execute(tq)
                        for row in self.mycursor :
                           print(row,"ru")
                           exis_freq=row[0]      
                        total_freq=int(exis_freq)+res[ele]
                        update_tab="UPDATE {numbo}_livevideo_inside SET frequency= {fre} where Name='{ele_name}';".format(numbo=num,fre=total_freq,ele_name=base_model.classLabels[ele])
                        self.mycursor.execute(update_tab)

               self.mydb.commit()

         def macr_data(self) :
               
               #inserting into the main window 
               insq="insert into livevideo_data(Name) values('{naamfile}');".format(naamfile="LIVE FEED")
               self.mycursor.execute(insq)

               #taking the numo thing
               self.mycursor.execute("SELECT MAX(Session_id) from livevideo_data;")
               for row in self.mycursor :
                        print(row)
                        numo=row[0]              

               #creating the window 
               cre_tab="CREATE TABLE `{numb}_livevideo_inside` ( `Name` varchar(255) NOT NULL,`frequency` bigint(20) NOT NULL);".format(numb=numo)
               try :
                        self.mycursor.execute(cre_tab)
                        print("created instance table")
                        
               except mycon.Error as err:
                        print(err)

               return numo  

if __name__ == "__main__":
    obj=run_live()