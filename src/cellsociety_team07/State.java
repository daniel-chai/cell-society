package cellsociety_team07;

/**
 * This class represents the state that a Cell is in.
 */
public class State
{
	public static final State OUT_OF_BOUNDS = new State("OUT_OF_BOUNDS");
	
	private String state;
	
	/**
	 * Constructor for State class.
	 */
	public State(String s) {
		this.state = s;
	}
	
	
	public String toString()
	{
		return state;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof State)) {
			return false;
		}
		else {
			State other = (State) obj;
			return this.state.equals(other.state);
		}
	}
	
	@Override
	public int hashCode()
	{
		return state.hashCode();
	}
}