TP3 – Développement d'applications réparties

Faculté des Sciences de Sfax

Année universitaire : 2026-2027

Matière : Développement d'applications réparties

Sujet : Serveur Multi-threads – Sockets TCP

Activité 3-1 : Acceptation de plusieurs clients
1. Objectif

L'objectif est de développer un serveur TCP multi-thread capable d'accepter plusieurs clients simultanément.

2. Réalisation

Le serveur utilise les sockets TCP et les threads Java pour gérer chaque client indépendamment.

3. Tests
   Démarrage du serveur

[Ajouter la capture d'écran]

Connexion de plusieurs clients

[Ajouter la capture d'écran]

Test avec Telnet

[Ajouter la capture d'écran]

Test sur le réseau local

[Ajouter la capture d'écran]

Activité 3-2 : Amélioration de l'application calculatrice
1. Objectif

Améliorer l'application calculatrice pour permettre à plusieurs clients d'effectuer des opérations simultanément.

2. Classes développées
   Operation.java : représente une opération mathématique.
   Resultat.java : représente le résultat retourné par le serveur.
   ServeurCalculatrice.java : accepte les connexions.
   ClientProcess.java : traite les opérations de chaque client dans un thread.
   Client.java : permet d'envoyer les opérations au serveur.
3. Tests et résultats
   Démarrage du serveur calculatrice

[Ajouter la capture d'écran]

Exécution d'une opération

[Ajouter la capture d'écran]

Connexion de plusieurs clients

[Ajouter la capture d'écran]

Gestion du compteur global

[Ajouter la capture d'écran]

Test sur le réseau local

[Ajouter la capture d'écran]

4. Synthèse

Ce TP nous a permis de comprendre le fonctionnement d'un serveur multi-thread en Java et la communication par sockets TCP.

Nous avons réalisé une application capable de gérer plusieurs clients simultanément grâce aux threads.

Nous avons également utilisé la communication par objets Java avec ObjectInputStream et ObjectOutputStream.

Enfin, nous avons introduit un compteur global partagé entre les différents threads et synchronisé son accès afin de garantir la cohérence des opérations traitées.