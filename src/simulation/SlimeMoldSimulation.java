package simulation;

import java.util.HashMap;
import java.util.Map;

import cellsociety_team07.ColorMap;
import cellsociety_team07.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import neighborhood_definer.NeighborhoodDefiner;
import structure.Structure;

/**
 * This class implements the SlimeMold simulation.
 * 
 * @author Daniel Chai
 */
public class SlimeMoldSimulation extends Simulation {
	private static final State stateEmpty = new State("EMPTY");
	private static final State stateMold = new State("MOLD");
	
	protected SlimeMoldSimulation(EventHandler<ActionEvent> goToMenu, int rows, int columns) {
		super(goToMenu, rows, columns);
	}

	@Override
	protected ColorMap initColors() {
		Map<State, Color> pairings = new HashMap<State, Color>();
		pairings.put(stateEmpty, Color.BLACK);
		pairings.put(stateMold, Color.GREEN);
		return new ColorMap(pairings);
	}
	
	@Override
	protected void initStates() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void updateGrid() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected NeighborhoodDefiner getNeighborhoodDefiner() {
		// TODO Auto-generated method stub
		return null;
	}
}
