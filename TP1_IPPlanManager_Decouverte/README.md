# TP1 : IPPlan-Manager - Découverte de la POO

## Objectif
Modéliser les bases d'un réseau informatique (IP, Interfaces, Équipements) en Java.

## Classes implémentées
- `AdresseIP`
- `ReseauIP`
- `InterfaceReseau`
- `Equipement`

## Réponses aux questions (Section 14)
Classe vs String : Une classe permet d'encapsuler la donnée et de lui ajouter des méthodes (validation, calculs réseaux) plus tard. Une String est juste du texte sans intelligence.

Classe vs Objet : La classe est le plan (le concept Equipement), l'objet est la réalisation concrète (le routeur R1 posé sur la table).

Constructeur : Il sert à initialiser les attributs de l'objet au moment de sa création avec le mot-clé new.

InterfaceReseau contient AdresseIP : Parce qu'une interface possède physiquement une adresse IP ; c'est une relation de composition.

Equipement contient InterfaceReseau : Pour modéliser le fait qu'un matériel réseau a besoin d'une interface pour communiquer.

Limite actuelle : L'équipement ne peut avoir qu'une seule interface, alors qu'en réalité un routeur en a souvent plusieurs.

Insuffisance pour un plan d'adressage : On ne fait que stocker du texte pour l'instant ; il manque la logique mathématique pour calculer les masques, les broadcasts et les plages d'IP.

