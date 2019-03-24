import json
import pickle
from keypoints.facial_landmarks import initialisation
from keypoints.detectvisage import detectvisage
import sys

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

# On récupère les coordonnees de la bonne posture
x11,y11,x19,y19,x17,y17,D = detectvisage(cheminPhoto, detector, predictor)

# On sauvegarde les coordonnées de la bonne posture
fichier = open("./stockage/coordonnees.json", "w")
fichier.write(json.dumps([x11,y11,x19,y19,x17,y17,D]))
fichier.close()
