package cellsociety_team07;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import javafx.scene.paint.Color;

public class ColorMap 
{
	private Map<State,Color> colorMap;
	
	/**
	 * Creates an unmodifiable Map
	 * @param stateColorPairings- Map from States to their corresponding Color
	 */
	public ColorMap(Map<State,Color> stateColorPairings)
	{
		colorMap = Collections.unmodifiableMap(stateColorPairings);
	}
	
	/**
	 * Returns the corresponding Color for the given state state
	 * @param state- State of the Cell
	 * @return the Color the Cell should be
	 */
	public Color getColor(State state)
	{
		if(colorMap.containsKey(state))
		{
			return colorMap.get(state);
		}
		System.out.println("ERRRRRRROOOOOOORRR STATE NOT FOUND");
		System.out.println(state.toString());
		return Color.BLUE;
	}
	
}
