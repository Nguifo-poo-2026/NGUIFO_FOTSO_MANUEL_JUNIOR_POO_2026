1. Présentation du Projet
L'application IPPlan-Manager est un outil de gestion d'infrastructure réseau développé en Java. Ce module, correspondant au TP5, porte sur l'implémentation du moteur VLSM (Variable Length Subnet Mask). Sa fonction principale est d'automatiser le découpage d'une adresse réseau mère en plusieurs sous-réseaux de tailles variables. En adaptant précisément le masque au nombre d'hôtes demandés par chaque service, l'outil permet d'optimiser l'espace d'adressage et d'éviter le gaspillage d'adresses IP.

2. Architecture du Module VLSM
Le projet respecte les principes de la Programmation Orientée Objet avec une séparation claire des responsabilités à travers les classes suivantes :

BesoinReseau : Objet stockant les données d'entrée comme le nom du service et le nombre d'hôtes.

ResultatVLSM : Objet stockant les données calculées telles que l'IP réseau, le CIDR, le masque décimal et les plages utilisables.

CalculateurReseau : Classe utilitaire contenant la logique mathématique pour les conversions d'adresses et le calcul de la taille des blocs.

MoteurVLSM : Classe de service métier qui orchestre le tri des besoins et la génération automatique du plan d'adressage.

3. Réponses aux Questions de Compréhension

Question 1. Pourquoi le VLSM permet-il d'économiser les adresses IP ?
Le VLSM permet d'utiliser des masques de longueurs différentes. On n'alloue que le strict nécessaire à chaque sous-réseau, par exemple un masque /29 pour un besoin de 5 hôtes au lieu d'un masque /24 standard. Cela laisse beaucoup plus d'adresses disponibles pour d'autres besoins futurs.

Question 2. Pourquoi faut-il traiter les plus grands besoins en premier ?
C'est une contrainte algorithmique. Pour garantir que les blocs d'adresses s'alignent sur des frontières binaires correctes et éviter de créer des espaces vides inutilisables dans l'adresse IP, il faut toujours placer les plus gros réseaux au début de la plage d'adressage.

Question 3. Quelle est la différence entre un besoin réseau et un résultat VLSM ?
Le besoin est l'expression de l'attente de l'utilisateur avant le calcul. Le résultat est la solution technique finale produite par le moteur, incluant l'adresse réseau exacte, le masque et la plage d'IP utilisables.

Question 4. Pourquoi la classe MoteurVLSM est-elle une classe de service métier ?
Elle est considérée comme une classe de service car elle porte toute l'intelligence de l'application. Elle exécute les traitements logiques comme le tri, la boucle de génération des réseaux et l'avancement automatique de l'adresse IP.

Question 5. Pourquoi transforme-t-on une adresse IP en entier pour certains calculs ?
Une adresse IP est techniquement une chaîne de caractères sur laquelle on ne peut pas faire d'opérations mathématiques. En la convertissant en nombre entier, on peut simplement ajouter la taille du bloc calculé pour trouver immédiatement l'adresse du sous-réseau suivant.

Question 6. Quel est le rôle de la méthode calculerCidrPourHotes() ?
Elle automatise la recherche du masque optimal. Elle identifie le masque CIDR le plus petit capable d'accueillir le nombre d'hôtes souhaité tout en réservant obligatoirement les adresses de réseau et de broadcast.

Question 7. Pourquoi une adresse de réseau et une adresse de broadcast ne sont-elles pas attribuées aux machines ?
L'IP de réseau identifie le groupe lui-même pour le routage et l'IP de broadcast sert à envoyer des messages à toutes les machines en même temps. Les attribuer à un ordinateur bloquerait les communications normales sur le réseau.

Question 8. Pourquoi le moteur VLSM représente-t-il une étape importante dans le projet ?
Il constitue le coeur de l'application. C'est ce moteur qui apporte la véritable valeur ajoutée en supprimant les risques d'erreurs de calcul manuel pour l'administrateur.

4. Scénarios de Test et Résultats Obtenus

Scénario 1 : Entreprise Type (Réseau 192.168.1.0)

TECHNIQUE (120 hôtes) : 192.168.1.0/25 | Plage: 1.1 à 1.126 | Capacité: 126

WIFI (80 hôtes) : 192.168.1.128/25 | Plage: 1.129 à 1.254 | Capacité: 126

ADMINISTRATION (50 hôtes) : 192.168.2.0/26 | Plage: 2.1 à 2.62 | Capacité: 62

Scénario 2 : Petite Entreprise (Réseau 10.0.0.0)

WIFI_INVITES (40 hôtes) : 10.0.0.0/26 | Plage: 10.0.0.1 à 10.0.0.62 | Capacité: 62

ADMIN (25 hôtes) : 10.0.0.64/27 | Plage: 10.0.0.65 à 10.0.0.94 | Capacité: 30

COMPTABILITE (12 hôtes) : 10.0.0.96/28 | Plage: 10.0.0.97 à 10.0.0.110 | Capacité: 14

SERVEURS (8 hôtes) : 10.0.0.112/28 | Plage: 10.0.0.113 à 10.0.0.126 | Capacité: 14

Scénario 3 : Campus Universitaire (Réseau 172.16.0.0)

ETUDIANTS (500 hôtes) : 172.16.0.0/23 | Plage: 16.0.1 à 16.1.254 | Capacité: 510

WIFI PUBLIC (200 hôtes) : 172.16.2.0/24 | Plage: 16.2.1 à 16.2.254 | Capacité: 254

PERSONNEL (120 hôtes) : 172.16.3.0/25 | Plage: 16.3.1 à 16.3.126 | Capacité: 126

LABORATOIRE (60 hôtes) : 172.16.3.128/26 | Plage: 16.3.129 à 16.3.190 | Capacité: 62

5. Difficultés Rencontrées
Plusieurs défis ont été relevés durant ce travail :

Le tri des objets : La gestion du tri décroissant sur une liste d'objets complexes a nécessité l'utilisation d'un comparateur spécifique.

Le calcul des plages : La précision nécessaire pour calculer la première et la dernière IP utilisable sans déborder sur les réseaux voisins.

La manipulation binaire : L'utilisation technique des décalages de bits pour transformer des nombres en adresses IPv4 lisibles.

6. Conclusion
Ce projet m'a permis de comprendre concrètement le fonctionnement du VLSM et de mettre en pratique la programmation orientée objet pour résoudre un problème de réseau réel. L'outil final est capable de générer des plans d'adressage sans erreurs.