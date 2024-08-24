import cv2
import matplotlib.pyplot as plt
import os
import sys
import datetime

def ask() :
    absolute_path_file=sys.argv[1]
    return absolute_path_file

absolute_path = os.path.dirname(__file__)
absolute_path = absolute_path.replace("\\","/")

relative_path_confile = "/ssd_mobilenet_v3_large_coco_2020_01_14.pbtxt"
relative_path_frodel = "/frozen_inference_graph.pb"

full_path_confile=absolute_path+relative_path_confile
full_path_frodel=absolute_path+relative_path_frodel

model =cv2.dnn_DetectionModel(full_path_frodel,full_path_confile)

classLabels=[]

file_name= absolute_path+'/labels.txt'

with open (file_name, 'r') as fpt: 
    classLabels =fpt.read().rstrip('\n').split('\n')

#getting the timestamp
current_time = datetime.datetime.now()
str_date_time= current_time.timestamp()
#date_time = datetime.fromtimestamp(time_stamp)
#str_date_time = date_time.strftime("%d-%m-%Y, %H:%M:%S")

