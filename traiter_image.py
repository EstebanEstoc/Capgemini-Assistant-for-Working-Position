import json
import pickle
from brightness.brightness import calculate_brightness
from keypoints.detectvisage import detectvisage
from keypoints.xydif import xydif
import sys

cheminPhoto = sys.argv[1]

# On récupère detector et predictor pour pouvoir utiliser detectvisage
detectorFile = open("./stockage/detector.txt", "rb")
detector = pickle.loads(detectorFile.read())
predictorFile = open("./stockage/predictor.txt", "rb")
predictor = pickle.loads(predictorFile.read())

# On récupère les coordonnées de la bonne posture
fichier = open("./stockage/coordonnees.json", "r")
coordonneesValides = json.loads(fichier.read())
fichier.close()

# On récupère les coordonnees de la posture actuelle
x11,y11,x19,y19,x17,y17,D = detectvisage(cheminPhoto, detector, predictor)

# On identifie si c'est une bonne posture
boolPosture, boolDistance = xydif(coordonneesValides, [x11,y11,x19,y19,x17,y17,D])

# On identifie si la luminosite est bonne
boolBrightness = calculate_brightness(cheminPhoto)

# On renvoie tous les booleens
print(str(boolPosture) + ";" + str(boolDistance) + ";" + str(boolBrightness))
