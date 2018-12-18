#!/bin/bash

unzip RDV.zip

javac ./webapps/projetWeb/WEB-INF/classes/*.java


touch "./webapps/projetWeb/BDD/data.db"

echo "DROP TABLE utilisateurs,RDV,MAGASIN,CLIENTS;" | sqlite3 "./webapps/projetWeb/BDD/data.db"

echo "CREATE TABLE IF NOT EXISTS utilisateurs(identifiant VARCHAR(20),password VARCHAR(20));"  | sqlite3 "./webapps/projetWeb/BDD/data.db"

echo "INSERT INTO utilisateurs VALUES('root','root');"  | sqlite3 "./webapps/projetWeb/BDD/data.db"

echo "CREATE TABLE IF NOT EXISTS MAGASIN(NOM TEXT,ADRESSE TEXT,EMAIL TEXT,TEL TEXT,TEXTEBIENVENUE TEXT,URLIMAGE TEXT, LUNDI TEXT,MARDI TEXT,MERCREDI TEXT,JEUDI TEXT,VENDREDI TEXT,SAMEDI TEXT,DIMANCHE TEXT);"  | sqlite3 "./webapps/projetWeb/BDD/data.db"

echo "INSERT INTO MAGASIN VALUES('mon magasin','1 rue de la paix 59000 Lille', 'monmagasin@gmail.com', '0123456789', 'Bienvenue dans mon magasin','./image/accueil.jpg','Fermé','9h00-12h00 14h00-18h00','9h00-12h00 14h00-18h00','9h00-12h00 14h00-18h00','9h00-12h00 14h00-18h00','9h00-12h00 14h00-18h00','Fermé');"  | sqlite3 "./webapps/projetWeb/BDD/data.db"

echo "CREATE TABLE IF NOT EXISTS CLIENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT,NOM VARCHAR(50),PRENOM VARCHAR(50),EMAIL VARCHAR(20),TEL VARCHAR(20),RAPPEL_PAR VARCHAR(20));"  | sqlite3 "./webapps/projetWeb/BDD/data.db"

echo "CREATE TABLE IF NOT EXISTS RDV(IDCLIENT INT NOT NULL,LUNDI VCHAR(20),MARDI VCHAR(20),MERCREDI VCHAR(20),JEUDI VCHAR(20),VENDREDI VCHAR(20),SAMEDI VCHAR(20),MATIN VCHAR(20),APREM VCHAR(20));"  | sqlite3 "./webapps/projetWeb/BDD/data.db"



sh "./startup.sh"

firefox "http://localhost:8080/projetWeb/Index" && firefox "http://localhost:8080/projetWeb/login.html"



