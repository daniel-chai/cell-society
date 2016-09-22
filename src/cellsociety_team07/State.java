package cellsociety_team07;

/**
 * This class represents the state that a Cell is in.
 */
public class State 
{
	public static State OUT_OF_BOUNDS = new State("OUT_OF_BOUNDS");
	
	private String s;
	
	/**
	 * Constructor for State class.
	 */
	public State(String s) {
		this.s = s;
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
			return this.s.equals(other.s);
		}
	}
}