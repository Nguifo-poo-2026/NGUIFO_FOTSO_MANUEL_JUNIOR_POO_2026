###### **TP9 – Persistance et organisation professionnelle**



**Objectif**

&#x20;Ajouter la persistance des données : lecture des besoins depuis un fichier CSV, sauvegarde du plan, des VLANs, des recommandations et génération d’un r  apport technique texte. Organiser le projet en packages professionnels (model, service, repository, exception, main).



**Notions étudiées**

&#x20;- Lecture/écriture de fichiers (`FileReader`, `FileWriter`)

&#x20;- Format CSV (séparateur `;`)

&#x20;- Architecture en couches (model, service, repository)

&#x20;- Séparation des responsabilités

&#x20;- Génération de rapport



**Fichiers utilisés**

&#x20;- exports/besoins.csv – fichier d’entrée (nom;hôtes)

&#x20;- exports/plan\_adressage.csv – plan VLSM

&#x20;- exports/vlans.csv – liste des VLANs

&#x20;- exports/recommandations.txt – conseils techniques

&#x20;- exports/rapport\_complet.txt – rapport lisible par un humain



**Scénarios testés**

&#x20;1. Campus IRT : chargement de besoins.csv (500,200,120,60,30) → génération complète.

&#x20;2. PME : création manuelle de besoins\_pme.csv (50,20,80,15,40) → modification temporaire de Main pour charger ce fichier → rapport rapport\_pme.txt.



**Résultats obtenus**

&#x20;- Les fichiers sont correctement créés dans le dossier exports.

&#x20;- Le rapport texte est bien structuré et lisible.

&#x20;- Le CSV peut être ouvert dans un tableur.



**Difficultés rencontrées**

&#x20;- Gestion des chemins relatifs (`exports/besoins.csv`) – le dossier exports doit exister à la racine.

&#x20;- Ignorer la première ligne d’en-tête lors de la lecture.

&#x20;- Synchroniser les modifications de ResultatVLSM (ajout de hotesDemandes et `marge`) avec les autres classes.



**Réponses aux questions**



&#x20;1. Qu’est-ce que la persistance des données ?  

&#x20;  C’est le fait de conserver les données en dehors de la mémoire vive (RAM), par exemple sur le disque dur via des fichiers, afin de les retrouver après un redémarrage de l’application.



&#x20;2. Pourquoi une application professionnelle doit-elle sauvegarder ses résultats ?  

&#x20;  Pour archiver, partager, documenter et reprendre ultérieurement le plan d’adressage sans devoir le recalculer.



&#x20;3. Différence entre fichier CSV et rapport texte ?  

&#x20;  Le CSV est structuré pour être traité par des programmes (tableur, script). Le rapport texte est destiné à un humain : mise en page, titres, explications.



&#x20;4. Pourquoi un package `repository` ?  

&#x20;  Il centralise l’accès aux données (lecture/écriture). Si on change de format (base de données, XML), on modifie seulement ce package.



&#x20;5. Pourquoi un package `service` ?  

&#x20;  Il contient la logique métier (calculs, validations, recommandations). Il est indépendant de la façon dont les données sont saisies ou stockées.



&#x20;6. Pourquoi ne pas tout écrire dans `Main` ?  

&#x20;  Pour maintenir la lisibilité, la testabilité et la maintenabilité. Une classe Main surchargée devient rapidement un “spaghetti code” impossible à faire évoluer.



&#x20;7. Pourquoi `besoins.csv` rend l’application plus flexible ?  

&#x20;  L’utilisateur peut modifier le fichier sans recompiler le code, et on peut même générer les besoins par un autre outil.



&#x20;8. Pourquoi la séparation en packages améliore la maintenabilité ?  

&#x20;  Chaque package a une responsabilité claire. On sait où chercher pour modifier la gestion des fichiers (`repository`), une règle métier (`service`) ou une structure de données (`model`).



