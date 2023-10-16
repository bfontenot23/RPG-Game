The program I have chosen to write is the RPG simulator for 96pts.  My apologies to whoever has to grade this.  The main() function exists in Game.java.  For the convenience of the grader, I have
mimicked the file structure of this project in the submission to be downloaded as a zip file, however if this isn't allowed, I've also included an "all in one" .txt file that contains every class
compiled into one file.  I've also copied the file "names.txt" at the bottom of this README, though do note that the code will look for it in the /src folder.  THE NUMBER OF NAMES IS A STATIC
REFERENCE IN THE GENERATENAME() METHOD!  ADDING OR REMOVING NAMES WILL BREAK THE CODE!

Assumptions/Creative Liberties:
- File Hierarchy
    RPG Game/
    ├─ src/
    │  ├─ Character.java
    │  ├─ Player.java
    │  ├─ Enemy.java
    │  ├─ Game.java
    │  ├─ BattleManager.java
    │  ├─ names.txt
    ├─ README.txt
- Multiple Players/Enemies
    - The first thing you'll notice is that you are prompted with how many players/enemies you want.  This is fully functional and working, but please grade the assignment with 1 player and 1 enemy, as
      there may be a case I have not foreseen when running with multiple enemies.  TO THE EXTENT I HAVE TESTED IT WORKS FINE, BUT JUST IN CASE.
- Preparedness
    - While not specified in the scope of the program, I've added the concept of defense, or as I will call it, "preparedness."  For every attack the goblin misses, the player
      will gain one stack of preparedness up to 5.  When the goblin successfully hits the player, the damage dealt will be equal to a random value between 3 and 7 minus the number of
      preparedness stacks present.  This also works in reverse, as the goblin will also generate preparedness, only for balancing, the goblin will instead generate 3 stacks with a max of 9.
      In the code, the variable used to reference this will be "def".  This stat will be invisible to the player.
- Speed
    - Each entity will be given a randomly assigned speed value between 1 and 50.  If the speed of two entities is a tie, the enemy will move first.  This will be used to create
      a turn order as the battle begins.
- Goblin names
    - source: https://www.reddit.com/r/d100/comments/i0f9rx/my_list_of_100_goblin_names_for_your_consideration/
    - Removed names I felt would be inappropriate
- Pacifism
    - Because pacifism is based, nobody actually dies and instead will run away when they are too hurt to continue (JK, I'm just too lazy to add a second boolean variable to determine if a playable
      character is participating in battle or not, as the "fled" variable already does this exact thing.)


names.txt:

Borkle
Marrow
Tododon
Sparkmack
Svish
Mogglewog
Bendigo
Jare
Peacho
Lock
Shock
Barrel
Snik
Snak
Gordo
Nipmonger
Riddle
Spip
Kaa
Bonegrundle
Yaxmax
Tamborine
Riggity
Fishspleen
Bladder Dan
Mumblemorg
Kettle
Gnogin
Eee
Rattrap
Bigsmalls
Pork
Fwip
Gong
Zaza
Meeg
Meeg Two
Meeg Three
Uvano
Pingpang
Bowel
Ham
Gritgrash
Countbean
Sap Sam
Leek Leek
Bwob
Parsnip Jr.
Parsnip Sr.
Fat Cat
Eyemasher
Quiss
Wawa
Spork
Turnaround
Barfknees
KnifeyMcFingers
Cowshout
Spank
Stumpy
Backwater
Crowlaw
Clockwind
Burtlan
Smee
Macintosh
Ol' Crabapple
Muckman
Dirtwallow
Crooknose
Beetlepocket
Sticky
Vraaz
Vick
Brackish
Pondjohn
Waxmuncher
Wicklicker
Candleear
Grimm
Portho
Odo
Fleshgutter
Slugsnatcher
Milksalt
Stewslosh
Cast Iron
Dutch
Squirrelskinner
Froggrope
Topsyturvy
Lardmouth
Sinew
Hypotenoose
Gallow
Boblin