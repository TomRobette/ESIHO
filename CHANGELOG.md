// ICI SERONT NOTÉES LES MODIFICATIONS FAITES (si elles sont assez pertinentes) AU CODE

// Veuillez déposer à partir d'ici :
#TomRobette#04/05/2021#
-Mise en place du déplacement du joueur de case en case
-Changement de ce fichier changelog.txt en CHANGELOG.md

#TomRobette#02/09/2020#
-Corrections de bugs
-Ajout d'un titre à la fenêtre
-Création d'un .jar avec seulement le combat
-Création d'un nouveau .jar non fonctionnel
-Correction du bug de localisation des tilesets

#TomRobette#01/09/2020#
-Ajout de la fonctionnalité de suppression d'entités
-Les entités vaincues en combat désapparaissent
-Classes Quest et QuestStatus faites
-Ajout d'un dialog Journal pour voir les quêtes.
-Correction de quelques bugs
-Ajout du compteur d'or dans l'inventaire
-Ajout d'un icon
-Ajout d'un Titre au menu

#TomRobette#28/08/2020#
-Ajout de PNJs et entités divers
-Ajout d'un combattant Slime
-Quelques optimisations

#TomRobette#26/08/2020#
-Correction du bug d'affichage du dialog de fin de combat

#TomRobette#25/08/2020#
-Ajout d'un dialog lors d'une fin de combat

#TomRobette#20/08/2020#
-Correction du bug d'affichage de l'inventaire
-Ajout de la liste des combattants dans le menu inventaire

#TomRobette#18/08/2020#
-Ajout de la possibilité d'initialiser le contenu d'un coffre depuis le fichier .entities de la carte
-Portage des discussions et ajout d'Items en classe Dialog

#TomRobette#11/08/2020#
-Ajout de la possibilité de faire apparaître les NPC avec un sens prédéfini
-Correction du bug d'affichage lors de l'apparition des NPC

#TomRobette#05/08/2020#
-Ajout d'une pause activée lors d'une intéraction

#TomRobette#03/08/2020#
-Correction supplémentaire du bug de caractères
-Ajout des Conversations
-Ajout de classes indépendantes selon les Npc

#TomRobette#01/08/2020#
-Correction du bug lié aux caractères étranges
-Ajout des méthodes getValeur et getSprite dans les objets
-Affichage des objets lorsqu'ils sont obtenus un coffre.

#TomRobette#29/07/2020#
-Ajout d'une classe ScreenLoader pour accéder aux écrans et interfaces
-Les npc peuvent maintenant lancer des combats
-Ajout d'un accès au nom des objets depuis la classe Item
-Ajout d'un UI lorsqu'un Item est obtenu

#TomRobette#28/07/2020#
-Correction d'un bug majeur lié aux tilesets des maps
-Ajout d'une nouvelle map

#TomRobette#27/07/2020#
-Déplacement des Npc
-Correction des bugs d'animation

#TomRobette#26/07/2020#
-Ajout d'une classe NPC
-Corrections de bugs

#TomRobette#17/07/2020#
-L'animation des Activables ne se fait que lors de l'activation
-CombatEntity et CombatEntityType renommés en Combattant et CombattantType
-Ajout d'une classe Activable et Coffre, la seconde, ainsi que d'autres plus tard, hérite de la première.

#TomRobette#17/07/2020#
-Les combats sont fonctionnels
-Corrections de divers bugs lors du combat

#TomRobette#14/07/2020#
-Ajout de l'écran pour switch le combattant

#TomRobette#13/07/2020#
-Divers bugs réglés
-Ajout d'une Team pour monstres
-Ajout des noms lors du combat
-Correction du positionnement des barres de vie
-Ajout d'un écran de victoire ou de défaite

#TomRobette#09/07/2020#
-Ajout des barres de vies lors du combat
-Refonte du système de combat

#TomRobette#02/07/2020#
-Ajout d'animations pour les entités
-Corrections de bugs d'animations
-Refonte du système de déplacement (Maintenant ce n'est plus Tiles par Tiles)

#TomRobette#23/06/2020#
-Ajout d'un écran de debug (ScreensScreen) où l'on sélectionne les autres écrans
-Restructuration Majeure des Items
-Restructuration Majeure des Teams
-Restructuration Mineure des CombatEntities
-Ajout de collisions avec les entités collidable

#TomRobette#22/06/2020#
-Modification du Launcher pour pouvoir passer entre plusieurs écrans
-Ajout de l'écran du jeu
-Ajout de l'écran titre (sans titre)

#TomRobette#18/06/2020#
-Corrections de bugs liés aux déplacements

#TomRobette#17/06/2020#
-Affichage des maps
-Reconstruction des déplacements

#TomRobette#16/06/2020#
-Début de restructuration du projet pour LibGDX

#TomRobette#08/06/2020#
-L'interface de combat fonctionne !

#TomRobette#05/06/2020#
-Refonte de la classe Combat
-Interface de combat fonctionnelle mais très basique. Il reste à debugger.

#ThomasDegave#03/06/2020#
-Création d'une maquette en fxml pour créer l'interface du profile
-Création et début de l'interface du profile sans fxml

#TomRobette#01/06/2020#
-Refondement des fonctions de Sauvegardes en sérialisation

#TomRobette#25/05/2020#
-Ajout d'une méthode getMap
-Ajout d'une fonction de tp

#TomRobette#25/05/2020#
-Correction de la classe Ajouter
-Correction de la classe Enlever
-Correction de la classe Engine
-Ajout d'une fonction statique getItem dans Item
-Ajout d'une fonction statique getTeam dans Team

#TomRobette#22/05/2020#
-Ajout d'un système de collision avec les npc
-Ajout d'un système d'interaction avec les npc

#TomRobette#21/05/2020#
-Ajout d'un ArrayList d'ArrayList de Booléens pour les collisions dans la map
-Ajout d'une fonction de lecture de fichiers de collisions dans Layer
-Ajout de l'affichage de Pnjs dans la map
-Création d'un système (Très rudimentaire) de collision
-Correction d'un bug concernant l'affichage des maps et leurs coordonnées
-Correction d'un bug concernant la lecture des collisions du Layer

#TomRobette#19/05/2020#
-Correction des bugs

#TomRobette#18/05/2020#
-Ajout d'une fonction directionnelle pour le déplacement
-Ajout d'une animation lors du déplacement

#TomRobette#17/05/2020#
-Ajout d'une lecture de touches
-Ajout d'une initialisation de Tileset pour les Sprites de PNJs (Spritesets)
-Ajout d'un PNJ joueur avec une liste de tilesets. La fonction d'appel est en statique dans PNJ
-Ajout d'un système de déplacement (très basique)

#TomRobette#16/05/2020#
-Optimisation (majeure !) de l'affichage des maps (concernant la récupération et la lecture des tilesets)

#TomRobette#11/05/2020#
-Il est maintenant possible d'afficher un tile venant d'une map
-Modification du tableau de tableau de la classe Layer en une collection de collection
-Affichage des maps terminé.

#TomRobette#08/05/2020#
-Changement majeur de la classe decor (transformée en la classe Tiles)
-Ajout d'une classe Tileset
-Ajout d'une lecture de fichier image dans Tileset
-Ajout de la lecture et de l'ajout des Tiles (anciennement décors)
-Les Ids des Tileset vont se présenter ainsi : Zx	où x est le nombre. Ex : Z2
-Les Ids des Tiles vont se présenter ainsi : Tx-y	où T pour Tile, x pour l'id (sans le caractère Z) Tileset correspondant, et y pour l'id du Tile (sans le caractère non plus)
ex : T2-28 => Le 28e Tile du tileset 2
-Ajout de la classe Layer (couche) qui contiendra un tableau de tableau de Tile
-Modification de la classe Map. C'est maintenant une classe avec 3 Layer et une dernière couche pour les Pnjs
-Ajout d'une map test
-Ajout de la lecture des fichiers dans Layer
-Ajout de la construction de couche dans Layer

#TomRobette#02/05/2020#
-Les attaques ont été mises en statique

#FabienBoulnois#29/04/2020#
-Creation de la page MortRespawn
-Ajout de la fonction test mort qui renvoit vers la page MortRespawn

#ThomasDegave#22/04/2020#
-Création d'une page pour le lvl up

#TomRobette#15/04/2020#
-Liaison du controleur combat et de la classe combat

#ThomasDegave#15/04/2020#
-Boutons sur la droite de la page de combat sont fonctionnels

#ThomasDegave#14/04/2020#
-Création d'un menu pour tester les differentes "maquettes" (espace de combat pour le moment)

#TomRobette#13/04/2020#
-Ajout de la classe Quete et de ses méthodes
-Ajout de la listeQuetes et de sa procédure d'initialisation à InitContenu.

#AlexisO'grady#07/04/2020#
-Ajout d'un arc dans listeItems
-Ecriture d'items divers

#FabienBoulnois#06/04/2020#
-Creation des pages Options / Nouvelle Partie
-Modifications du menu principal

#TomRobette#06/04/2020#
-Ajout d'une classe Sauvegarde dans "GameData.Default.Save"
-Ajout d'une méthode écrivant une arborescence pour la sauvegarde si elle n'existe pas
-Ajout d'une méthode créant un fichier de sauvegarde
-Ajout d'une chaîne de caractère "position" dans Team ayant pour but de savoir où est l'équipe. 
C'est une chaîne de caractère, il faut donc faire "id de la map"+"position x"+"position y".
-Correction de MoveList
-Ajout d'une méthode écrivant toute la progression du jeu (Avec les classes que nous avons pour l'instant) dans Sauvegarde

#AlexisO'grady#31/03/2020#
-Ajout dans la listeItems dans InitContenu d'un baton, d'une dague, d'une épée, de 2 armures et 2 potions
-Maj des fonctions useItem dans Arme, Armure, Consommable
-Ajout des fonctions addPv et removePv dans PV

#ThomasDegave#31/03/2020#
-Création des pnj

#TomRobette#31/03/2020#
-Ajout d'une fonction analyseVictoire() dans la class Combat
-Le combat s'arrête en cas de victoire ou de défaite
-Ajout d'une fonction lvlUp

#TomRobette#22/03/2020#
-Ajout de méthodes liées à l'argent dans la classe Team
-Ajout d'un cheat pour ajouter de l'argent et pour en retirer

#TomRobette#21/03/2020#
-Ajout du système de priorisation des pnj en fonction de leur vitesse
-Création de l'IA de combat
-Création de système de tour par tour
-Ajout de l'utilisation de l'attaque sur une cible
-Pour l'instant il n'y a pas de saisie

#TomRobette#16/03/2020#
-Ajout d'une librairie Cheat
-Ajout d'un moteur aux cheats
-Ajout de Classes en fonction du type de cheat (Enlever, Ajouter, Téleporter, etc)
-Ajout d'un système d'analyse du code de cheat
-Classes Enlever et Ajouter terminées
-Ajout de Id dans la classe Team
-Modification de la listeentity de la classe Team en listePNJ
-Ajout des méthodes get et set Id, addPnj, removePnj et getInventaire à la classe Team
-Ajout de listeTeam dans la classe InitContenu
-Le MainActivity n'est maintenant plus lançable de lui-même, mais grâce à un launcher

#ThomasDegave#12/03/2020#
-Création d'un package Test, qui contient un fichier par personne pour faire des test.

#ThomasDegave#11/03/2020#
-Réorganisation de la classe Pnj avec une liste d'image a la place d'une image
-Remplis la méthode initPnjs() avec les Pnj qui seront dans le jeu
-Commencer à completer la classe Entity
!InitContenu Entity pas terminé

#TomRobette#11/03/2020#
-Modifications des ID en String
-Ajout des coefficients d'armure et armes dans les modifiers du combat
-Correction des attributs de la classe Entity

#TomRobette#10/03/2020#
-Réorganisation de la librairie Ressources
-Ajout des débuts de classe pour Map, Decor et Pnj
-Début du MainActivity, du Controller, et des FXML

#TomRobette#08/03/2020#
-Réorganisation de la structure du projet
-Ajout des Librairies Default et Contenu (voir les README dans chacun)
-Ajout des débuts de classes comme inventaire, objets, teams, etc

#TomRobette#06/03/2020#
-Réorganisation des classes d'affichage en une seule
-Création d'un MainActivity
-CREATION DE LA STRUCTURE DU PROJET : ESIHO

#TomRobette#05/03/2020#
-Ajout d'un diagramme de classe avec celles qui sont déjà faites
-Ajout sur le diagramme des classes items, map, inventaire, la plupart des affichages UI, décor, pnj, etc. Il reste à compléter certaines

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
LES PLUS RÉCENTS EN HAUT !!!!!!
