Cell Society
====
#### Second project for CompSci 308 Fall 2016

Team 7: Grayson Wise, Ryan Bergamini, Daniel Chai
___
### Introduction

The goal of this project is to implement a program that simulates Cellular Automata. It will allow the user to select an input file, and then run the simulation using the rules defined within that file. The input file will contain information about every cell, it's neighborhood, the possible states of each cell, and the set of rules by which cells change state. However, due to the great range that these instructions could take we must make our code as flexible as possible. 

In order to make our code flexible, we need to have distinct classes that each handle small sections of the overall project. Then, if a change is required, we would simply change the one class that handles that aspect of the project. The other classes would just interact with it the same way, as they would not be changed.  
___
### Overview

Our Application will be based on a SceneManager with various SceneModes. The SceneManager will not be a class with the bulk of the logic of the program; the SceneManager’s purpose will take care of Scene to Scene transitions. The SimulationMode and the InputDataMode will be subclasses of the SceneMode. In the SimulationMode is where the simulation will take place, while the data input will take place in the InputDataMode. 

The SimulationMode will handle the graphics for the simulation, while the Simulation class will handle the logic. Under the umbrella of SimulationMode for the graphics there will be a GraphicsRender class that would convert State and Structure objects from the simulations into graphical representations.
___
### User Interface
Our Cell Society application will have a Main Menu from which users can maneuver to a input data screen and a run simulation screen. We decided to go with a Main Menu design instead of going directly into the simulation so that we have more flexibility if we wanted to add another feature to the Application. 
With in our simulation we plan to have a start stop button to toggle through the simulation.
___
### Design Details
*Simulation Implementation*
- Cell class: The Cell class describes the behavior of a single cell in the grid. The Cell class will also have reference to a game loop created in the Simulation class. Through the game loop, we will have more control over how time progresses through the Simulation. Whenever the Cell needs to get the time of the simulation, it will call the getTime() method from the game loop for the simulation.
The Cell class will have a reference to a Neighborhood class, which would contain references to the Cells around it. The Simulation will initialize the Neighborhood for each Cell. Therefore, when the Simulation class initializes the Neighborhood for each Cell, we can modify what defines a Neighborhood to define what defines local interaction between Cells. 
Each Cell will then have a calculateState() function, which is where the logic or underlying function for each Cell will be defined.
- State class: A State class describes what state a cell is in. The State class will work like an enumeration.
- Location class: Will contain a x, y value for the position in the Structure. Every Cell will have a Location.
- Structure class: Although most simulations are displayed through Grids, we added a Structure class for the Grid class to inherit from, in case the shape of the simulation changes
- Grid class: The Grid class will have a Cell[][] to keep track of the cells in the simulation.
- Simulation class: The Simulation class will have the game loop in it, which will run based off a JavaFX Timeline. In the simulation loop the game loop will be stepped for each discrete time value as long as the isRunning variable is true. The Cells will then be updated each time the loop runs and so will the graphics. That way, if the Simulation is rewinded, the Cells will still be updated, even though the simulation is running. The Simulation class will also be responsible for setting up the Simulation variables.

*Graphics Rendering*
- To separate functionality from graphics, we will create a GraphicsRender class that will take functions and return JavaFX nodes determining how they will be rendered based on the values for the Cell or Grid.

*Input Reading*
- Our team still need to learn how to read from XML files to configure a simulation, so we decided to create a whole InputReader class to encapsulate that issue. That InputReader will be called upon to initialize the Cells and the Grid for the Simulation class.

*SceneMode Maneuvering*
- In our SceneManager class there will be methods that take care of switching the scene to be displayed on the stage. One SceneManager object must be used throughout the whole application so that the stage is kept constant and only scenes are swapped. 
- Each SceneMode will return a Scene object that the SceneManager will present on the stage it references.

___
### Design Considerations
We considered launching the application directly into the simulation, but we decided it would be better to have a Main Menu screen where the user could toggle between options if more options appeared.
For maneuvering between SceneModes, we considered not having a SceneManager at all and creating the next SceneMode we wanted to transition to it. Pros of that case is we wouldn’t have to reset each SceneMode whenever it is presented to the stage of the SceneManager, but cons of it is that each SceneMode would have to keep reference to the Stage of the application and would have to pass it along between SceneModes. We decided to go with the SceneManager design, even though we would have to create each SceneMode with a reference to the SceneManager and have to reset each SceneMode as its presented, we would not have to worry about passing the Stage between SceneModes. Additionally with the SceneManager design, we could easily define transitions in the SceneMode superclass to allow for easier implementations of transitions in the SceneMode subclasses.
___
### Team Responsibilities
- Together: We will discuss each part of the application with each other, to ensure we are all on the same page. The foundation of any great team is communication. And we will make sure to communicate our ideas and any updates for each part of the application as they arise. Implementation will then be divided up on the different parts of the application. 
- Daniel: Implementing the overarching structure of the application
- Grayson: Implementing the data conversion from XML files
- Ryan: Implementing the Simulation and the SimulationMode
