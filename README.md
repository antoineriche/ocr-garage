

## Exécution de l'application

1. Importer le programme dans un IDE compatible JAVA (IntelliJ, Eclipse).
2. Installer une version de JAVA >= JAVA 8 (utilisation de lambdas et de streams).
3. Lancer l'exécution de la méthode main dans la classe `Main.class`

##  Explications

Si vous n'avez jamais démarré l'application, le terminal vous invite à renseigner un nom de garage.

Les différents véhicules sont ensuite ajoutés à ce dernier et les données sont enregistrées dans le fichier suivant :

`repertoire_project/garages/NOM_DU_GARAGE.txt`
<i> Les informations relatives à la configurations se trouvent dans la classe `GarageUtility.class`</i>

Lors des démarrages suivants, l'application va scanner ce répertoire afin de récupérer les garages enregistrés. Vous pouvez alors choisir de modifier un garage existant ou de créer un nouveau garage en entrant un nouveau nom.

Il est possible de supprimer un garage en supprimant le commentaire autour de la méthode de la classe `Main.class` : `garage.resetGarage();`
