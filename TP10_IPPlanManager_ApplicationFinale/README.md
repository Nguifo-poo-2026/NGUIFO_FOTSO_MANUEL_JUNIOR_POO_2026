###### **TP10 – Application finale IPPlan-Manager**



**Objectif**

&#x20;Assembler toutes les fonctionnalités des TPs précédents dans une application console complète avec menu interactif, saisie utilisateur, génération VLSM,  création automatique de VLANs, validations, recommandations intelligentes, sauvegarde des résultats et génération de rapport.



**Fonctionnalités réalisées**

&#x20;- Menu avec trois options : saisie manuelle, chargement depuis CSV, quitter.

&#x20;- Saisie du nom du projet, de l’adresse réseau de départ et des besoins (services + nombre d’hôtes).

&#x20;- Validation de l’adresse IP de départ.

&#x20;- Génération du plan VLSM (tri décroissant, choix du CIDR optimal).

&#x20;- Association automatique des sous-réseaux à des VLANs (ID incrémentés de 10 en 10).

&#x20;- Validation avancée : adresses IP, chevauchements, conflits VLAN.

&#x20;- Moteur de recommandations (WiFi, serveurs, grands VLANs, administration).

&#x20;- Sauvegarde des résultats dans exports : CSV du plan, CSV des VLANs, recommandations texte, rapport complet.

&#x20;- Possibilité de charger les besoins depuis un fichier CSV (format `nom;hotes`).



**Organisation du projet**

&#x20;- ipplanmanager.model : classes métier (BesoinReseau, ResultatVLSM, VLAN, Recommandation)

&#x20;- ipplanmanager.service : services (CalculateurReseau, MoteurVLSM, GestionnaireVLAN, ValidateurPlanAdressage, MoteurRecommandation, RapportService, ApplicationIPPlanManager, règles de recommandation)

&#x20;- ipplanmanager.repository : accès aux fichiers (BesoinRepository, FichierPlanRepository)

&#x20;- ipplanmanager.exception : exceptions personnalisées

&#x20;- ipplanmanager.console : interaction utilisateur (ConsoleService)

&#x20;- ipplanmanager.main : point d’entrée (Main)



**Scénarios testés**

&#x20;1. Campus IRT – saisie manuelle : ETUDIANTS(500), WIFI\_INVITES(200), ENSEIGNANTS(120), LABOS(60), SERVEURS(30) avec adresse de départ 10.10.0.0.

&#x20;2. PME – chargement CSV : ADMIN(50), COMPTA(20), WIFI\_INVITES(80), SERVEURS(15), VOIP(40) depuis exports/besoins\_pme.csv.

&#x20;3. Multi‑services – TECHNIQUE(120), DIRECTION(25), CAMERAS(60), SUPPORT(35), INVITES(100) sur 192.168.1.0.



**Fichiers générés**

&#x20;- exports/Campus\_IRT\_plan.csv – plan d’adressage

&#x20;- exports/Campus\_IRT\_vlans.csv – liste des VLANs

&#x20;- exports/Campus\_IRT\_recommandations.txt – conseils

&#x20;- exports/Campus\_IRT\_rapport.txt – rapport complet



**Difficultés rencontrées**

&#x20;- Assurer la cohérence des imports après découpage en packages.

&#x20;- Gérer la réinitialisation du GestionnaireVLAN entre deux générations.

&#x20;- Synchroniser la nouvelle version de ResultatVLSM avec MoteurVLSM et les autres classes.

&#x20;- Implémenter la recherche de chevauchement avec des adresses entières sans erreur de débordement.



**Réponses aux questions**



&#x20;1. Pourquoi le TP10 représente-t-il une application plus complète ?

&#x20;  Il intègre toutes les briques : saisie utilisateur, calcul VLSM, VLANs, validations, recommandations, persistance et interface console – le tout dans une architecture organisée.



&#x20;2. Rôle de `ApplicationIPPlanManager` ?

&#x20;  C’est la classe orchestratrice. Elle coordonne les services (saisie, calcul, validation, sauvegarde) sans que Main n’ait à connaître ces détails.



&#x20;3. Pourquoi `Main` doit-elle rester courte ?

&#x20;  Pour respecter le principe de responsabilité unique : Main ne fait que lancer l’application. Toute la logique métier est ailleurs, ce qui facilite les tests et l’évolution.



&#x20;4. Pourquoi séparer les packages `model`, `service`, `repository`, … ?

&#x20;  Pour une architecture claire et maintenable. Chaque package a une raison de changer (ex: modification du format CSV → seulement `repository`). C’est le principe de séparation des préoccupations.



&#x20;5. Pourquoi la saisie est dans `ConsoleService` ?

&#x20;  Pour isoler le code d’interaction avec l’utilisateur. Si plus tard on passe à une interface graphique, seul ConsoleService sera remplacé.



&#x20;6. Pourquoi valider l’adresse de départ avant de générer le plan VLSM ?

&#x20;  Une adresse invalide rendrait tous les calculs erronés. La validation précoce évite des erreurs en cascade et permet un message d’erreur immédiat.



&#x20;7.Pourquoi le moteur de recommandations est‑il exécuté après la génération des VLANs ?

&#x20;  Les règles de recommandation analysent des VLANs. Il faut donc d’abord créer les VLANs à partir des résultats VLSM.



&#x20;8. Pourquoi la sauvegarde rend l’application réellement exploitable ?

&#x20;  Un plan d’adressage est un document de travail. Sans persistance, tout est perdu à l’arrêt du programme. La sauvegarde permet de conserver, partager et intégrer le plan dans une documentation.



&#x20;9. Pourquoi le rapport technique est‑il important ?

&#x20;  Il fournit une vue humaine, lisible et synthétique (besoins, plan, VLANs, recommandations) aux administrateurs réseau, aux auditeurs ou pour la formation.



&#x20;10. Améliorations futures possibles ?

&#x20;   - Interface graphique (JavaFX ou Swing)

&#x20;   - Export vers Excel/PDF

&#x20;   - Base de données (SQLite) pour stocker plusieurs projets

&#x20;   - Routage dynamique, calcul des ACLs, etc.



**Conclusion**

&#x20;Ce projet m’a permis de comprendre comment la POO permet de construire pas à pas une application métier réaliste. Chaque TP a ajouté une couche de  complexité utile : encapsulation, collections, calculs réseau, VLANs, validations, recommandations, persistance. Le résultat final est un outil d’aide à la planification IP exploitable en contexte professionnel.

