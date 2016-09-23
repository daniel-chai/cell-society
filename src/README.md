# cellsociety 

Put your source code, resources, and property files here.


### Generic Design notes

> Strucutre - StructureView relationship:
	* Considered Strong relationsip between the two
	* Considered calling the update in subclasses, but that would
	  force us to either make a copy of the board for each time we
	  load the view.