gs-netlogo-extension

Dans l'extension actuelle, les commandes suivantes sont disponibles (les noms sont assez �quivoques quant � leur fonction) :

Manipulation de graphs :
gs:add-singlegraph
gs:add-multigraph
gs:remove-graph
gs:set-current-graph    --> on a une liste de Graph cr��s, une liste de Viewer affich�s, mais un seul Graph et un seul Viewer est "current", ce qui permet aux utilisateurs d'avoir moins d'attributs � �crire (noms du graph et viewer concern�s par les commandes)
gs:graphs-names
gs:current-graph-name
gs:clear-current-graph
gs:clear-graph

Manipulation de viewers :
gs:start-viewer
gs:close-viewer
gs:set-current-viewer
gs:viewers-names
gs:current-viewer-name

Manipulation de nodes :
gs:current-nodes-names
gs:add-node  --> certains commandes ne concernent que le Graph courant
gs:an        --> les commandes courtes fonctionnent comme l'exemple : "crt" au lieu de "create-turtles"
gs:add-all-nodes
gs:aan
gs:add-node-to-graph
gs:ang
gs:remove-node
gs:rn
gs:remove-node-from-graph
gs:rng

Manipulation d'egdes :
gs:current-edges-names
gs:add-edge
gs:ae
gs:add-all-edges
gs:aae
gs:remove-edge
gs:re
gs:add-edge-to-graph
gs:aeg
gs:remove-edge-from-graph
gs:reg

gs:grid-generation (exemple d'utilisation d'algo de graphstream.algo)

On peut donc lancer des fen�tres GraphStream � partir de NetLogo et les remplir de Nodes et d'Edges simples. La prochaine �tape est de rapatrier des informations de ces fen�tres vers NetLogo et de transmettre des attributs. A ce sujet, ajouter par d�faut des attributs suppl�mentaires aux turtles n'est pas possible sans toucher au code source de NetLogo. Il para�t hautement pr�f�rable de transmettre un template de NetLogo coupl� � GS aux utilisateurs, contenant d�j�, par exemple, une sp�cialisation des turtles en nodes et des links en edges et les attributs g�n�raux d�j� d�clar�s + une proc�dure de cr�ation / r�cup�ration d'un Graph de GS standard...
====================