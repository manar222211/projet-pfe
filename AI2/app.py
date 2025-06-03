from flask_cors import CORS
import pandas as pd
from flask import Flask, request, jsonify
import pickle
import numpy as np
# Initialisation de l'application Flask
app = Flask(__name__)
CORS(app)
# Chargement du modèle et de l'encodeur
with open('leaves_prediction.pkl', 'rb') as file:
    model = pickle.load(file)
with open('leaves_encoder.pkl', 'rb') as file:
    encodeurs = pickle.load(file)
def utiliser_model_preentraine(nouvelles_donnees, model, encodeurs):
    # Traitement des dates
    nouvelles_donnees['StartDate'] = pd.to_datetime(nouvelles_donnees['StartDate'])
    nouvelles_donnees['EndDate'] = pd.to_datetime(nouvelles_donnees['EndDate'])
    nouvelles_donnees['ProjectStartDate'] = pd.to_datetime(nouvelles_donnees['ProjectStartDate'])
    nouvelles_donnees['ProjectEndDate'] = pd.to_datetime(nouvelles_donnees['ProjectEndDate'])
    nouvelles_donnees['DepositDate'] = pd.to_datetime(nouvelles_donnees['DepositDate'])
    nouvelles_donnees['Start_Month'] = nouvelles_donnees['StartDate'].dt.month
    nouvelles_donnees['Start_Weekend'] = nouvelles_donnees['StartDate'].dt.weekday >= 5
    nouvelles_donnees['End_Month'] = nouvelles_donnees['EndDate'].dt.month
    nouvelles_donnees['End_Weekend'] = nouvelles_donnees['EndDate'].dt.weekday >= 5
    nouvelles_donnees['ProjectStart_Month'] = nouvelles_donnees['ProjectStartDate'].dt.month
    nouvelles_donnees['ProjectEnd_Month'] = nouvelles_donnees['ProjectEndDate'].dt.month
    nouvelles_donnees['Deposit_Month'] = nouvelles_donnees['DepositDate'].dt.month
    nouvelles_donnees['Deposit_Year'] = nouvelles_donnees['DepositDate'].dt.year
    nouvelles_donnees['Deposit_Day'] = nouvelles_donnees['DepositDate'].dt.day
    nouvelles_donnees = nouvelles_donnees.drop(['StartDate', 'EndDate', 'ProjectStartDate', 'ProjectEndDate', 'DepositDate'], axis=1)
    # Encodage des variables catégorielles
    categorie = nouvelles_donnees.select_dtypes(include=object).columns
    for col in categorie:
        if col in encodeurs:
            nouvelles_donnees[col] = encodeurs[col].transform(nouvelles_donnees[col])
        else:
            raise ValueError(f"Colonne inconnue pour l'encodage: {col}")
    # Encodage booléen
    for col in nouvelles_donnees.columns:
        if nouvelles_donnees[col].dtype == 'bool' and col in encodeurs:
            nouvelles_donnees[col] = encodeurs[col].transform(nouvelles_donnees[col])
    # Conversion en tableau
    tableau = np.array(nouvelles_donnees)
    tableau = tableau.reshape(1, -1)
    # Prédiction
    predictions = model.predict(tableau)
    # Gestion des prédictions multiples (2 sorties)
    if predictions.shape[1] == 2:
        status_code, reason_code = predictions[0]
        resultat = {
            'Status': encodeurs['Status'].inverse_transform([int(status_code)])[0],
            'RefuseOrAcceptReason': encodeurs['RefuseOrAcceptReason'].inverse_transform([int(reason_code)])[0]
        }
    else:
        # Cas d'une seule sortie
        resultat = {
            'Status': encodeurs['Status'].inverse_transform([int(predictions[0])])[0]
        }
    return resultat
@app.route('/predict', methods=['POST'])
def predict():
    try:
        json_data = request.get_json()
        df = pd.DataFrame([json_data])  # Convertir en DataFrame
        prediction = utiliser_model_preentraine(df, model, encodeurs)
        return jsonify(prediction)
    except Exception as e:
        return jsonify({'error': str(e)}), 400
if __name__ == '__main__':
    app.run(debug=True)