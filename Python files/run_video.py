import cv2
import matplotlib.pyplot as plt
import base_model
from datab import datab
from pathlib import Path
import os
import mysql.connector as mycon
import numpy as np

class run_video(datab) :
      
      def __init__(self):
          super().__init__()
          self.kimp=self.macr_data()
          self.RU_VID()

      k=base_model.ask()
      cap = cv2.VideoCapture(k)
      print(k)#absolute file path
      fname=Path(k).stem #reads only the file name
      file_size=os.path.getsize(k) #gets file size in bytes
      fsize=file_size/(1024.0*1024.0)

      model=base_model.model

      model.setInputSize(320,320)
      model.setInputScale(1.0/127.5)
      model.setInputMean((127.5,127,5,127.5))
      model.setInputSwapRB(True)

      if not cap.isOpened():
                  cap = cv2.VideoCapture(0)
      elif not cap.isOpened():
                  raise IOError("Can't open the video")
      font_scale = 3
      font = cv2.FONT_HERSHEY_PLAIN

      list_res=[]
      
      def RU_VID(self):

               font_scale = 3
               font = cv2.FONT_HERSHEY_PLAIN

               while True:
                  ret, frame = self.cap.read()
                  
                  ClassIndex, confidece, bbox = self.model.detect(frame, confThreshold= 0.55)
                  
                  print(ClassIndex)

                 #self.ins_data(ClassInde=ClassIndex)
                  
                  if(len(ClassIndex)!=0):
                     for ClassInd, conf, boxes in zip(ClassIndex.flatten(), confidece.flatten(), bbox):
                           if(ClassInd<=80):
                              cv2.rectangle(frame, boxes, (255,0,0),2)
                              cv2.putText(frame, base_model.classLabels[ClassInd-1], (boxes[0]+10, boxes[1]+40), font, fontScale = font_scale, color=(0,255,0), thickness=3)
                  cv2.imshow( 'Obj detection', frame)
                  plt.show()
                  self.dato_ins(self.kimp,ClassIndex)
                  
                  if cv2.waitKey(2) & 0xff == ord('k'):
                     self.mycursor.execute("update localvideo_data set Total_objects={number} where Session_id={id}".format(number=(len(self.list_res)),id=self.kimp))
                     print(self.kimp)
                     print(len(self.list_res))
                     self.mydb.commit()
                     break
                  print('run')

               self.cap.release()
               cv2.destroyAllWindows()
         
      def dato_ins(self,num,ClassInde) :
               
               unique,counts= np.unique(ClassInde,return_counts=True)
               res=dict(zip((unique-1), counts))
               print(res)

               for ele in res :
                     # if the element is not already present in the table (directly insert the data)
                     if ele not in self.list_res :
                        self.list_res.append(ele)
                        inse_tab="insert into {numbo}_localvideo_inside values( '{name_of_ele}' , {frequency} );".format(numbo=num,name_of_ele=base_model.classLabels[ele],frequency=(res[ele]))
                        self.mycursor.execute(inse_tab)
                     
                     else : 
                     # if the element is present (add the previous freq with curr one )
                        tq="select frequency from {numb}_localvideo_inside where Name='{ele_name}';".format(numb=num,ele_name=base_model.classLabels[ele])
                        self.mycursor.execute(tq)
                        for row in self.mycursor :
                           print(row,"ru")
                           exis_freq=row[0]      
                        total_freq=int(exis_freq)+res[ele]
                        update_tab="UPDATE {numbo}_localvideo_inside SET frequency= {fre} where Name='{ele_name}';".format(numbo=num,fre=total_freq,ele_name=base_model.classLabels[ele])
                        self.mycursor.execute(update_tab)

               self.mydb.commit()




               # for ele in ClassInde :
               #          if ele not in self.list_res :
               #                self.list_res.append(ele)
               #                #res[ele]=np.count_nonzero(ClassInde == ele)
               #                inse_tab="insert into {numbo}_localvideo_inside values( '{name_of_ele}' , {frequency} );".format(numbo=num,name_of_ele=base_model.classLabels[i],frequency=(res[i]))
               #                self.mycursor.execute(inse_tab)
               #          # elif (ele in ClassInde[i:]):
               #          #        continue                              
               #          else :
               #                 tq="select frequency from {numb}_localvideo_inside where Name='{ele_name}'".format(numb=num,ele_name=base_model.classLabels[i])
               #                 k=self.mycursor.execute(tq)
               #                 total=k+(np.count_nonzero(ClassInde == i))
               #                 inser_tab="UPDATE {numbo}_localvideo_inside SET frequency= {fre}".format(fre=total)
               #                 self.mycursor.execute(inser_tab)
                              
               # Total_ele = len(self.list_res)
               # return Total_ele,res
               


      def macr_data(self) :
               
               #inserting into the main window 
               insq="insert into localvideo_data(Name,Length) values('{naamfile}','{size}');".format(naamfile=self.fname,size=self.fsize)
               self.mycursor.execute(insq)

               #self.mycursor.execute("insert into localvideo_data(Total_objects) values({objects}) where Session_id = {where} ;".format(To))

               #taking the numo thing
               self.mycursor.execute("SELECT MAX(Session_id) from localvideo_data;")
               for row in self.mycursor :
                        print(row)
                        numo=row[0]              

               #creating the window 
               cre_tab="CREATE TABLE `{numb}_localvideo_inside` ( `Name` varchar(255) NOT NULL,`frequency` bigint(20) NOT NULL);".format(numb=numo)
               try :
                        self.mycursor.execute(cre_tab)
                        print("created instance table")
                        
               except mycon.Error as err:
                        print(err)

               return numo              

      # def ins_data(self,classInde,num) :

      #           krip=self.dato_ins(classInde)
      #           class_counts=krip[1]

      #           #adding into the main session window 
      #           insq="insert into localvideo_data(Name,Time,Length) values('{naamfile}','{samay}','{size}',{obj_no});".format(naamfile=self.fname,samay=base_model.str_date_time,size=self.fsize)
      #           self.mycursor.execute(insq)
      #           print("inserted")
                
      #           self.mycursor.execute("SELECT MAX(Session_id) from localvideo_data;")
      #           for row in self.mycursor :
      #                   print(row)
      #                   numo=row[0]
                
      #           cre_tab="CREATE TABLE `{numb}_localvideo_inside` ( `Name` varchar(255) NOT NULL,`frequency` bigint(20) NOT NULL);".format(numb=numo)
      #           try :
      #                   self.mycursor.execute(cre_tab)
                        
      #           except mycon.Error as err:
      #                   print(err)
                
      #           for class_label, count in class_counts.items():
                               
                                     
      #                   inse_tab="insert into {numbo}_localvideo_inside values( '{name_of_ele}' , {frequency} );".format(numbo=num,name_of_ele=base_model.classLabels[class_label],frequency=count)
      #                   print(num,class_label,count)
      #                   try :
      #                           self.mycursor.execute(inse_tab)
      #                           print("should be")
      #                   except mycon.Error as erro:
      #                          print(erro)
      #                   print("here")


if __name__ == "__main__":
    obj=run_video()