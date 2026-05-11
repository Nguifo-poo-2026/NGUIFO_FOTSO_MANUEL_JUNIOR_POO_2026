# TP3 : Collections et Composition dans IPPlan-Manager

## Objectifs
* [cite_start]Mise en œuvre de la composition entre classes Java[cite: 34, 73].
* [cite_start]Manipulation des collections avec `ArrayList`[cite: 35, 109].
* [cite_start]Création de relations complexes entre les réseaux et les équipements [cite: 29-30].

## Notions étudiées
* [cite_start]**Composition** : Un équipement possède désormais une liste d'interfaces[cite: 138, 229].
* [cite_start]**Collections** : Utilisation de `java.util.ArrayList` pour stocker dynamiquement les données[cite: 118, 338].
* [cite_start]**Parcours** : Utilisation de la boucle `for-each` pour l'affichage et la recherche[cite: 203, 240].

## Tests réalisés
* [ ] Création d'une infrastructure avec 3 sous-réseaux (Admin, Tech, WiFi).
* [ ] Ajout de 6 équipements (Routeur, Switch, Serveurs, PC).
* [ ] Test de la méthode `rechercherEquipement()` : 
    * Résultat pour un nom existant : [Succès/Échec]
    * Résultat pour un nom inexistant : [Succès/Échec]

## Difficultés rencontrées
* *Exemple : Comprendre comment l'ArrayList remplace l'attribut unique du TP2.*
* *Exemple : Gestion de la casse dans la méthode de recherche.*

## Réponses aux questions
1-Qu'est-ce qu'une composition en POO ?
C'est une relation "fortement liée" entre deux classes où un objet (le "tout") possède un ou plusieurs objets d'une autre classe (les "parties") comme attributs . Par exemple, si l'objet Equipement est supprimé, ses InterfaceReseau n'ont plus de raison d'exister. 
2-Pourquoi utilise-t-on ArrayList dans ce TP ?
On l'utilise pour gérer des ensembles d'objets dont le nombre peut varier dynamiquement . Contrairement à un tableau classique (fixe), l'ArrayList permet d'ajouter des équipements ou des interfaces au fur et à mesure sans saturer la mémoire à l'avance.  
3-Quelle différence existe entre une variable simple et une collection ?
Une variable simple (ex: String nom) ne contient qu'une seule donnée à la fois. Une collection (ex: ArrayList) est un conteneur capable de stocker et d'organiser plusieurs objets du même type simultanément.  
4-Pourquoi un équipement possède-t-il plusieurs interfaces ?
Pour être réaliste : dans une infrastructure réelle, un routeur ou un serveur doit pouvoir se connecter à plusieurs réseaux différents en même temps (ex: un port pour le LAN, un pour le WAN).  
5-Pourquoi une infrastructure réseau contient-elle plusieurs sous-réseaux ?
Cela permet de segmenter le trafic pour des raisons de sécurité et d'organisation (ex: séparer le WiFi des invités du réseau de l'administration). 
6- Quel est le rôle de la boucle for-each ?
Son rôle est de parcourir chaque élément d'une collection du premier au dernier de manière simplifiée, sans avoir à gérer un compteur ou un index i. 
7- Pourquoi la classe InfrastructureReseau devient-elle importante ?
Elle devient le "cœur" ou le chef d'orchestre du projet car elle regroupe tous les éléments (équipements et sous-réseaux) pour représenter un réseau complet et cohérent. 
8- Pourquoi les collections sont-elles indispensables dans les applications professionnelles ?
Parce qu'elles permettent de manipuler de grands volumes de données de manière flexible (recherche, tri, ajout dynamique), ce qui est impossible avec des variables isolées .  