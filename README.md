# cell-society

Cellular automata simulations created as a team class project for CS 308

Contributors: Daniel Chai, Ryan Bergamini, Grayson Wise

## Overview

Cellular Automata are simulations based on a model that consists of a regular grid of cells, 
each in one of a finite number of states (such as on and off). Simulations start with all 
cells in an initial state and are run by updating each cell based on a set of fixed rules 
described in terms of the cell's current state and the states of its immediate neighbors. 
This program implements five different Cellular Automata simulations.

## Simulations

- [Schelling's Model of Segregation](http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/) 
- [Predator-Prey](http://nifty.stanford.edu/2011/scott-wator-world/WatorWorld.htm) 
- [Spreading of Fire](http://nifty.stanford.edu/2007/shiflet-fire/)
- [Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)
- [Slime Molds](http://zool33.uni-graz.at/schmickl/Self-organization/Group_behavior/Slime_mold_behavior/slime_mold_behavior.html)

## Features

- Configure initial states through xml files in data/xml/
- Two ways to run a simulation:
  1. Press "Show Next Step" to manually step through simulation
  2. Press "Play" to automatically play simulation
- Cells in a grid can be set to three different shapes:
  1. Squares
  2. Triangles
  3. Hexagons
- Grid parameters can be updated at any time
- Cell states can be randomized at any time
