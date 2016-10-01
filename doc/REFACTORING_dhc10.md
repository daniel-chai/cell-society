# Refactoring Lab Exercise

The design issue that I looked at was the issue of passing in parameters to the Simulation
constructors. This affected the following classes:
- SceneManager
- FireSimulation
- GameOfLifeSimulation
- PredatorPreySimulation
- SegregationSimulation

Previously, the Simulation constructors were taking in all the parameters as Strings and then
parsing them as desired. For example, rows and columns would be taken in as type String
and then parsed into ints in the Simulation classes. This was done because the XML parsing
provides all these values as Strings. However, the problem with this is that attributes like 
rows and columns fundamentally are not Strings and should be appropriately passed in as ints. 
To solve this issue, I moved the parsing into the SceneManager. This makes more sense because
the SceneManager also calls the XML parsing classes right now. So the Simulation classes
shouldn't have to know about parsing and should just focus on the functionality of the 
simulations. But perhaps even better would be creating a separate class just for getting the
XML inputs into the right format. That would result in even more separation of concerns.