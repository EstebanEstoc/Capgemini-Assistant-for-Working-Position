from imutils import face_utils
import numpy as np
import argparse
import imutils
import dlib
from math import *
import cv2
import json
import pickle
from keypoints.facial_landmarks import initialisation
from keypoints.detectvisage import detectvisage
import sys


def timeUp(path,detector,predictor):

        # load the input image, resize it, and convert it to grayscale
        image = cv2.imread(path)
        #image = imutils.resize(image, width=500)
        gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

        # detect faces in the grayscale image
        rects = detector(gray, 1)
        if (len(rects) == 0):
          print("False")
        else:
          print("True")


cheminPhoto = sys.argv[1]
print(cheminPhoto)
# On lance la fonction d'initialisation pour l'analyse d'image
detector, predictor = initialisation()

# On sauvegarde le detector
detectorFile = open("./stockage/detector.txt", "wb")
detectorFile.write(pickle.dumps(detector))
detectorFile.close()

# On sauvegarde de predictor
predictorFile = open("./stockage/predictor.txt", "wb")
predictorFile.write(pickle.dumps(predictor))
predictorFile.close()

timeUp(cheminPhoto, detector, predictor)
