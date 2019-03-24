from imutils import face_utils
import numpy as np
import argparse
import imutils
import dlib
from math import *
import cv2

def detectvisage(path,detector,predictor):

        # load the input image, resize it, and convert it to grayscale
        image = cv2.imread(path)
        #image = imutils.resize(image, width=500)
        gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

        # detect faces in the grayscale image
        rects = detector(gray, 1)

        # loop over the face detections
        for (i, rect) in enumerate(rects):
                        # determine the facial landmarks for the face region, then
                        # convert the facial landmark (x, y)-coordinates to a NumPy
                        # array
                        shape = predictor(gray, rect)
                        shape = face_utils.shape_to_np(shape)
                 
                        # convert dlib's rectangle to a OpenCV-style bounding box
                        # [i.e., (x, y, w, h)], then draw the face bounding box
                        (x, y, w, h) = face_utils.rect_to_bb(rect)
                        cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
                         
                        # show the face number
                        cv2.putText(image, "Face #{}".format(i + 1), (x - 10, y - 10),
                                cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)
                         
                        # loop over the (x, y)-coordinates for the facial landmarks
                        # and draw them on the image
                        i=1
                        for (x, y) in shape:
                                if (i==1) :
                                        x11 = x
                                        y11 = y
                                        i = i+1
                                if (i==9) :
                                        x19 = x
                                        y19 = y
                                        i = i+1

                                if (i==17) :
                                        x17 = x
                                        y17 = y
                                        i = i+1

                                if (i==43):
                                        X1 = x
                                        Y1 = y
                                        #cv2.circle(image, (x, y), 10, (0, 0, 255), -1)
                                        i = i+1
                                if (i==40):
                                        X2 = x
                                        Y2 = y
                                        #cv2.circle(image, (x, y), 10, (0, 0, 255), -1)
                                        i = i+1
                                i = i+1 
                                cv2.circle(image, (x, y), 2, (0, 0, 255), -1)
                        D = sqrt((Y1-Y2)**2+(X1-X2)**2)
                        cv2.imwrite(path, image)
                        return(int(x11),int(y11),int(x19),int(y19),int(x17),int(y17),float(D))
                        
        # show the output image with the face detections + facial landmarks
        #cv2.imshow("Output", image)
        #cv2.waitKey(0)
