# import the necessary packages
from imutils import face_utils
import numpy as np
import argparse
import imutils
import dlib
from math import *
import cv2

def initialisation():
    # construct the argument parser and parse the arguments
    #ap = argparse.ArgumentParser()
    #ap.add_argument("-p", "--shape-predictor", required=True,
    #	help="path to facial landmark predictor")
    #ap.add_argument("-i", "--image", required=True,
    #	help="path to input image")
    #args = vars(ap.parse_args())

    # initialize dlib's face detector (HOG-based) and then create
    # the facial landmark predictor
    detector = dlib.get_frontal_face_detector()
    predictor = dlib.shape_predictor("keypoints/shape_predictor_68_face_landmarks.dat")
    
    return detector, predictor

